package nl.tudelft.group14.assignment3;

import nl.tudelft.group14.assignment3.model.Iteration;
import nl.tudelft.group14.assignment3.model.Maze;
import nl.tudelft.group14.assignment3.model.VisualizerPheromone;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Maze m;
        
        System.out.println("Loading matrix from file...");
        m = Maze.loadFile("medium");
        new VisualizerPheromone("Test", 300, 300, m.getRows(), m.getCols(), m).setVisible(true);
        System.out.println("Calculating route...");
        int max_iterations = 10000;
        for (int a = 0; a < max_iterations; a++) {
        	Iteration i = new Iteration(1);
        	i.iterate(m);
            if (a % (max_iterations / 100) == 0) System.out.println((float) a / max_iterations * 100 + "%");
        }

        System.out.println(String.format("%d %s", Iteration.shortest_route.size(), Iteration.shortest_route.toString()));
        System.exit(0);
    }
}
