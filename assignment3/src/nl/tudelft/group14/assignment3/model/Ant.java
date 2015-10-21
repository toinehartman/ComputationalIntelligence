package nl.tudelft.group14.assignment3.model;

import java.util.ArrayList;
import java.util.Stack;

public class Ant {

    private int x = 0;
    private int y = 0;
    private Stack<Block> route;
    private Maze maze;
    private Block[][] matrix;
    private ArrayList<Block> visited;
    
    public Ant (int x, int y, Maze maze)
    {
    	this.x = x;
    	this.y = y;
    	this.maze = maze;
    	route = new Stack<Block>();
    	visited = new ArrayList<Block>();
    	matrix = Maze.getMatrix();
    }
    
    public ArrayList<Block> cloneNeighbours(ArrayList<Block> neighbours) {
    	ArrayList<Block> result = new ArrayList<Block>();
    	for (Block b : neighbours)
    		result.add(b);
    	return result;
    }
    
    public void move() {
    	Block currentBlock = matrix[0][0];
    	visited.add(currentBlock);
    	while ((currentBlock.getX() != maze.getCols() - 1) && (currentBlock.getY() != maze.getRows() - 1)) {
	        ArrayList<Block> currentNeighbours = maze.getNeighbours(currentBlock);
	        ArrayList<Block> temp = cloneNeighbours(currentNeighbours);
	        int size = currentNeighbours.size();
	        for (int i = 0; i < size; i++) {
	        	if (visited.contains(temp.get(i)))
	        		currentNeighbours.remove(temp.get(i));
	        }
	        
	        if (currentNeighbours.size() > 0) {
		        currentBlock = decideDirection(currentNeighbours);
		        route.push(currentBlock);
		        if(!visited.contains(currentBlock))
		        	visited.add(currentBlock);
		        this.setX(currentBlock.getX());
		        this.setY(currentBlock.getY());
	        } else {
	        	currentBlock = route.pop();
	        	this.setX(currentBlock.getX());
		        this.setY(currentBlock.getY());
	        }
    	}
    	
    	float amountPheromone = 100 / route.size();
    	for (Block b : route) {
    		Floor f = (Floor)b;
    		f.setPheromone(f.getPheromone() + amountPheromone);
    		System.out.println("(" + f.getX() + ", " + f.getY() + ")");
    	}
    }
    
    public void moveTo(Block block) {
    	setX(block.getX());
    	setY(block.getY());
    }

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Floor decideDirection(ArrayList<Block> neighbours) {
    	float totalPheromone = 0;
    	
    	for (Block b : neighbours) {
    		Floor f = (Floor)b;
        	totalPheromone += f.getPheromone();
        }
    	
    	float random = (float)Math.random() * 100;
    	
    	for (Block b : neighbours) {
    		Floor f = (Floor)b;
    		float percentage = (f.getPheromone() / totalPheromone) * 100;
    		if ((random -= percentage) < 0) return f;
    	}	
    	
    	return null;
    }
}
