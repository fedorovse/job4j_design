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
        while (row < data.length) {
            if (data[row].length == 0 || column == data[row].length) {
                row++;
                column = 0;
            } else if (column < data[row].length) {
                break;
            }
        }
        return row < data.length && column < data[row].length;
    }

    @Override
    public Integer next() {
        int result = 0;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        result = data[row][column++];
        return result;
    }
}
