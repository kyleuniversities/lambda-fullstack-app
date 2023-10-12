package com.lambda.lambda.common.helper;

/**
 * Helper class for String Operations
 */
public final class StringHelper {
    /**
     * Creates a new String Builder
     */
    public static StringBuilder newBuilder() {
        return new StringBuilder();
    }

    /**
     * Repeats text for a given number of times
     */
    public static String repeatText(String text, int times) {
        StringBuilder repeatedText = StringHelper.newBuilder();
        IterationHelper.forEach(times, () -> repeatedText.append(text));
        return repeatedText.toString();
    }

    /**
     * Converts an object to String even if null
     */
    public static String toString(Object item) {
        return item + "";
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private StringHelper() {
        super();
    }
}
