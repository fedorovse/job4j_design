package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MatrixToFile {

    public static int[][] multiple(int size) {
        int[][] result = new int[size][size];
        for (int row = 0; row < result.length; row++) {
            for (int cell = 0; cell < result[row].length; cell++) {
                result[row][cell] = (row + 1) * (cell + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] rsl = multiple(9);
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int[] row : rsl) {
                for (int cell : row) {
                    out.write((cell + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
