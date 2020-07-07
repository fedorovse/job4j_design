package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

/**
 * SimpleSet - реализует простой контейнер для хранения уникальных объектов
 * @author Fedorov Sergey email: ingor-ru@mail.ru
 * @since 07.07.2020
 * @version 1
 * @param <T>
 */
public class SimpleSet<T> implements Iterable<T> {
    
    /**
     * array - динамический массив для хранения объектвов
     */
    private SimpleArray<T> array = new SimpleArray<T>();

    /**
     * Добавление уникального объекта в множество.
     * @param value - добавляемый объект
     */
    public void add(T value) {
        if (!contains(value)) {
            this.array.add(value);
        }
    }

    /**
     * Проверяет есть ли такой же объект в множестве.
     * @param value
     * @return boolean true если множество содержит такой объект.
     */
    public boolean contains(T value) {
        boolean result = false;
        for (T element : array) {
            if (Objects.equals(element, value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}
