package nl.tudelft.group14.assignment3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Iteration {

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
			ants.add(new Ant(0, 0, maze, 1000));
    	}
    	
    	while (!isAllFinished()) {
    		for (Ant a : ants) {
				a.move();
			}
    	}
    	
    	for (Ant a : ants) {
			System.out.println(a.getRoute().size());
			maze.applyPheromone(a);	
		}
    	//maze.applyPheromone();	
    }    
}
