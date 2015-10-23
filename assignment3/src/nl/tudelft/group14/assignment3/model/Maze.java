package nl.tudelft.group14.assignment3.model;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

/**
 * Created by toinehartman on 06/10/15.
 *
 * @author toinehartman
 * @version 1.0
 * @since 06/10/15
 */
public class Maze {

	private static Block[][] matrix;
	private float pheromone = 100f;
	private float evaporate = 0.1f;
	private Map<Stack<Block>, Float> pheromoneQueue; 
	public List<Point> wallPoints = new ArrayList<>();
	public List<Point> floorPoints = new ArrayList<>();

    private int[] start;
    private int[] end;
	
    public Maze(int cols, int rows, int[] start, int[] end, float pheromone, float evaporation) {
        matrix = new Block[cols][rows];
        pheromoneQueue = new HashMap<Stack<Block>, Float>();

        this.pheromone = pheromone;
        this.evaporate = evaporation;
        this.start = start;
        this.end = end;
    }

    public static Maze loadFile(String maze_name, float pheromone, float evaporation) throws FileNotFoundException {
        String maze_file = "resources/" + maze_name + " maze.txt";
        String coords_file = "resources/" + maze_name + " coordinates.txt";

        Scanner coord_scanner = new Scanner(new File(coords_file));
        coord_scanner.useDelimiter(Pattern.compile(",\\s|;\\s?"));

        System.out.println("Reading start and end coordinates...");
        int[] start = {Integer.parseInt(coord_scanner.next().replace('\n', '\0').trim()),
                Integer.parseInt(coord_scanner.next().replace('\n', '\0').trim())};
        int[] end = {Integer.parseInt(coord_scanner.next().replace('\n', '\0').trim()),
                Integer.parseInt(coord_scanner.next().replace('\n', '\0').trim())};

        Scanner maze_scanner = new Scanner(new File(maze_file));

        System.out.println("Reading maze dimensions...");
        int cols = maze_scanner.nextInt();
        int rows = maze_scanner.nextInt();

        Maze maze = new Maze(cols, rows, start, end, pheromone, evaporation);

        System.out.println("Reading maze values...");
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
            	int value = maze_scanner.nextInt();
            	if (value == 1) {
            		maze.floorPoints.add(new Point(col, row));
            		matrix[col][row] = new Floor(col, row, 1);
            	} else {
            		maze.wallPoints.add(new Point(col, row));
            		matrix[col][row] = new Wall(col, row);
            	}
            }
        }

        maze_scanner.close();

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
    
    public List<Block> getNeighbours(Block block) {
    	ArrayList<Block> neighbours = new ArrayList<Block>();
    	if (getRight(block) != null)
    		neighbours.add(getRight(block));
    	if (getLeft(block) != null)
    		neighbours.add(getLeft(block));
    	if (getUp(block) != null)	
    		neighbours.add(getUp(block)); 
    	if (getDown(block) != null)	
    		neighbours.add(getDown(block));
    	
    	return new CopyOnWriteArrayList<Block>(neighbours);
    }
    
    public String toStringAnt(Ant ant) {
        String s = "";

        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                if (col == ant.getX() && row == ant.getY())
                	s += "A";
                else if (matrix[col][row] instanceof Wall)
                	s += "0";
                else if (matrix[col][row] instanceof Floor)
                	s += "x";
            }
            s += "\n";
        }

        return s;
    }

    public void addPheromone(Stack<Block> route, Set<Block> pheromoneroute) {
    	float size = (float)route.size();
    	float pheromoneAmount = pheromone / size;
    	pheromoneQueue.put(route, pheromoneAmount);
    }
    
    public void applyPheromone(Ant a) {
    	evaporate();
    	
    	for (int p = 0; p < a.getRoute().size(); p++){
    		((Floor) a.getRoute().get(p)).setPheromone(a.getPheromonePerBlock());
    	}
    	
//    	for(Map.Entry<Stack<Block>, Float> blockFloatEntry : pheromoneQueue.entrySet()) {
//    		for(Block block : blockFloatEntry.getKey()) {
//    			Floor floor = (Floor) block;
//    			floor.setPheromone(floor.getPheromone() + blockFloatEntry.getValue());
//    		}
//    	}
//    	pheromoneQueue.clear();
    }
    
    public void evaporate() {
    	for (Point p : floorPoints) {
    		Floor f = (Floor)matrix[p.x][p.y];
    		f.setPheromone(f.getPheromone() * (1f - evaporate));
    	}
    }
    
	public static Block[][] getMatrix() {
		return matrix;
	}

	public static void setMatrix(Block[][] matrix) {
		Maze.matrix = matrix;
	}

    public int[] getStart() {
        return start;
    }

    public int[] getEnd() {
        return end;
    }
}
