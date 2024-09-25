package com.lambda.lambda.common.helper.string;

import com.lambda.lambda.common.util.string.StringMap;
import com.lambda.lambda.common.util.string.StringReplacer;

/**
 * Helper class for String Code Operations
 */
public final class StringReplacementHelper {
    /**
     * Creates a new String Builder
     */
    public static StringBuilder newBuilder() {
        return new StringBuilder();
    }

    /**
     * Replaces all instances of a substring within a text
     */
    public static String replace(String text, String target, String replacement) {
        return StringReplacementHelper.replace(text, StringMap.newInstance(target, replacement));
    }

    /**
     * Replaces all instances of a substrings within a text denoted by a replacement map
     */
    public static String replace(String text, StringMap replacementMap) {
        return StringReplacer.newInstance().replace(text, replacementMap);
    }

    /***
     * 
     * Private Constructor to prevent instantiation
     */

    private StringReplacementHelper() {
        super();
    }
}
