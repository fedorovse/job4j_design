package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {

    private SimpleArray<T> array = new SimpleArray<T>();

    public void add(T value) {
        if (!contains(value)) {
            this.array.add(value);
        }
    }



    public boolean contains(T value) {
        boolean result = false;
        Iterator<T> it = this.array.iterator();
        while (it.hasNext()) {
            if (it.next().equals(value)) {
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
