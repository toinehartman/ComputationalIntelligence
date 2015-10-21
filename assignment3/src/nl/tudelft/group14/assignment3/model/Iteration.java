package nl.tudelft.group14.assignment3.model;

public class Iteration {

    private int antAmount;
    
    public Iteration(int antAmount)
    {
        this.antAmount = antAmount;
    }
    
    public void iterate(Maze maze) {
    	for (int i = 0; i < antAmount; i++) {
    		Ant currentAnt = new Ant(0, 0, maze);
    		currentAnt.move();
    	}
    }
    
}
