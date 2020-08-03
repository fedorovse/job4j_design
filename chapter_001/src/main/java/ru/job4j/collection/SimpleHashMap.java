package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements Iterable<K> {

    static class Node<K, V> {
        final int hash;
        final K key;
        private V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "key=" + key + ", value=" + value;
        }
    }

    static final int hash(Object key) {
        int rsl = 0;
        if (key != null) {
            int h = key.hashCode();
            rsl = h ^ (h >>> 16);
        }
        return rsl;
    }

    static final int DEFAULT_CAPACITY = 16;
    static final double DEFAULT_LOAD_FACTOR = 0.75;

    /**
     * size - текущее количество пар в мапе
     */
    private int size = 0;
    /**
     * modCount - инкремент при изменении в мапе для отслеживания наличия изменений
     */
    private int modCount = 0;
    /**
     * capacity - текущий размер массива
     */
    private int capacity;
    /**
     * loadFactor - загруженность массива в % при которой он расширяется
     */
    private double loadFactor;
    /**
     * хеш таблица для хранения пар
     */
    private Node<K, V>[] table;

    public SimpleHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.table = new Node[DEFAULT_CAPACITY];
    }

    /**
     * Добавление в мапу пары ключ - значение
     * @param key - ключ
     * @param value - значение
     * @return при успешном добавлении вернет true
     */
    public boolean insert(K key, V value) {
        checkSize();
        boolean rsl = false;
        int hashKey = SimpleHashMap.hash(key);
        int index = (capacity - 1) & hashKey;
        if (table[index] == null) {
            table[index] = new Node<>(hashKey, key, value);
            rsl = true;
            size++;
            modCount++;
        } else if (Objects.equals(table[index].getKey(), key)) {
            table[index].setValue(value);
            rsl = true;
            modCount++;
        }
        return rsl;
    }

    private void checkSize() {
        if (size >= (int) (loadFactor * capacity)) {
            resize();
        }
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Node<K, V>[] newTable = new Node[newCapacity];
        int hashKey;
        int oldIndex;
        int newIndex;
        for (K key: this) {
            hashKey = SimpleHashMap.hash(key);
            oldIndex = (capacity - 1) & hashKey;
            newIndex = (newCapacity - 1) & hashKey;
            newTable[newIndex] = table[oldIndex];
        }
        capacity = newCapacity;
        table = newTable;
    }

    /**
     *
     * @return количество пар в мапе
     */
    public int getSize() {
        return this.size;
    }

    /**
     * По заданному ключу вернуть значение
     * @param key - объект ключа
     * @return V - значение соответствующее ключу
     */
    public V get(K key) {
        V rsl = null;
        int hashKey = SimpleHashMap.hash(key);
        int index = (capacity - 1) & hashKey;
        if (table[index] != null && table[index].getHash() == hashKey) {
            rsl = table[index].getValue();
        }
        return rsl;
    }

    /**
     * Удаляет пару ключ - значение из мапы при наличии пары с заданным ключом
     * @param key - объект ключа
     * @return вернет true если объект был удален
     */
    public boolean delete(K key) {
        boolean rsl = false;
        int hashKey = SimpleHashMap.hash(key);
        int index = (capacity - 1) & hashKey;
        if (table[index] != null && Objects.equals(table[index].getKey(), key)) {
            table[index] = null;
            rsl = true;
            size--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            /**
             * itCountElements - счетчик количества перебранных элементов
             */
            private int itCountElements = 0;
            /**
             * itIndex - индекс где возможно хранится следующий Node
             */
            private int itIndex = 0;

            @Override
            public boolean hasNext() {
                checkModCount();
                return itCountElements < size;
            }

            @Override
            public K next() {
                K rsl = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (int index = itIndex; index < table.length; index++) {
                    Node<K, V> node = table[index];
                    if (node != null) {
                        rsl = node.getKey();
                        itIndex = index;
                        itCountElements++;
                        break;
                    }
                }
                return rsl;
            }

            private void checkModCount() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
