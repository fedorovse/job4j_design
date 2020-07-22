package ru.job4j.collection;

import java.util.Iterator;

public class SimpleHashMap<K, V> implements Iterable<K> {

    private int modCount = 0;
    private int capacity;
    private double loadFactor = 0.75;



    public boolean insert(K key, V value) {
        return true;
    }

    public V get(K key) {
        return null;
    }

    public boolean delete(K key) {
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public K next() {
                return null;
            }
        };
    }
}
