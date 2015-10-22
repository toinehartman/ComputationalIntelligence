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

	public List<Block> cloneNeighbours(List<Block> neighbours) {
        List<Block> result = new ArrayList<Block>();
        for (Block b : neighbours)
            result.add(b);
        return result;
    }
	
	public void move() {
        Block currentBlock = matrix[0][0];
        visited.add(currentBlock);
        while (x != 24 || y != 14) {
            List<Block> currentNeighbours = maze.getNeighbours(currentBlock);
            List<Block> temp = cloneNeighbours(currentNeighbours);
            int size = temp.size();

            for (int i = 0; i < size; i++) {
                if (visited.contains(temp.get(i)))
                    currentNeighbours.remove(temp.get(i));
            }
            System.out.println(currentNeighbours.size());            
            System.out.println(getX() + " - " + getY());
            
            if (currentNeighbours.size() > 0) {
                if (route.isEmpty()) {
                    visited.clear();
                    currentNeighbours = maze.getNeighbours(currentBlock);
                }
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
