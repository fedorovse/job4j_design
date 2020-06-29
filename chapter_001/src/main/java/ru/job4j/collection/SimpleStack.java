package ru.job4j.collection;

import java.util.Iterator;

/**
 * Реализован стэк на односвязном списке
 * @author Fedorov Sergey email: ingor-ru@mail.ru
 * @since 26.06.2020
 * @version 1
 * @param <T>
 */
public class SimpleStack<T> {
    /**
     * Односвязный список для хранения стэка
     */
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        T result = null;
        Iterator<T> it = linked.iterator();
        while (it.hasNext()) {
            result = it.next();
        }
        linked.deleteLast();
        return result;
    }

    public void push(T value) {
        linked.add(value);
    }

    public T popFirst() {
        return linked.deleteFirst();
    }

    public void pushFirst(T value) {
        linked.addFirst(value);
    }

}
