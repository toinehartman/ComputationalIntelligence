package nl.tudelft.group14.assignment3;

import nl.tudelft.group14.assignment3.model.Maze;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Maze m;

        System.out.println("Loading matrix from file...");
        m = Maze.loadFile("resources/easy maze.txt");

        System.out.println(String.format("Matrix has %d cols, %d rows", m.cols(), m.rows()));

        System.out.println("Finished!");
        System.out.print(m);
    }
}
