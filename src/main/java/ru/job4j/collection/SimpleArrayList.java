package ru.job4j.collection;

import java.util.*;

/**
 * Класс реализует простую параметризованную коллекцию
 * для хранения объектов на базе массива Object[]
 * @param <T>
 */
public class SimpleArrayList<T> implements SimpleList<T> {

    /**
     * массив где хранятся объекты коллекции
     */
    private T[] container;

    /**
     * количество элементов в коллекции
     */
    private int size;

    /**
     * Счетчик изменений коллекции инкрементируется при любом изменении коллекции
     */
    private int modCount;

    public SimpleArrayList() {
        this.container = (T[]) new Object[10];
    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void grow() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    /**
     * Добавление элемента в конец коллекции
     * @param value типа T
     */
    @Override
    public void add(T value) {
        if (size == container.length) {
            this.grow();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    /**
     * Замена элемента коллекции с заданным индексом на переданный элемент
     * Если index находится вне допустимых пределов, то кинет IndexOutOfBoundsException
     * @param index индекс элемента, который будем заменять
     * @param newValue элемент, который записываем в коллекцию
     * @return T - элемент, который был в коллекции по переданному индексу до замены
     */
    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T old = container[index];
        container[index] = newValue;
        modCount++;
        return old;
    }

    /**
     * Удаление элемента с заданным индексом из коллекции
     * Если index находится вне допустимых пределов, то кинет IndexOutOfBoundsException
     * @param index удаляемого элемента
     * @return T - элемент который удалили из коллекции
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T old = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return old;
    }

    /**
     * Возвращает элемент коллекции, который хранится по заданному индексу
     * Если index находится вне допустимых пределов, то кинет IndexOutOfBoundsException
     * @param index элемента, который хотим прочитать
     * @return T- элемент коллекции с заданным индексом
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    /**
     * Возвращает количество элементов в коллекции
     * @return int - количество элементов в коллекции
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int point = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return  container[point++];
            }
        };
    }
}