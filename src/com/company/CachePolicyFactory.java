package com.company;

public class CachePolicyFactory<V> {

    public static CacheEvictionPolicy getCachePolicy(CachePolicy cachePolicy , int size) {

        switch (cachePolicy) {
            case MRU: return new MRUCachePolicy(size);
            case LRU:
            default: return new LRUCachePolicy(size);
        }

    }
}
