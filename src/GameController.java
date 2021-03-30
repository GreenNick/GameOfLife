import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameController extends Application {
    private final int canvasWidth;
    private final int canvasHeight;
    private final GameBoard board;
    private final Canvas canvas;
    private final GraphicsContext graphicsContext;

    /**
     * Default Constructor that creates a new GameController with a backing
     * board that has 75 columns and 50 rows.
     *
     */
    public GameController() {
        this(75, 50);
    }

    /**
     * Constructor that creates a new GameController with a GameBoard of a
     * specified size.
     *
     * @param xSize the number of cells in each row of the board
     * @param ySize the number of cells in each column of the board.
     */
    public GameController(int xSize, int ySize) {
        canvasWidth = xSize * 10;
        canvasHeight = ySize * 10;
        board = new GameBoard(xSize, ySize);
        board.randomize();
        canvas = new Canvas(canvasWidth, canvasHeight);
        graphicsContext = canvas.getGraphicsContext2D();
    }

    /**
     * Main method that launches the game.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        root.getChildren().add(canvas);
        updateCanvas();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Private helper method that re-renders the canvas to reflect the current
     * state of the backing array.
     *
     */
    private void updateCanvas() {
        for (int x = 0; x < board.getXSize(); x++) {
            for (int y = 0; y < board.getYSize(); y++) {
                if (board.getBackingArray()[x][y].getStatus()) {
                    graphicsContext.setFill(Color.BLACK);
                } else {
                    graphicsContext.setFill(Color.WHITE);
                }

                graphicsContext.fillRect(x * 10, y * 10, 10, 10);
            }
        }
    }
}
