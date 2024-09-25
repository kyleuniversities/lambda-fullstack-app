package com.lambda.lambda.app.helper;

import java.util.List;
import java.util.Map;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.string.StringCodeHelper;
import com.lambda.lambda.common.helper.string.StringHelper;

/**
 * Helper class for Map Operations
 */
public final class CodeHelper {
    /**
     * Converts an output to code
     */
    public static String toCode(Object item) {
        return StringCodeHelper.toCode(StringHelper.toString(item));
    }

    /**
     * Converts code to text
     */
    public static String toText(String code) {
        return StringCodeHelper.toText(code);
    }

    /**
     * Converts a String list to text
     */
    public static List<String> toText(List<String> list) {
        return ListHelper.map(list, CodeHelper::toText);
    }

    /**
     * Converts a String map to text
     */
    public static Map<String, String> toText(Map<String, String> map) {
        return MapHelper.mapValues(map, CodeHelper::toText);
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private CodeHelper() {
        super();
    }
}
