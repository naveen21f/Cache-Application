package com.company;

public class CacheEntry<K, V> implements Comparable<CacheEntry>{

    private K key;
    private V val;

    public CacheEntry(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K getKey() {
        return key;
    }

    public V getVal() {
        return val;
    }

    @Override
    public int compareTo(CacheEntry cacheEntry) {
        if(cacheEntry.getKey().equals(this.key))
            return 0;
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheEntry<?, ?> that = (CacheEntry<?, ?>) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        return val != null ? val.equals(that.val) : that.val == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (val != null ? val.hashCode() : 0);
        return result;
    }
}
