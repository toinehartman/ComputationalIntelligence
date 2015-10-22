package nl.tudelft.group14.assignment3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Iteration {

	public static Stack<Block> shortest_route;
	public static String shortest_directions;

    private int antAmount;
    ArrayList<Set<Block>> routes;
    List<Ant> ants;
    
    public Iteration(int antAmount)
    {
        this.antAmount = antAmount;
        this.ants = new ArrayList<>();
    }
    
    public boolean isAllFinished() {
    	for (Ant a : ants) {
    		if (!a.isFinished())
    			return false;
    	}
    	return true;
    }
    
    public void iterate(Maze maze) {
    	for (int i = 0; i < antAmount; i++) {
			ants.add(new Ant(0, 0, maze, 1000, maze.getStart(), maze.getEnd()));
    	}
    	
    	while (!isAllFinished()) {
    		for (Ant a : ants) {
				a.move();
//				System.out.println(maze.toStringAnt(a));
			}
    	}
    	
    	for (Ant a : ants) {
            if (shortest_route == null || a.getRoute().size() < shortest_route.size()) {
//                System.out.println(a.getRoute().size());
                shortest_route = a.getRoute();
                shortest_directions = a.getRouteDirections();
            }
			maze.applyPheromone(a);	
		}
    	//maze.applyPheromone();	
    }    
}
