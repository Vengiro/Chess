package GameManagment;

import gui.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private boolean Win = false;
    public static void main(String[] args) {
        launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameState gs = new GameState();
        Board board = new Board(gs);
        primaryStage.setScene(new Scene(board.pane()));
        primaryStage.show();

    }
}
