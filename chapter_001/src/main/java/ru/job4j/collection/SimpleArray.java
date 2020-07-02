package ru.job4j.collection;

import java.util.*;


/**
 * SimpleArray - реализует динамический саморасширяющийся массив для хранения различных объектов
 * Очень сильно усеченная версия ArrayList
 * @author Fedorov Sergey email: ingor-ru@mail.ru
 * @since 30.05.2020
 * @version 1
 * @param <T>
 */
public class SimpleArray<T> implements Iterable<T> {
    /**
     * START_SIZE - длинна внутреннего массива по дефолту
     */
    private final int START_SIZE = 10;
    /**
     * size - текущая реальная длинна внутреннего массива
     */
    private int size = 0;
    /**
     * Object[] array - внутренний массив для хранения объектов
     */
    private Object[] array;
    /**
     * position - индекс в массиве array по которому будет добавлен следующий элемент
     */
    private int position = 0;
    /**
     * modCount - счетчик который изменяется при добавлении элементов.
     * Необходим для предотвращения изменений при обходе массива итератором
     */
    private int modCount = 0;

    public SimpleArray() {
        this.size = START_SIZE;
        this.array = new Object[this.size];
    }

    public SimpleArray(int size) {
        this.size = size;
        this.array = new Object[size];
    }

    public T get(int index) {
        Objects.checkIndex(index, this.position);
        return (T) array[index];
    }

    public void add(T model) {
        if (this.position == this.size) {
            this.size = array.length + (array.length / 2) + 1;
            Object[] temp = Arrays.copyOf(array, this.size);
            array = temp;
        }
        this.array[position++] = model;
        this.modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                checkModCount();
                return this.index < position;
            }

            @Override
            public T next() {
                checkModCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[index++];
            }

            private void checkModCount() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
