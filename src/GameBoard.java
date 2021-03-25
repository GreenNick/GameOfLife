import java.util.Random;

public class GameBoard {
    private final int xSize;
    private final int ySize;
    private final Random rand;
    private Cell[][] backingArray;

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
                element.setStatus(rand.nextInt(2) == 0);
            }
        }
    }

    /**
     * Replaces the current backingArray with a new backingArray containing
     * updated cells corresponding to the GameBoard's next state. Cells are
     * updated according to the rules of Conway's Game of Life. The GameBoard is
     * a torus. Cells at the top of the board neighbor cells at the bottom, and
     * cells at the right of the board neighbor cells at the left.
     *
     * Rules:
     * 1. Any live cell with fewer than two live neighbors dies.
     * 2. Any live cell with more than three live neighbors dies.
     * 3. Any live cell with two or three live neighbors lives.
     * 4. Any dead cell with exactly three live neighbors will come to life.
     *
     */
    public void updateBoardState() {
        Cell[][] nextBoard = new Cell[xSize][ySize];

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                int numNeighbors = 0;

                // Check top
                numNeighbors += checkCell(x, (y + 1) % ySize);
                // Check top-right
                numNeighbors += checkCell((x + 1) % xSize, (y + 1) % ySize);
                // Check right
                numNeighbors += checkCell((x + 1) % xSize, y);
                // Check bottom-right
                numNeighbors += checkCell((x + 1) % xSize, (y - 1) % ySize);
                // Check bottom
                numNeighbors += checkCell(x, (y - 1) % ySize);
                // Check bottom-left
                numNeighbors += checkCell((x - 1) % xSize, (y - 1) % ySize);
                // Check left
                numNeighbors += checkCell((x - 1) % xSize, (y) % ySize);
                // Check top-left
                numNeighbors += checkCell((x - 1) % xSize, (y + 1) % ySize);

                if (backingArray[x][y].getStatus() && (numNeighbors < 2)) {
                    nextBoard[x][y] = new Cell(false);
                } else if (backingArray[x][y].getStatus()
                        && (numNeighbors > 3)) {
                    nextBoard[x][y] = new Cell(false);
                } else if (backingArray[x][y].getStatus()) {
                    nextBoard[x][y] = new Cell(true);
                } else if (!backingArray[x][y].getStatus()
                        && (numNeighbors == 3)) {
                    nextBoard[x][y] = new Cell(true);
                } else {
                    nextBoard[x][y] = new Cell(false);
                }
            }
        }

        backingArray = nextBoard;
    }

    /**
     * Helper method that checks if the specified cell in the backingArray is
     * alive or not and returns 1 or 0. Used to help calculate the number of
     * living neighbors around a cell.
     *
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @return 1 if the cell is alive, 0 if it is dead
     */
    private int checkCell(int x, int y) {
        if (x == -1) {
            x = xSize - 1;
        }

        if (y == -1) {
            y = ySize - 1;
        }

        return (backingArray[x][y].getStatus()) ? 1 : 0;
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
