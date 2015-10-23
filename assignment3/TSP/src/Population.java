public class Population {

    // Dataset for all the routes in a population.
    Route[] routes;

    /**
     * Create a population
     * @param populationSize
     * @param initialise
     */
    public Population(int populationSize, boolean initialize) {
        routes = new Route[populationSize];
        // If the population has to be initialized, do so
        if (initialize) {
            // Generate routes and add theme to the population
            for (int i = 0; i < populationSize(); i++) {
                Route route = new Route();
                route.generateIndividual();
                // SaveRoute saves a specific route in the dataset of the population.
                saveRoute(i, route);
            }
        }
    }
    
    /**
     * Saves a route in the population dataset.
     * @param index
     * @param route
     */
    public void saveRoute(int index, Route route) {
        routes[index] = route;
    }
    
    /**
     * Gets a route from population
     * @param index
     * @return
     */
    public Route getRoute(int index) {
        return routes[index];
    }

    /**
     * Get the fittest route from the population.
     * @return
     */
    public Route getFittest() {
        // Initialize the fittest to the first route in the population
        Route fittest = routes[0];
        
        // For each route in population, check fitness and return the fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getRoute(i).getFitness()) {
                fittest = getRoute(i);
            }
        }
        return fittest;
    }

    /**
     * Return the size of the population.
     * @return
     */
    public int populationSize() {
        return routes.length;
    }
}