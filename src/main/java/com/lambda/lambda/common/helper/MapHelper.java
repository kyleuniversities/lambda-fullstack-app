package com.lambda.lambda.common.helper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import com.lambda.lambda.common.util.entry.Entry;

/**
 * Helper class for Map Operations
 */
public final class MapHelper {
    /**
     * Iterates through the entries of a Map
     */
    public static <K, V> void forEach(Map<K, V> map, BiConsumer<K, V> action) {
        MapHelper.forEach(map, FunctionHelper.newBiPredicateFromBiConsumer(action));
    }

    /**
     * Iterates through the entries of a Map and stops if a break point is reached
     */
    public static <K, V> boolean forEach(Map<K, V> map, BiPredicate<K, V> action) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (!action.test(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Maps the values of a Map
     */
    public static <K, V, W> Map<K, W> mapValues(Map<K, V> map, Function<V, W> mapping) {
        Map<K, W> mapped = MapHelper.newLinkedHashMap();
        MapHelper.forEach(map, (K key, V value) -> mapped.put(key, mapping.apply(value)));
        return mapped;
    }

    /**
     * Creates a new Hash Map
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    /**
     * Creates a new Linked Hash Map
     */
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    /**
     * Converts a Map to an Entry List
     */
    public static <K, V> List<Entry<K, V>> toEntryList(Map<K, V> map) {
        List<Entry<K, V>> entryList = ListHelper.newArrayList();
        MapHelper.forEach(map, (K key, V value) -> entryList.add(EntryHelper.newEntry(key, value)));
        return entryList;
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private MapHelper() {
        super();
    }
}
