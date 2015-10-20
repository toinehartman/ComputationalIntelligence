package nl.tudelft.group14.assignment3.model;

/**
 * Created by toinehartman on 20/10/15.
 *
 * @author toinehartman
 * @version 1.0
 * @since 20/10/15
 */
public class Matrix<T> {
    private T[][] matrix;

    public Matrix(int cols, int rows) {
    }

    public int cols() {
        return matrix[0].length;
    }

    public int rows() {
        return matrix.length;
    }

    public T[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(T[][] matrix) {
        this.matrix = matrix;
    }

    public T get(Coordinates c) {
        return matrix[c.getRow()][c.getCol()];
    }

    public void set(T value, Coordinates c) {
        matrix[c.getRow()][c.getCol()] = value;
    }

    @Override
    public String toString() {
        String s = "";

        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                Coordinates c = new Coordinates(col, row);
                s += get(c);
                s += " ";
            }
            s += "\n";
        }

        return s;
    }
}
