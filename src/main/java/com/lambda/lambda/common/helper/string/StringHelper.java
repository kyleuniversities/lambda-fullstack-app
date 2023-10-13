package com.lambda.lambda.common.helper.string;

import java.util.List;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.IterationHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.util.string.StringList;

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
     * Concatenates Strings together from a List
     */
    public static String join(List<String> list, String delimiterText) {
        return StringHelper.join(list, delimiterText, false, false);
    }

    /**
     * Concatenates Strings together from a List
     */
    public static String join(List<String> list, String delimiterText,
            boolean willIncludeDelimiterAtStart, boolean willIncludeDelimiterAtEnd) {
        StringBuilder joined = StringHelper.newBuilder();
        ConditionalHelper.ifThen(willIncludeDelimiterAtStart, () -> joined.append(delimiterText));
        ListHelper.forEach(list, (String string) -> joined.append(string + delimiterText));
        ConditionalHelper.ifThen(!willIncludeDelimiterAtEnd,
                () -> joined.delete(joined.length() - delimiterText.length(), joined.length()));
        return joined.toString();
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
     * Splits a String into parts
     */
    public static StringList split(String text, String regex) {
        return StringList.newInstance(text.split(regex));
    }

    /**
     * Checks if a substring is present at a given index
     */
    public static boolean substringEquals(String text, String target, int index) {
        if (index + target.length() > text.length()) {
            return false;
        }
        return text.substring(index, index + target.length()).equals(target);
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
