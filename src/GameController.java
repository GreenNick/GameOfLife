import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class GameController extends Application {
    private final int canvasWidth;
    private final int canvasHeight;
    private final GameBoard board;

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
    }

    /**
     * Main method that launches the game.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        GameController gameController = new GameController(100, 100);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
