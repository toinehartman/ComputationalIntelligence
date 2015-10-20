package nl.tudelft.group14.assignment3.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by toinehartman on 06/10/15.
 *
 * @author toinehartman
 * @version 1.0
 * @since 06/10/15
 */
public class Maze extends Matrix<Boolean> {

    private Pheromones pheromones;

    public Maze(int cols, int rows) {
        this.setMatrix(new Boolean[rows][cols]);
        this.pheromones = new Pheromones(cols, rows);
    }

    public static Maze loadFile(String filename) throws FileNotFoundException {
        Scanner s = new Scanner(new FileInputStream(filename));

        int cols = s.nextInt();
        int rows = s.nextInt();

        Maze maze = new Maze(cols, rows);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Coordinates c = new Coordinates(col, row);
                int value = s.nextInt();
                maze.set((value == 1), c);
                maze.getPheromones().set((value == 1) ? 1.f : 0.f, c);
            }
        }

        s.close();

        return maze;
    }

    public int numberOfOptions(Coordinates c) {
        Coordinates[] range = {new Coordinates(-1, 0),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1)};
        int numberOfOptions = 0;

        if (c.getCol() < 0 || c.getRow() < 0 || c.getCol() >= this.cols() || c.getRow() >= this.rows())
            throw new IllegalArgumentException("Indices outside maze area are not allowed");

        for (Coordinates r : range) {
            Coordinates neighbour = c.add(r);

            if (neighbour.getCol() >= 0 && neighbour.getCol() < cols() &&
                    neighbour.getRow() >= 0 && neighbour.getRow() < rows()) {
                if (get(neighbour)) {
                    // This square is accessible!
                    // TODO: save and return the location...
                    numberOfOptions++;
                }
            } else {
                // This square is outside the borders of the maze
            }
        }

        return numberOfOptions;
    }

    @Override
    public String toString() {
        String s = "";

        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                Coordinates c = new Coordinates(col, row);
                s += get(c) ? 1 : 0;
                s += " ";
            }
            s += "\n";
        }

        return s;
    }

    public Pheromones getPheromones() {
        return pheromones;
    }

    public void setPheromones(Pheromones pheromones) {
        this.pheromones = pheromones;
    }

    public void setPheromone(float value, Coordinates c) {
        this.pheromones.set(value, c);
    }
}
