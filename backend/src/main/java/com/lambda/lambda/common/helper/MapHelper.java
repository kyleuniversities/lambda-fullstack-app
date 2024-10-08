package com.lambda.lambda.common.helper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;
import com.lambda.lambda.common.util.entry.Entry;
import com.lambda.lambda.common.util.map.BiMap;
import com.lambda.lambda.common.util.map.LinkedHashBiMap;

/**
 * Helper class for Map Operations
 */
public final class MapHelper {
    /**
     * Checks if a map contains a key
     */
    public static <K, V> boolean containsKey(Map<K, V> map, K key) {
        return map.containsKey(key);
    }

    /**
     * Checks if a map does not contain a key
     */
    public static <K, V> boolean doesNotContainKey(Map<K, V> map, K key) {
        return !MapHelper.containsKey(map, key);
    }

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
     * Gets a value from a map
     */
    public static <K, V> V get(Map<K, V> map, K key) {
        return map.get(key);
    }

    /**
     * Gets a value from a map, and if it does not exists, initializes it first
     */
    public static <K, V> V getInitialize(Map<K, V> map, K key, Supplier<V> valueInitializer) {
        ConditionalHelper.ifThen(MapHelper.doesNotContainKey(map, key),
                () -> MapHelper.put(map, key, valueInitializer.get()));
        return MapHelper.get(map, key);
    }

    /**
     * Checks if a map is empty
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map.isEmpty();
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
     * Creates a new BiMap
     */
    public static <K, V> BiMap<K, V> newBiMap() {
        return MapHelper.newLinkedHashBiMap();
    }

    /**
     * Creates a new Hash Map
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    /**
     * Creates a new Linked Hash BiMap
     */
    public static <K, V> LinkedHashBiMap<K, V> newLinkedHashBiMap() {
        return LinkedHashBiMap.newInstance();
    }

    /**
     * Creates a new Linked Hash Map
     */
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    /**
     * Puts a key-value pair into a map
     */
    public static <K, V> void put(Map<K, V> map, K key, V value) {
        map.put(key, value);
    }

    /**
     * Converts a Map to a Key List
     */
    public static <K, V> List<K> toKeyList(Map<K, V> map) {
        return ListHelper.map(MapHelper.toEntryList(map), (Entry<K, V> entry) -> entry.getKey());
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
     * Converts a Map to a Value List
     */
    public static <K, V> List<V> toValueList(Map<K, V> map) {
        return ListHelper.map(MapHelper.toEntryList(map), (Entry<K, V> entry) -> entry.getValue());
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private MapHelper() {
        super();
    }
}
