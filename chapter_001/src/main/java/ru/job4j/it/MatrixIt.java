package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (column < data[row].length) {
            result = true;
        } else if (row + 1 < data.length) {
            for (int i = row + 1; i < data.length; i++) {
                if (data[i].length > 0) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        int result = 0;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column < data[row].length) {
            result = data[row][column++];
        } else {
            for (int i = row + 1; i < data.length; i++) {
                if (data[i].length > 0) {
                    row = i;
                    column = 0;
                    result = data[row][column++];
                }
            }
        }
        return result;
    }
}
