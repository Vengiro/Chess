package gui;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public final class Board {

    private final BorderPane pane;

    private final int CELL_SIZE = 100;


    public Board() throws IOException {
        Rectangle rectangle = new Rectangle(100,100);
        rectangle.setFill(Color.BLUE);
        Text t = new Text(5, -15,"♟");
        t.setStyle("-fx-font-size: 90;");
        t.setTextOrigin(VPos.TOP);
        Text ta = new Text(5, -15,"♔");
        ta.setStyle("-fx-font-size: 90;");
        ta.setTextOrigin(VPos.TOP);
        Group g = new Group(rectangle, ta);
        Group g2 = new Group(rectangle, t);
        g2.setTranslateX(100);
        Group g3 = new Group(g, g2);

        this.pane = new BorderPane(g3);


    }

    public Pane pane() {
        return pane;
    }

    private Group RowBuilder(int row) {
        Group g = new Group();
        for (int i = 0; i < 8; i++) {
            Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE);
            if ((i + row) % 2 == 0) rectangle.setFill(Color.BLACK);
            else rectangle.setFill(Color.WHITE);
            Text t = new Text(5, -15, "♟");
            t.setStyle("-fx-font-size: 90;");
            t.setTextOrigin(VPos.TOP);
            Group g2 = new Group(rectangle, t);
            g2.setTranslateX(i * CELL_SIZE);
            g.getChildren().add(g2);
        }
        return g;
    }

}
