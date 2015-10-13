package nl.tudelft.group14.assignment3;

import nl.tudelft.group14.assignment3.model.Maze;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int col, row, options;
        Maze m;

        col = 4;
        row = 1;

        m = Maze.loadFile("resources/test maze.txt");
        options = m.numberOfOptions(col, row);

        System.out.println(String.format("Square <%d, %d> has %d options!", col, row, options));

    }
}
