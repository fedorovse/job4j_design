package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable {

    private final Object[] tArray;
    private final int size;
    private final int DEFAULT_SIZE = 10;
    private int position = 0;

    public SimpleArray() {
        this.size = DEFAULT_SIZE;
        this.tArray = new Object[size];
    }

    public SimpleArray(int size) {
        this.tArray = new Object[size];
        this.size = size;
    }

    public boolean add(T model) {
        Objects.checkIndex(this.position, this.size);
        this.tArray[this.position++] = model;
        return true;
    }

    public T set(int index, T model) {
        T oldT = get(index);
        this.tArray[index] = model;
        return oldT;
    }

    public T remove(int index) {
        T removedT = this.get(index);
        if (this.position - 1 - index >= 0) {
            System.arraycopy(tArray, index + 1, tArray, index, this.position - 1 - index);
        }
        this.position--;
        return removedT;
    }

    public T get(int index) {
        Objects.checkIndex(index, this.position);
        return (T) this.tArray[index];
    }

    @Override
    public Iterator iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) tArray[index++];
            }
        };
    }
}
