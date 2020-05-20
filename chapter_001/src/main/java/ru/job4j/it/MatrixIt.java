package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
        if (data[row].length == 0) {
            findNextElement();
        }
    }

    private void findNextElement() {
        for (int i = row; i < data.length; i++) {
            if (column + 1 < data[i].length) {
                column++;
                break;
            } else if (i + 1 < data.length && data[i + 1].length > 0) {
                row = i + 1;
                column = 0;
                break;
            } else if (i == data.length - 1 && data[i].length == 0) {
                row = data.length - 1;
                column = data[i].length;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        int result = 0;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        result = data[row][column];
        findNextElement();
        return result;
    }
}
