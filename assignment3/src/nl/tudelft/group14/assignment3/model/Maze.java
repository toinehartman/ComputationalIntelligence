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
        
        int height = s.nextInt();
        int width = s.nextInt();

        boolean[][] result = new boolean[width][height];
        
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                int value = s.nextInt();
                result[j][i] = value == 1 ? true : false;
            }
        }
        
        Maze res = new Maze(height, width);
        res.setMatrix(result);
        
        s.close();
        
        return res;
    }
}
