package com.lambda.lambda.common.helper;

/**
 * Helper class for String Operations
 */
public final class IntegerHelper {
    /**
     * Returns the absolute value of an integer
     */
    public static int absoluteValue(int value) {
        return ConditionalHelper.ifReturnElse(value < 0, -value, value);
    }

    /**
     * Operation for greater than
     */
    public static boolean greaterThan(int a, int b) {
        return a > b;
    }

    /**
     * Operation for less than
     */
    public static boolean lessThan(int a, int b) {
        return a < b;
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private IntegerHelper() {
        super();
    }
}
