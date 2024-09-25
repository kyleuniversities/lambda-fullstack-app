package com.lambda.lambda.common.util.string;

import com.lambda.lambda.common.util.entry.ValueEntry;

public final class StringEntry extends ValueEntry<String, String> {
    // New Instance Method
    public static StringEntry newInstance(String key, String value) {
        return new StringEntry(key, value);
    }

    // Constructor Method
    private StringEntry(String key, String value) {
        super(key, value);
    }
}
