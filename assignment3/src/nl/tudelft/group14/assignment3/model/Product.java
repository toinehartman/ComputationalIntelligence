package nl.tudelft.group14.assignment3.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by toinehartman on 23/10/15.
 *
 * @author toinehartman
 * @version 1.0
 * @since 23/10/15
 */
public class Product {
    private static final String FILENAME = "resources/tsp products.txt";

    private int id;
    private int x;
    private int y;

    private Product(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public static ArrayList<Product> loadProducts() {
        ArrayList<Product> products = new ArrayList<>();

        try (InputStream is = new FileInputStream(FILENAME)) {
            Scanner s = new Scanner(is);

            s.useDelimiter(Pattern.compile(":\\s|;|,\\s"));
            int len = Integer.parseInt(s.next());

            while (len > products.size()) {
                int id = Integer.parseInt(s.next().trim());
                int x = Integer.parseInt(s.next().trim());
                int y = Integer.parseInt(s.next().trim());

                Product p = new Product(id, x, y);
                products.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
