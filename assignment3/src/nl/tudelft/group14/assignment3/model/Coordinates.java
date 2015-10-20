package nl.tudelft.group14.assignment3.model;

/**
 * Created by toinehartman on 20/10/15.
 *
 * @author toinehartman
 * @version 1.0
 * @since 20/10/15
 */
public class Coordinates {
    private int col;
    private int row;

    public Coordinates(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public Coordinates add(Coordinates other) {
        return new Coordinates(this.col + other.col,
                this.row + other.row);
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return String.format("{%d, %d}", this.col, this.row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (getCol() != that.getCol()) return false;
        return getRow() == that.getRow();

    }

    @Override
    public int hashCode() {
        int result = getCol();
        result = 31 * result + getRow();
        return result;
    }
}
