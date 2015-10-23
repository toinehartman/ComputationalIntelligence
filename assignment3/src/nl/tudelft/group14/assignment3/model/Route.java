package nl.tudelft.group14.assignment3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by toinehartman on 23/10/15.
 *
 * @author toinehartman
 * @version 1.0
 * @since 23/10/15
 */
public class Route extends Stack<Block> {
    private String directions;
    public List<Entry> route;

    public Route() {
        this.directions = "";
        this.route = new ArrayList<>();
    }

    public String getDirections() {
        return this.directions;
    }

    public void setLengthAndRoute(int length) {
        this.directions = length + ";\n" + directions;

        route.forEach(step -> directions += step.show());
    }

    public void addStart(int x, int y) {
        route.add(new Start(x, y));
    }

    public void addDirection(int dir) {
        route.add(new Direction(dir));
    }

    public void addPickup(int number) {
        route.add(new Pickup(number));
    }

    abstract class Entry {
        public abstract String show();
    }

    class Pickup extends Entry {

        private int number;

        public Pickup(int number) {
            this.number = number;
        }

        @Override
        public String show() {
            return String.format("take product #%d;\n", number);
        }
    }

    class Direction extends Entry {

        private int direction;

        public Direction(int direction) {
            this.direction = direction;
        }

        @Override
        public String show() {
            return String.format("%d;\n", direction);
        }
    }

    class Start extends Entry {

        private int x;
        private int y;

        public Start(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String show() {
            return String.format("%d, %d;\n", x, y);
        }
    }
}
