import java.util.Random;

public class GameBoard {
    private int xSize;
    private int ySize;
    private Cell[][] backingArray;
    private Random rand;

    /**
     * Default constructor that creates a new 500 by 500 GameBoard.
     *
     */
    public GameBoard() {
        this(500, 500);
    }

    /**
     * Constructor that creates a new GameBoard of a specified size.
     *
     * @param xSize number of cells in each row
     * @param ySize number of cells in each column
     */
    public GameBoard(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        backingArray = new Cell[xSize][ySize];
        rand = new Random();

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                backingArray[x][y] = new Cell();
            }
        }
    }

    /**
     * Constructor that creates a new GameBoard from a backingArray.
     *
     * @param backingArray initial array for the new GameBoard
     */
    public GameBoard(Cell[][] backingArray) {
        xSize = backingArray.length;
        ySize = backingArray[0].length;
        this.backingArray = backingArray;
        rand = new Random();
    }

    /**
     * Randomizes the living status of every cell in the backingArray. There is
     * a 50% chance that a given cell will be brought to life and a 50% chance
     * that the cell will be killed.
     *
     */
    public void randomize() {
        for (Cell[] row : backingArray) {
            for (Cell element : row) {
                element.setIsAlive(rand.nextInt(2) == 0);
            }
        }
    }

    /**
     * Accessor method that returns the horizontal size of the GameBoard.
     *
     * @return number of cells in each row
     */
    public int getXSize() {
        return xSize;
    }

    /**
     * Accessor method that returns the vertical size of the GameBoard.
     *
     * @return numbers of cells in each column
     */
    public int getYSize() {
        return ySize;
    }

    /**
     * Accessor method that returns the GameBoard's backingArray.
     *
     * @return the GameBoard's backingArray
     */
    public Cell[][] getBackingArray() {
        return backingArray;
    }

    /**
     * Mutator method that sets a new backingArray for the GameBoard.
     *
     * @param backingArray the GameBoard's new backingArray
     */
    public void setBackingArray(Cell[][] backingArray) {
        if (backingArray == null) {
            throw new IllegalArgumentException(
                    "Cannot set backingArray to null.");
        }

        this.backingArray = backingArray;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder((xSize * ySize) + ySize);

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                output.append(backingArray[x][y].toString());
            }

            output.append('\n');
        }

        return output.toString();
    }
}
