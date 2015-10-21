package nl.tudelft.group14.assignment3;

import nl.tudelft.group14.assignment3.model.Iteration;
import nl.tudelft.group14.assignment3.model.Maze;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Maze m;
        Iteration i = new Iteration(50);

        System.out.println("Loading matrix from file...");
        m = Maze.loadFile("resources/easy maze.txt");
        i.iterate(m);
    }
}
