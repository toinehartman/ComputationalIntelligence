package nl.tudelft.group14.assignment3.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by toinehartman on 06/10/15.
 *
 * @author toinehartman
 * @version 1.0
 * @since 06/10/15
 */
public class Maze {
    private boolean[][] matrix;

    public Maze(int cols, int rows) {
        this.matrix = new boolean[rows][cols];
    }

    public int cols() {
        return matrix[0].length;
    }

    public int rows() {
        return matrix.length;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
        System.out.println(String.format("%d %d", matrix[0].length, matrix.length));
        System.out.println(String.format("%d %d", this.cols(), this.rows()));
    }

    public static Maze loadFile(String filename) throws FileNotFoundException {
        Scanner s = new Scanner(new FileInputStream(filename));

        int width = s.nextInt();
        int height = s.nextInt();

        boolean[][] result = new boolean[height][width];

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int value = s.nextInt();
                result[j][i] = (value == 1);
            }
        }

        Maze res = new Maze(width, height);
        res.setMatrix(result);

        s.close();

        return res;
    }

    public boolean get(int col, int row) {
        return matrix[row][col];
    }

    public void set(boolean value, int col, int row) {
        matrix[row][col] = value;
    }

    public int numberOfOptions(int col, int row) {
        int[][] range = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int numberOfOptions = 0;

        if (col < 0 || row < 0 || col >= this.matrix[0].length || row >= this.matrix.length)
            throw new IllegalArgumentException("Indices outside maze area are not allowed");

        for (int[] c : range) {
            int neighbour_col = col + c[0];
            int neighbour_row = row + c[1];

            if (neighbour_col >= 0 && neighbour_col < cols() &&
                    neighbour_row >= 0 && neighbour_row < rows()) {
                if (get(neighbour_col, neighbour_row)) {
                    // This square is accessible!
                    // TODO: save and return the location...
                    numberOfOptions++;
                }
            } else {
                // This square is outside the borders of the maze
            }
        }

        return numberOfOptions;
    }

    @Override
    public String toString() {
        String s = "";

        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                s += get(col, row) ? 1 : 0;
                s += " ";
            }
            s += "\n";
        }

        return s;
    }
}
