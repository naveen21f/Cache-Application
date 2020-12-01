package com.company;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class MRUCachePolicy<V> implements CacheEvictionPolicy<V> {

    private final static Logger LOGGER = Logger.getLogger(LRUCachePolicy.class.getName());

    final int MAX_CACHE_SIZE;
    Deque<CacheEntry<Integer, V>> cacheEntryDeque;
    ConcurrentHashMap<Integer, CacheEntry<Integer, V>> cacheEntryConcurrentHashMap;

    public MRUCachePolicy(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        cacheEntryDeque = new LinkedList<>();
        cacheEntryConcurrentHashMap = new ConcurrentHashMap<>(cacheSize);
    }

    @Override
    public void insert(int key, V value) {

        CacheEntry<Integer, V> cacheEntry = cacheEntryConcurrentHashMap.get(key);
        if (cacheEntry != null) {
            cacheEntryDeque.remove(cacheEntry);
        } else {
            if (isFull()) {
                CacheEntry cacheEntryEvict = cacheEntryDeque.removeFirst();
                cacheEntryConcurrentHashMap.remove(cacheEntryEvict.getKey());
            }
        }
        CacheEntry newEntry = new CacheEntry(key, value);
        cacheEntryDeque.addFirst(newEntry);
        cacheEntryConcurrentHashMap.put(key, newEntry);
    }

    @Override
    public V get(int key) {

        CacheEntry<Integer, V> cacheEntry = cacheEntryConcurrentHashMap.getOrDefault(key, null);
        if (cacheEntry != null) {
            LOGGER.info("key {} found in cache", key);
            cacheEntryDeque.remove(cacheEntry);
            cacheEntryDeque.addFirst(cacheEntry);
            return cacheEntry.getVal();
        }
        LOGGER.info("key {} not found in cache", key);
        return null;
    }

    @Override
    public Map<Integer, V> stateOfCache() {

        Map<Integer, V> cacheState = new HashMap<>(cacheEntryDeque.size());
        cacheEntryDeque.stream().iterator().forEachRemaining(e->{
            cacheState.put(e.getKey(), e.getVal());
        });
        return cacheState;
    }

    @Override
    public Boolean isFull() {

        return cacheEntryDeque.size() >= MAX_CACHE_SIZE;
    }

    @Override
    public Boolean isEmpty() {

        return cacheEntryDeque.size() == 0;
    }
}
