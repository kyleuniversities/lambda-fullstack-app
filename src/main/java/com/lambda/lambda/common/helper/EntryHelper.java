package com.lambda.lambda.common.helper;

import com.lambda.lambda.common.util.entry.Entry;
import com.lambda.lambda.common.util.entry.OrdinaryValueEntry;

public class EntryHelper {
    /**
     * Creates a new Entry
     */
    public static <K, V> Entry<K, V> newEntry(K key, V value) {
        return OrdinaryValueEntry.newInstance(key, value);
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private EntryHelper() {
        super();
    }
}
