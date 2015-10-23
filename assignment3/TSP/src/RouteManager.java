import java.util.ArrayList;

public class RouteManager {

    /**
     * Create a static dataset to save the different products.
     */
    private static ArrayList<Product> destinationProducts = new ArrayList<>();

    /**
     * Add a products tot the dataset.
     * @param product
     */
    public static void addProduct(Product product) {
        destinationProducts.add(product);
    }
    
    /**
     * Return a product.
     * @param index
     * @return
     */
    public static Product getProduct(int index){
        return destinationProducts.get(index);
    }
    
    /**
     * Return the amount of products in the dataset.
     * @return
     */
    public static int numberOfProducts(){
        return destinationProducts.size();
    }
}