package com.company;

import java.util.Map;

public interface CacheEvictionPolicy<V> {

    void insert(int key, V value);
    V get(int key);
    Map<Integer, V> stateOfCache();
    Boolean isFull();
    Boolean isEmpty();
}
