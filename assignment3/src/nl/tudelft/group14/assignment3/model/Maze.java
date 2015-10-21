package nl.tudelft.group14.assignment3.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by toinehartman on 06/10/15.
 *
 * @author toinehartman
 * @version 1.0
 * @since 06/10/15
 */
public class Maze {

	private static Block[][] matrix;
	
    public Maze(int cols, int rows) {
        matrix = new Block[cols][rows];
    }

    public static Maze loadFile(String filename) throws FileNotFoundException {
        Scanner s = new Scanner(new FileInputStream(filename));

        int cols = s.nextInt();
        int rows = s.nextInt();

        Maze maze = new Maze(cols, rows);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
            	int value = s.nextInt();
            	if (value == 1) {
            		matrix[col][row] = new Floor(col, row, 1);
            	} else {
            		matrix[col][row] = new Wall(col, row);
            	}
            }
        }

        s.close();

        return maze;
    }
    
    public int getCols() {
    	return matrix.length;
    }
    
    public int getRows() {
    	return matrix[0].length;
    }
    
    public Block getRight(Block block) {
    	int x = block.getX();
    	int y = block.getY();
    	Block result = null;
    	if (x < getCols() - 1) {
    		if (matrix[x + 1][y] instanceof Floor)
    			result = matrix[x + 1][y];
    	}
    	return result;
    }
    
    public Block getLeft(Block block) {
    	int x = block.getX();
    	int y = block.getY();
    	Block result = null;
    	if (x > 0) {
    		if (matrix[x - 1][y] instanceof Floor)
    			result = matrix[x - 1][y];
    	}
    	return result;
    }
    
    public Block getUp(Block block) {
    	int x = block.getX();
    	int y = block.getY();
    	Block result = null;
    	if (y > 0) {
    		if (matrix[x][y - 1] instanceof Floor)
    			result = matrix[x][y - 1];
    	}
    	return result;
    }
    
    public Block getDown(Block block) {
    	int x = block.getX();
    	int y = block.getY();
    	Block result = null;
    	if (y < getRows() - 1) {
    		if (matrix[x][y + 1] instanceof Floor)
    			result = matrix[x][y + 1];
    	}
    	return result;
    }
    
    public ArrayList<Block> getNeighbours(Block block) {
    	ArrayList<Block> neighbours = new ArrayList<Block>();
    	if (getRight(block) != null)
    		neighbours.add(getRight(block));
    	if (getLeft(block) != null)
    		neighbours.add(getLeft(block));
    	if (getUp(block) != null)	
    		neighbours.add(getUp(block)); 
    	if (getDown(block) != null)	
    		neighbours.add(getDown(block));
    	
    	return neighbours;
    }
    
    public String toString() {
        String s = "";

        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                if (matrix[col][row] instanceof Wall)
                	s += "Wall ";
                else
                	s += "Floor ";
            }
            s += "\n";
        }

        return s;
    }

	public static Block[][] getMatrix() {
		return matrix;
	}

	public static void setMatrix(Block[][] matrix) {
		Maze.matrix = matrix;
	}
}
