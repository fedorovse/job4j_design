package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (isOutEmpty()) {
            if (isInEmpty()) {
                throw new NoSuchElementException();
            } else {
                while (!isInEmpty()) {
                    T buf = in.pop();
                    out.push(buf);
                }
            }
        }
        return out.pop();
    }

    public void push(T value) {
        if (isInEmpty()) {
            while (!isOutEmpty()) {
               T buf = out.pop();
               in.push(buf);
            }
        }
        in.push(value);
    }

    private boolean isInEmpty() {
//        boolean result = false;
        try {
            return in.pop() == null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isOutEmpty() {
//        return out.pop() == null;
//        boolean result = false;
        try {
            return out.pop() == null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
