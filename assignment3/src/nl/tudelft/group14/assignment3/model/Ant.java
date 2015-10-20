package nl.tudelft.group14.assignment3.model;

import java.util.ArrayList;

public class Ant {

    private Coordinates c;
    private ArrayList<Coordinates> path;
    private Maze maze;
    
    public Ant (Coordinates c, Maze maze)
    {
        this.c = c;
        this.maze = maze;
        this.path = new ArrayList<>();
    }
    
    public void move() {
        
    }
    
    public void dropPheromone() {
        int length = path.size();
        float amount = 0;
        
        path.forEach((coord) -> {
            float ph = maze.getPheromones().get(coord);
            maze.getPheromones().set(ph + amount, coord);
        });
    }
}
