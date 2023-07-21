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

    public Board() throws IOException {
        Rectangle rectangle = new Rectangle(100,100);
        rectangle.setFill(Color.BLUE);
        Text t = new Text(5, -15,"♟");
        t.setStyle("-fx-font-size: 90;");
        t.setTextOrigin(VPos.TOP);
        this.pane = new BorderPane(new Group(rectangle, t));


    }

    public Pane pane() {
        return pane;
    }

}
