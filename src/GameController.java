import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class GameController extends Application {
    private final GameBoard board;
    private final Canvas canvas;
    private final GraphicsContext graphicsContext;

    /**
     * Default constructor that creates a new GameController that has a backing
     * GameBoard with the default number of rows (50) and columns (75). The
     * initial state of the GameBoard is randomized.
     *
     */
    public GameController() {
        board = new GameBoard();
        board.randomize();
        canvas = new Canvas(board.getXSize() * 10, board.getYSize() * 10);
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
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int x = 0; x < board.getXSize(); x++) {
            for (int y = 0; y < board.getYSize(); y++) {
                if (board.getBackingArray()[x][y].getStatus()) {
                    graphicsContext.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
    }
}
