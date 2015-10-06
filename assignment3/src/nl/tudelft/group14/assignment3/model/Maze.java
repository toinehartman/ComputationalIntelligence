package nl.tudelft.group14.assignment3.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
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
        this.matrix = new boolean[cols][rows];
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    public static Maze loadFile(String filename) throws FileNotFoundException {
        Scanner s = new Scanner(new FileInputStream(filename));

        while (s.hasNextInt()) {
            System.out.println(s.nextInt());
        }

        return null;
    }
}
