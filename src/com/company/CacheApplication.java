package com.company;

import java.util.logging.Logger;

public class CacheApplication {

    private final static Logger LOGGER = Logger.getLogger(CachePolicy.class.getName());

    public static void main(String[] args) {

        // write your code here
        LOGGER.info("----- CACHE APPLICATION -----");
        System.out.println("___________ LRU ______________");
        CacheEvictionPolicy<String> lruCache = CachePolicyFactory.getCachePolicy(CachePolicy.LRU, 5);
        lruCache.insert(1, "A");
        lruCache.insert(2, "B");
        lruCache.insert(3, "C");
        lruCache.insert(4, "D");
        lruCache.insert(5, "E");
        lruCache.insert(6, "F");
        lruCache.insert(2, "B");
        lruCache.insert(7, "G");

        lruCache.stateOfCache().entrySet().forEach(e -> {
            System.out.println(e.getKey() + ":" + e.getValue());
        });

        System.out.println("___________ MRU ______________");
        CacheEvictionPolicy<String> mruCache = CachePolicyFactory.getCachePolicy(CachePolicy.MRU, 5);
        mruCache.insert(1, "A");
        mruCache.insert(2, "B");
        mruCache.insert(3, "C");
        mruCache.insert(4, "D");
        mruCache.insert(5, "E");
        mruCache.insert(6, "F");
        mruCache.insert(3, "C");
        mruCache.insert(7, "G");
        mruCache.insert(2, "B");
        mruCache.insert(1, "A");
        mruCache.insert(2, "B");

        mruCache.stateOfCache().entrySet().forEach(e -> {
            System.out.println(e.getKey() + ":" + e.getValue());
        });
    }
}
