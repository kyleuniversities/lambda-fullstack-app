package com.lambda.lambda.common.helper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

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
     * Private Constructor to prevent instantiation
     */
    private MapHelper() {
        super();
    }
}
