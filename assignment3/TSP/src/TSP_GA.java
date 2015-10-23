/**
 * 
 * @author Christian
 *
 */

public class TSP_GA {

    public static void main(String[] args) {
        
        // Create some random products, in case of the ant algorithm, you have
        // to add them manually.
        Product product = new Product(60, 150);
        RouteManager.addProduct(product);
        Product product2 = new Product(180, 190);
        RouteManager.addProduct(product2);
        Product product3 = new Product(70, 170);
        RouteManager.addProduct(product3);
        Product product4 = new Product(140, 160);
        RouteManager.addProduct(product4);
        Product product5 = new Product(30, 80);
        RouteManager.addProduct(product5);
        Product product6 = new Product(90, 110);
        RouteManager.addProduct(product6);
        Product product7 = new Product(200, 60);
        RouteManager.addProduct(product7);
        Product product8 = new Product(130, 130);
        RouteManager.addProduct(product8);
        Product product9 = new Product(20, 100);
        RouteManager.addProduct(product9);
        Product product10 = new Product(110, 120);
        RouteManager.addProduct(product10);
        Product product11 = new Product(180, 200);
        RouteManager.addProduct(product11);
        Product product12 = new Product(50, 70);
        RouteManager.addProduct(product12);
        Product product13 = new Product(110, 70);
        RouteManager.addProduct(product13);
        Product product14 = new Product(190, 120);
        RouteManager.addProduct(product14);
        Product product15 = new Product(10, 60);
        RouteManager.addProduct(product15);
        Product product16 = new Product(100, 60);
        RouteManager.addProduct(product16);
        Product product17 = new Product(200, 60);
        RouteManager.addProduct(product17);
        Product product18 = new Product(0, 30);
        RouteManager.addProduct(product18);
        Product product19 = new Product(50, 30);
        RouteManager.addProduct(product19);
        Product product20 = new Product(140, 40);
        RouteManager.addProduct(product20);

        // Initialize population
        Population population = new Population(50, true);
        System.out.println("Initial distance of the route: " + population.getFittest().getDistance());

        // Evolve population over 100 generations
        population = GA.evolvePopulation(population);
        for (int i = 0; i < 100; i++) {
            population = GA.evolvePopulation(population);
        }

        // Print the results
        System.out.println("Evolving... :D");
        System.out.println("Final distance of the route: " + population.getFittest().getDistance());
        System.out.println("Chromosome:");
        System.out.println(population.getFittest());
    }
}