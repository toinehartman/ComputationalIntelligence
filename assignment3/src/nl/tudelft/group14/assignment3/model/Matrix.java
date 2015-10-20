package nl.tudelft.group14.assignment3.model;

/**
 * Created by toinehartman on 20/10/15.
 *
 * @author toinehartman
 * @version 1.0
 * @since 20/10/15
 */
public class Matrix {
    private boolean[][] matrix;

    public Matrix(int cols, int rows) {
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
    }

    public boolean get(int col, int row) {
        return matrix[row][col];
    }

    public void set(boolean value, int col, int row) {
        matrix[row][col] = value;
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
