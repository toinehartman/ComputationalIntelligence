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
	private float pheromone;
	private String routeDirections;

	private int[] start;
	private int[] end;

	public Stack<Block> getRoute() {
		return route;
	}

	public void setRoute(Stack<Block> route) {
		this.route = route;
	}

	public Ant (int x, int y, Maze maze, float pheromone, int[] start, int[] end) {
		this.x = x;
		this.y = y;
		this.maze = maze;
		route = new Stack<Block>();
		pheromoneroute = new HashSet<Block>();
		visited = new ArrayList<Block>();
		matrix = Maze.getMatrix();
		finished = false;
		currentBlock = matrix[start[0]][start[1]];
		this.pheromone = pheromone;
		routeDirections = maze.getStart()[0] + ", " + maze.getStart()[1] + ";\n";

		this.start = start;
		this.end = end;
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
		if (!finished) {
			Block oldCurrBlock = currentBlock;
			List<Block> currentNeighbours = getNeighbours(currentBlock);
			currentBlock = decideDirection(currentNeighbours);
			if (currentBlock == null) {
				System.out.println(String.format("%s, %s", currentNeighbours.toString(), oldCurrBlock.toString()));
				System.exit(0);
			}
			setX(currentBlock.getX());
			setY(currentBlock.getY());
			if (oldCurrBlock.getX() > currentBlock.getX())
			    routeDirections += "2;";
			else if (oldCurrBlock.getX() < currentBlock.getX())
			    routeDirections += "0;";
			else if (oldCurrBlock.getY() > currentBlock.getY())
			    routeDirections += "1;";
			else if (oldCurrBlock.getY() < currentBlock.getY())
			    routeDirections += "3;";
			
			route.push(currentBlock);
			visited.add(currentBlock);            
			pheromoneroute.add(currentBlock);
			if (x == this.end[0] && y == this.end[1]) {
				finished = true;
				routeDirections = route.size() + ";\n" + routeDirections;
//				System.out.println(routeDirections);
				//                System.out.println(route.toString());
			}
		}
	}


	public String getRouteDirections() {
        return routeDirections;
    }

    public void setRouteDirections(String routeDirections) {
        this.routeDirections = routeDirections;
    }

    public List<Block> getNeighbours(Block currentBlock){
		List<Block> currentNeighbours = maze.getNeighbours(currentBlock);

		if (currentNeighbours.size() > 1){

			for (Block b : currentNeighbours){			
				if (visited.size()>1){
					//				System.out.println(b.toString() + "  -  " + visited.get(visited.size()-1));
					if (b.equals(visited.get(visited.size()-2))){
						//					System.out.println(b.toString());
						currentNeighbours.remove(b);
					}
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

	public float getPheromonePerBlock(){
		float ppb;

		ppb = (this.pheromone / this.route.size());

		return ppb;

	}

	public Floor decideDirection(List<Block> neighbours) {
		float totalPheromone = 0;

		for (Block b : neighbours) {
			Floor f = (Floor)b;
			if (neighbours.size() == 3) {
			    
			}
			totalPheromone += f.getPheromone();
		}

		float random = (float)(Math.random());

		for (Block b : neighbours) {
			Floor f = (Floor)b;
			float percentage = (f.getPheromone() / totalPheromone);
			//    		System.out.println(random + " - " + percentage + "  "  + (random < percentage) + " -  x:" + f.getX() + " y:" + f.getY() + " size: " + neighbours.size());
			if ((random -= percentage) < 0 || neighbours.indexOf(b) == neighbours.size() - 1) return f;
		}

		return null;
	}
}
