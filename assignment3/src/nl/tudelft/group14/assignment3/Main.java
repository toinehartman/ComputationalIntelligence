package nl.tudelft.group14.assignment3;

import nl.tudelft.group14.assignment3.model.*;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static VisualizerPheromone grid;
    
    public static void main(String[] args) throws FileNotFoundException {
        Maze m;
        
        //set parameters
        float pheromone = 1000f;			//TODO needs implementation
        float evaporation = 0.5f;		//TODO needs implementation
        int max_iterations = 10;
        int NOants = 100;
        String mazeName = "medium";

        System.out.println("Loading products");
        List<Product> products = Product.loadProducts();
        System.out.println("Read " + products.size() + " products from file!");

        System.out.println("Loading matrix from file...");
        m = Maze.loadFile(mazeName, pheromone, evaporation);
        
        grid = new VisualizerPheromone("Test", 300, 300, m.getRows(), m.getCols(), m);
        grid.setVisible(true); 
        
        System.out.println("Calculating route...");
        
        int counter = 0;
        for (int a = 0; a < max_iterations; a++) {
        	Iteration i = new Iteration(NOants);
        	i.iterate(m);
        	counter++;
        	System.out.print("\r" + (float)((float)counter/(float)max_iterations)*100f + "%");
//            if (a % (max_iterations / 100) == 0) System.out.println((float) a / max_iterations * 100 + "%");
        }

        System.out.println(String.format("%s", Iteration.shortest_directions));
        System.exit(0);
    }
}
