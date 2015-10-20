package nl.tudelft.group14.assignment3.model;

/**
 * Created by toinehartman on 20/10/15.
 *
 * @author toinehartman
 * @version 1.0
 * @since 20/10/15
 */
public class Pheromones extends Matrix<Float> {
    public Pheromones(int cols, int rows) {
        this.setMatrix(new Float[rows][cols]);
    }
}
