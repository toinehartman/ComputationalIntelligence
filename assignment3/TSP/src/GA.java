/**
 * 
 * @author Christian
 *
 */

public class GA {

    // Parameters of the genetic algorithm
    private static final double mutationRate = 0.01;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // Evolves a certain population over one generation
    public static Population evolvePopulation(Population population) {
        Population newPopulation = new Population(population.populationSize(), false);

        // Keep the best individual from the population if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            // Save the route of the fittest individual from the population.
            newPopulation.saveRoute(0, population.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            Route parent1 = tournamentSelection(population);
            Route parent2 = tournamentSelection(population);
            // Crossover parents
            Route child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveRoute(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getRoute(i));
        }

        return newPopulation;
    }

    /**
     * Apply crossover to two "parents" and generate a "child"
     * @param parent1
     * @param parent2
     * @return
     */
    public static Route crossover(Route parent1, Route parent2) {
        // Initialize new child route
        Route child = new Route();

        // Get start and end subpositions from parent 1
        int startPos = (int) (Math.random() * parent1.routeSize());
        int endPos = (int) (Math.random() * parent1.routeSize());

        // Add the subposition from parent1 to the child
        for (int i = 0; i < child.routeSize(); i++) {
            // Check if the start position is smaller than the end position.
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setProduct(i, parent1.getProduct(i));
            } // Check if the end position is smaller than the start position.
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setProduct(i, parent1.getProduct(i));
                }
            }
        }

        // Loop through the products of parent2
        for (int i = 0; i < parent2.routeSize(); i++) {
            // Add the product if the child does not have it.
            if (!child.containsProduct(parent2.getProduct(i))) {
                // Find unused spaces in the child's route to add products
                for (int ii = 0; ii < child.routeSize(); ii++) {
                    // Add product if an unused space has been found in the
                    // child's route.
                    if (child.getProduct(ii) == null) {
                        child.setProduct(ii, parent2.getProduct(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    /**
     * Apply mutation to a certain tour.
     * @param tour
     */
    private static void mutate(Route route) {
        // Loop through the products in the route
        for(int i = 0; i < route.routeSize(); i++){
            // Calculate if mutation should be used
            if(Math.random() < mutationRate){
                // Get a second random space (other is i) in the route to mutate
                int j = (int)(route.routeSize() * Math.random());

                // Get the products at i and j
                Product product1 = route.getProduct(i);
                Product product2 = route.getProduct(j);

                // Swap the two products to complete the mutation
                route.setProduct(j, product1);
                route.setProduct(i, product2);
            }
        }
    }

    /**
     * Select Routes for crossover
     * @param population
     * @return
     */
    private static Route tournamentSelection(Population population) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // Get a random Route and add it to the tournament population
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int)(Math.random() * population.populationSize());
            tournament.saveRoute(i, population.getRoute(randomId));
        }
        // Return the fittest route from the tournament as candidate for crossover
        Route fittest = tournament.getFittest();
        return fittest;
    }
}