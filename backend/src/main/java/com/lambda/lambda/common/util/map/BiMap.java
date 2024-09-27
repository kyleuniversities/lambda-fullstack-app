package com.lambda.lambda.common.util.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class BiMap<K, V> implements Map<K, V> {
    // Instance Fields
    private Map<K, V> map;
    private Map<V, K> reverseMap;

    // Constructor Method
    protected BiMap(Map<K, V> map, Map<V, K> reverseMap) {
        super();
        this.map = map;
        this.reverseMap = reverseMap;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    public boolean containsReverseKey(V key) {
        return this.reverseMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return this.map.get(key);
    }

    public K getReverse(V value) {
        return this.reverseMap.get(value);
    }

    @Override
    public V put(K key, V value) {
        this.reverseMap.put(value, key);
        return this.map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        V value = this.map.get(key);
        this.reverseMap.remove(value);
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        this.map.clear();
        this.reverseMap.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.map.keySet();
    }

    public Set<V> reverseKeySet() {
        return this.reverseMap.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.map.entrySet();
    }
}
