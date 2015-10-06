package nl.tudelft.group14.assignment3.model;

import java.util.ArrayList;

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
        this.matrix = new boolean[cols][rows];
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }
}
