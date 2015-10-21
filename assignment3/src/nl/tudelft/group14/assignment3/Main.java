package nl.tudelft.group14.assignment3;

import nl.tudelft.group14.assignment3.model.Iteration;
import nl.tudelft.group14.assignment3.model.Maze;
import nl.tudelft.group14.assignment3.model.VisualizerPheromone;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Maze m;
        
        System.out.println("Loading matrix from file...");
        m = Maze.loadFile("resources/easy maze.txt");
        new VisualizerPheromone("Test", 300, 300, m.getRows(), m.getCols(), m).setVisible(true);
        for (int a = 0; a < 1000; a++) {
        	Iteration i = new Iteration(10);
        	i.iterate(m);
        }
    }
}
