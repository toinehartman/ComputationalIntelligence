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

    public boolean get(Coordinates c) {
        return matrix[c.getRow()][c.getCol()];
    }

    public void set(boolean value, Coordinates c) {
        matrix[c.getRow()][c.getCol()] = value;
    }

    @Override
    public String toString() {
        String s = "";

        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                Coordinates c = new Coordinates(col, row);
                s += get(c) ? 1 : 0;
                s += " ";
            }
            s += "\n";
        }

        return s;
    }
}
