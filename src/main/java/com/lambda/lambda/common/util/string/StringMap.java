package com.lambda.lambda.common.util.string;

import java.util.LinkedHashMap;
import java.util.Map;
import com.lambda.lambda.common.helper.MapHelper;

/**
 * Utility map for Strings
 */
public final class StringMap extends LinkedHashMap<String, String> {
    // New Instance Methods
    public static StringMap newInstance() {
        return new StringMap();
    }

    public static StringMap newInstance(String key, String value) {
        StringMap stringMap = StringMap.newInstance();
        stringMap.put(key, value);
        return stringMap;
    }

    public static StringMap newInstance(Map<String, String> map) {
        StringMap stringMap = StringMap.newInstance();
        MapHelper.forEach(map, (String key, String value) -> stringMap.put(key, value));
        return stringMap;
    }

    // Constructor
    private StringMap() {
        super();
    }

    // Accessor Methods
    public int getInteger(String key) {
        return Integer.parseInt(this.get(key));
    }

    public double getDouble(String key) {
        return Double.parseDouble(this.get(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(this.get(key));
    }
}
