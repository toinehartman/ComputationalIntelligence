package nl.tudelft.group14.assignment3.model;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Iteration {

	public static Stack<Block> shortest_route;

    private int antAmount;
    public static List<Ant> ants;

    private int[] start;
    private int[] end;
    
    public Iteration(int antAmount, int[] start, int[] end)
    {
        this.antAmount = antAmount;
        this.ants = new ArrayList<>();
        this.start = start;
        this.end = end;
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
			ants.add(new Ant(0, 0, maze, 1000, start, end));
    	}
    	
    	while (!isAllFinished()) {
    		for (Ant a : ants) {
				a.move();
			}
    	}
    	
    	for (Ant a : ants) {
            if (shortest_route == null || a.getRoute().size() < shortest_route.size()) {
                shortest_route = a.getRoute();
            }
			maze.applyPheromone(a);	
		}
    }
}
