public class Cell {
    private boolean isAlive;

    /**
     * Default constructor that creates a new dead cell.
     *
     */
    public Cell() {
        this(false);
    }

    /**
     * Constructor that creates a new Cell with a specified living status.
     *
     * @param isAlive true if the new cell is alive, false if it is dead
     */
    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    /**
     * Accessor method that returns the current living status of the cell.
     *
     * @return true if the cell is alive, false if it is dead
     */
    public boolean getStatus() {
        return isAlive;
    }

    /**
     * Mutator method that updates the cell's status to be alive or dead.
     *
     * @param isAlive true to bring the cell to life, false to kill it
     */
    public void setStatus(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public String toString() {
        return (isAlive) ? "▓" : "░";
    }
}
