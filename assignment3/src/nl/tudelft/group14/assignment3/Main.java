package nl.tudelft.group14.assignment3;

import nl.tudelft.group14.assignment3.model.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Main {

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
        
        System.out.println("Calculating route...");
        
        for (int a = 0; a < max_iterations; a++) {
        	Iteration i = new Iteration(NOants);
        	i.iterate(m);

            System.out.print("\r" + (((float) (a + 1) / max_iterations)) * 100f + "%");
        }

        System.out.println();

        try (FileOutputStream fos = new FileOutputStream("shortest_route_" + mazeName + ".txt");) {
            fos.write(Iteration.shortest_directions.getBytes());
        } catch (IOException e) {
            System.out.println(String.format("%s", Iteration.shortest_directions));
        }

        System.exit(0);
    }
}
