package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    /**
     * коэффициент заполнения внутреннего массива с объектами,
     * если он будет превышен, то массив увеличится в два раза.
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * capacity - текущая вместимость массива table, где хранятся пары
     */
    private int capacity = 8;

    /**
     * count - текущее количество пар в мапе
     */
    private int count = 0;

    /**
     * modCount - счетчик операций меняющих структуру мапы.
     * Инкрементируется при каждой такой операции
     */
    private int modCount = 0;

    /**
     * table - массив, где хранятся пары
     */
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (((float) count / capacity) >= LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int getIndex(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
    }

    /**
     * Вычисляем хэш по хэшкоду ключа
     * @param hashCode int хэшкод ключа
     * @return int хэш мапы
     */
    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> capacity);
    }

    /**
     * Рассчитывает индекс бакета из хэша
     * @param hash - хэш рассчитанный на основе хэшкода объекта
     * @return int - индекс в массиве table для данного хэша
     */
    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    /**
     * Увеличивает в два раза массив с объектами мапы,
     * и копирует в него все объекты из старого массива
     * индексы пересчитываются
     */
    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity *= 2;
        table = new MapEntry[capacity];
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                table[getIndex(oldTable[i].key)] = oldTable[i];
            }
        }
    }

    /**
     * Ищет в мапе значение по ключу и возвращает его
     * если такого ключа нет, то вернет null
     * @param key типа K ключ по которому идет поиск
     * @return объект типа V, либо null если объект не найден
     */
    @Override
    public V get(K key) {
        V result = null;
        int index = getIndex(key);
        if (table[index] != null && Objects.equals(key, table[index].key)) {
            result = table[index].value;
        }
        return result;
    }

    /**
     * Удаление объекта в мапе
     * @param key типа K - ключ объекта, который необходимо удалить
     * @return bollean true если объект успешно удален
     */
    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = getIndex(key);
        if (table[index] != null) {
            table[index] = null;
            result = true;
            modCount++;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            final int expectedModCount = modCount;

            int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        index = i;
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}