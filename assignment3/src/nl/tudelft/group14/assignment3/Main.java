package nl.tudelft.group14.assignment3;

import nl.tudelft.group14.assignment3.model.Iteration;
import nl.tudelft.group14.assignment3.model.Maze;
import nl.tudelft.group14.assignment3.model.Product;
import nl.tudelft.group14.assignment3.model.Route;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static Route route;

    public static void main(String[] args) throws FileNotFoundException {
        //set parameters
        float pheromone = 1000f;			//TODO needs implementation
        float evaporation = 0.95f;		//TODO needs implementation
        int max_iterations = 10;
        int NOants = 1;
        String mazeName = "easy";

        System.out.println("Loading products");
        List<Product> products = Product.loadProducts();
        System.out.println("Read " + products.size() + " products from file!");

        Route route = new Route();

        for (int i = -1; i < products.size(); i++) {
            Maze m = Maze.loadFile(mazeName, pheromone, evaporation);
            Iteration.shortest_route = null;

            if (i == -1) {
                // Route van start naar product 0
                Product p = products.get(0);

                System.out.println("From start to product " + p.getId());

                int [] product = {p.getX(), p.getY()};

                for (int a = 0; a < max_iterations; a++) {
                    Iteration it = new Iteration(NOants, m.getStart(), product);
                    it.iterate(m);

                    System.out.print("\r" + (((float) a) / max_iterations) * 100.f + "%");
                }
                System.out.print('\r');
                System.out.println("Shortest: " + Iteration.shortest_route.size());

                route.addAll(Iteration.shortest_route);

            } else if (i == products.size() - 1) {
                // Route van laatste naar end
                Product p = products.get(products.size() - 1);

                System.out.println("From product " + p.getId() + " to end");

                int[] product = {p.getX(), p.getY()};

                for (int a = 0; a < max_iterations; a++) {
                    Iteration it = new Iteration(NOants, product, m.getEnd());
                    it.iterate(m);

                    System.out.print("\r" + (((float) a) / max_iterations) * 100.f + "%");
                }
                System.out.print('\r');
                System.out.println("Shortest: " + Iteration.shortest_route.size());

                route.addPickup(p.getId());
                route.addAll(Iteration.shortest_route);

            } else {
                // Route van deze naar volgende
                Product p1 = products.get(i);
                Product p2 = products.get(i + 1);

                System.out.println("From product " + p1.getId() + " to product " + p2.getId());

                int[] product1 = {p1.getX(), p1.getX()};
                int[] product2 = {p2.getX(), p2.getX()};

                for (int a = 0; a < max_iterations; a++) {
                    Iteration it = new Iteration(NOants, product1, product2);
                    it.iterate(m);
                    System.out.print("\r" + (((float) a) / max_iterations) * 100.f + "%");
                }
                System.out.print('\r');
                System.out.println("Shortest: " + Iteration.shortest_route.size());

                route.addPickup(p1.getId());
                route.addAll(Iteration.shortest_route);
            }
        }

        System.out.println(route.size() + "" + route);
        System.exit(0);
    }
}
