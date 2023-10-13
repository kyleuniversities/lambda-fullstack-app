package com.lambda.lambda.common.helper;

/**
 * Helper class for Conditional Operations
 */
public final class ConditionalHelper {
    /**
     * Returns one value if a condition is true, or another if it is false
     */
    public static <T> T ifReturnElse(boolean condition, T acceptanceValue, T rejectionValue) {
        return condition ? acceptanceValue : rejectionValue;
    }

    /**
     * Performs an action if a condition is met
     */
    public static <T> void ifThen(boolean condition, Runnable action) {
        if (condition) {
            action.run();
        }
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private ConditionalHelper() {
        super();
    }
}
