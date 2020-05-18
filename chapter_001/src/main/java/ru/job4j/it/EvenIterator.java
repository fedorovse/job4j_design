package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public EvenIterator(int[] data) {
        this.data = data;
        for (int i = 0; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                point = i;
                break;
            } else {
                point = data.length;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        while (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[point];
        if (point + 1 < data.length) {
            for (int i = point + 1; i < data.length; i++) {
                if (data[i] % 2 == 0) {
                    point = i;
                    break;
                } else if (i + 1 == data.length) {
                    point = data.length;
                }
            }
        }
        return rsl;
    }
}
