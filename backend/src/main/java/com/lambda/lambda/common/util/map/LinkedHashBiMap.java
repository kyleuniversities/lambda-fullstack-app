package com.lambda.lambda.common.util.map;

import com.lambda.lambda.common.helper.MapHelper;

public class LinkedHashBiMap<K, V> extends BiMap<K, V> {
    // New Instance Method
    public static <K, V> LinkedHashBiMap<K, V> newInstance() {
        return new LinkedHashBiMap<>();
    }

    // Constructor Method
    private LinkedHashBiMap() {
        super(MapHelper.newLinkedHashMap(), MapHelper.newLinkedHashMap());
    }
}
