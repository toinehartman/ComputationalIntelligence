package nl.tudelft.group14.assignment3;

import nl.tudelft.group14.assignment3.model.Maze;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Maze m = Maze.loadFile("resources/hard maze.txt");
    }
}
