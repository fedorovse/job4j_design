package ru.job4j.collection;

import java.util.Iterator;

public class SimpleStack<T> {

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
}
