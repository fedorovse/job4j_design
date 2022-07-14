package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Реализация простого множества. Хранит уникальные элементы.
 * @param <T> тип хранимых элементов
 */
public class SimpleSet<T> implements Set<T> {

    /**
     * поле set контейнер для хранения элементов множества
     */
    private final SimpleArrayList<T> set = new SimpleArrayList<>();

    /**
     * Метод добавит новый элемент в множество при условии,
     * что такого элемента в множестве нет
     * @param value элемент типа Т, который добавляем в множество
     * @return boolean true - если множество изменилось в результате операции
     */
    @Override
    public boolean add(T value) {
        boolean result = false;
        if (!this.contains(value)) {
            set.add(value);
            result = true;
        }
        return result;
    }

    /**
     * Проверка наличия элемента в множестве
     * @param value типа Т наличие которого проверяем
     * @return boolean true - если данный элемент присутствует в множестве
     */
    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T element : set) {
            if (Objects.equals(element, value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
