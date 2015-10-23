public class Product {
    int x;
    int y;
    
    /**
     * Create a product at a random location between 0 and 200.
     */
    public Product(){
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
    }
    
    /**
     * Create a product a a specified location
     * @param x (X-coordinate of the product)
     * @param y (Y-coordinate of the product)
     */
    public Product(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Return the X coordinate of the product.
     * @return X coordinate of the product
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * Return the Y coordinate of the product.
     * @return Y coordinate of the product.
     */
    public int getY(){
        return this.y;
    }
    
    /**
     * Return the distance between two specified cities.
     * @param city
     * @return the distance between the two cities.
     */
    public double distanceTo(Product product){
        // For the ants problem, you should use the ACO algorithm instead of this.
        // Absolute value, because this.X may be smaller than product.X
        int xDistance = Math.abs(getX() - product.getX());
        int yDistance = Math.abs(getY() - product.getY());
        // Use Pythagoras' law to calculate the distance
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        
        return distance;
    }
    
    @Override
    public String toString(){
        return getX()+", "+getY();
    }
}