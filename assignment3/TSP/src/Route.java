import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Christian
 *
 */

public class Route{

    // Dataset of the products (route).
    private ArrayList<Product> route = new ArrayList<>();
    
    // Initialize the fitness and distance of a rotue.
    private double fitness = 0;
    private int distance = 0;
    
    // Initialize a blank route (all values are null).
    public Route(){
        for (int i = 0; i < RouteManager.numberOfProducts(); i++) {
            route.add(null);
        }
    }
    
    /**
     * Generate a route with a dataset of products.
     * @param product
     */
    public Route(ArrayList<Product> product){
        this.route = product;
    }

    /**
     * Generate a random individual.
     */
    public void generateIndividual() {
        // Loop through all our destination products and add them to our route
        for (int i = 0; i < RouteManager.numberOfProducts(); i++) {
          setProduct(i, RouteManager.getProduct(i));
        }
        
        // Randomly reorder the products from the route
        Collections.shuffle(route);
    }

    /**
     * Get a specific product grom the route.
     * @param productPosition
     * @return
     */
    public Product getProduct(int productPosition) {
        return (Product)route.get(productPosition);
    }

    /**
     * Set a specific product in the route.
     * @param productPosition
     * @param product
     */
    public void setProduct(int productPosition, Product product) {
        route.set(productPosition, product);
        
        // If the products have been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }
    
    /**
     * Get the fitness of the route.
     * @return
     */
    public double getFitness() {
        if (fitness == 0) {
            // Fitness is distance^-1.
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }
    
    /**
     * Get the total distance of a route.
     * @return
     */
    public int getDistance(){
        // Check if distance has not yet been calculated.
        if (distance == 0) {
            int productDistance = 0;
            // Loop through our routes products.
            for (int i = 0; i < routeSize(); i++) {
                // Get product we come from
                Product fromProduct = getProduct(i);
                
                // Get the product we want to go to
                Product destinationProduct;
                
                // Check if we are not on the last product
                if(i + 1 < routeSize()){
                    destinationProduct = getProduct(i+1);
                }
                // If we are not, get the first product
                else{
                    destinationProduct = getProduct(0);
                }
                // Get the distance between to products and add it to
                // the distance of the route.
                productDistance += fromProduct.distanceTo(destinationProduct);
            }
            distance = productDistance;
        }
        return distance;
    }

    /**
     * Get the amount of products in our route.
     * @return
     */
    public int routeSize() {
        return route.size();
    }
    
    /**
     * Check if a route contains a product.
     * @param product
     * @return
     */
    public boolean containsProduct(Product product){
        return route.contains(product);
    }
    
    @Override
    public String toString() {
        // Visualize route like a chromosome
        String geneString = "|";
        for (int i = 0; i < routeSize(); i++) {
            geneString += getProduct(i)+"|";
        }
        return geneString;
    }
}