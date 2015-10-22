package nl.tudelft.group14.assignment3.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Ant {

    private int x = 0;
    private int y = 0;
    private Block currentBlock;
    private Stack<Block> route;
    private Set<Block> pheromoneroute;
    private Maze maze;
    private Block[][] matrix;
    private ArrayList<Block> visited;
    private boolean finished;
    
    public Stack<Block> getRoute() {
		return route;
	}

	public void setRoute(Stack<Block> route) {
		this.route = route;
	}

	public Ant (int x, int y, Maze maze) {
		this.x = x;
		this.y = y;
		this.maze = maze;
		route = new Stack<Block>();
		pheromoneroute = new HashSet<Block>();
		visited = new ArrayList<Block>();
		matrix = Maze.getMatrix();
		finished = false;
		currentBlock = matrix[0][0];
	}
    
    public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void move() {
		if (!finished) {	
			List<Block> currentNeighbours = getNeighbours();
			currentBlock = decideDirection(currentNeighbours);		
	    	setX(currentBlock.getX());
	    	setY(currentBlock.getY());
	    	route.push(currentBlock);
	    	visited.add(currentBlock);	    	
	    	pheromoneroute.add(currentBlock);
	    	if (x == 24 && y == 14) {
	    		finished = true;
//	    		System.out.println(route.toString());
	    	}
		}
    }
    
	public List<Block> getNeighbours(){
		List<Block> currentNeighbours = maze.getNeighbours(currentBlock);
		
		for (Block b : currentNeighbours){			
			if (visited.size()>1){
//				System.out.println(b.toString() + "  -  " + visited.get(visited.size()-1));
				if (b.equals(visited.get(visited.size()-2))){
//					System.out.println(b.toString());
					currentNeighbours.remove(b);
				}
			}
		}
		
		return currentNeighbours;
		
	}
	
    public Set<Block> getPheromoneroute() {
		return pheromoneroute;
	}

	public void setPheromoneroute(Set<Block> pheromoneroute) {
		this.pheromoneroute = pheromoneroute;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Floor decideDirection(List<Block> neighbours) {
    	float totalPheromone = 0;
    	    	
    	for (Block b : neighbours) {
    		Floor f = (Floor)b;
        	totalPheromone += f.getPheromone();
        }
    	
    	float random = (float)(Math.random());
    	
    	for (Block b : neighbours) {
    		Floor f = (Floor)b;
    		float percentage = (f.getPheromone() / totalPheromone);
//    		System.out.println(random + " - " + percentage + "  "  + (random < percentage) + " -  x:" + f.getX() + " y:" + f.getY());
    		if ((random -= percentage) < 0) return f;
    	}	
    	
    	return null;
    }
}
