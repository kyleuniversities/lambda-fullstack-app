package com.lambda.lambda.common.helper.string;

import java.util.List;
import com.lambda.lambda.common.helper.ListHelper;

/**
 * Helper class for String List Operations
 */
public final class StringListHelper {
    /**
     * Filters leading and trailing empty Strings
     */
    public static void filterSurroundingEmptyStrings(List<String> list) {
        ListHelper.filterSurrounding(list, StringHelper::isNotEmpty);
    }

    /**
     * Filters leading and trailing empty Strings
     */
    public static List<String> filterSurroundingEmptyStringsClone(List<String> list) {
        return ListHelper.filterSurroundingClone(list, StringHelper::isNotEmpty);
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private StringListHelper() {
        super();
    }
}
