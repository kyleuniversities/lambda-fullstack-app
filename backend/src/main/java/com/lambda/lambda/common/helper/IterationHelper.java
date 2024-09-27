package com.lambda.lambda.common.helper;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import com.lambda.lambda.common.helper.number.IntegerHelper;

/**
 * Helper class for Iterative Operations
 */
public final class IterationHelper {
    /**
     * Performs an action a given number of times
     */
    public static void forEach(int upTo, Runnable action) {
        IterationHelper.forEach(upTo, FunctionHelper.newConsumerFromRunnable(action));
    }

    /**
     * Loops through a range of given values
     */
    public static void forEach(int upTo, Consumer<Integer> action) {
        IterationHelper.forEach(upTo, FunctionHelper.newPredicateFromConsumer(action));
    }

    /**
     * Loops through a range of given values and stops if a break point is reached
     */
    public static boolean forEach(int upTo, Predicate<Integer> action) {
        return IterationHelper.forEach(0, upTo, action);
    }

    /**
     * Loops through a range of given values
     */
    public static void forEach(int start, int upTo, Consumer<Integer> action) {
        IterationHelper.forEach(start, upTo, FunctionHelper.newPredicateFromConsumer(action));
    }

    /**
     * Loops through a range of given values and stops if a break point is reached
     */
    public static boolean forEach(int start, int upTo, Predicate<Integer> action) {
        int increment = ConditionalHelper.ifReturnElse(start < upTo, 1, -1);
        return IterationHelper.forEach(start, upTo, increment, action);
    }

    /**
     * Loops through a range of given values and stops if a break point is reached
     */
    public static boolean forEach(int start, int upTo, int increment, Predicate<Integer> action) {
        BiPredicate<Integer, Integer> actionCondition = ConditionalHelper
                .ifReturnElse(increment > 0, IntegerHelper::lessThan, IntegerHelper::greaterThan);
        return IterationHelper.forEach(start, upTo, increment, actionCondition, action);
    }

    /**
     * Loops through a range of given values and stops if a break point is reached
     */
    public static boolean forEach(int start, int upTo, int increment,
            BiPredicate<Integer, Integer> actionCondition, Predicate<Integer> action) {
        for (int i = start; actionCondition.test(i, upTo); i += increment) {
            if (!action.test(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * While loops through an action
     */
    public static void whileLoop(Supplier<Boolean> condition, Runnable action) {
        while (condition.get()) {
            action.run();
        }
    }

    /**
     * While loops through a breakable action
     */
    public static boolean whileLoop(Supplier<Boolean> condition, Supplier<Boolean> action) {
        while (condition.get()) {
            if (!action.get()) {
                return false;
            }
        }
        return true;
    }

    /**
     * While loops through a breakable action
     */
    public static void whileLoopUntilFalse(Supplier<Boolean> action) {
        IterationHelper.whileLoop(() -> true, action);
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private IterationHelper() {
        super();
    }
}
