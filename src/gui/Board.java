package gui;

import GameManagment.GameState;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import pawns.Pawns;

import java.io.IOException;
import java.util.ArrayList;

public final class Board {

    private final GameState gameState = new GameState();
    private final BorderPane pane;
    private int lastSelected = -1;
    private int selected = -1;

    private static final double CELL_SIZE = 80;



    public Board() throws IOException {
        Group g = BoardBuilder();
        this.pane = new BorderPane(g);


    }

    public Pane pane() {
        return pane;
    }


    private Group BoardBuilder() {
        StringProperty[] rowContent = new SimpleStringProperty[gameState.getBoard().length];
        for (int i = 0; i < 64; i++) {
            rowContent[i] = new SimpleStringProperty(gameState.getBoard()[i]);

        }
        ArrayList<Pawns> pawns =  (ArrayList<Pawns>) gameState.getPawns();
        Group g = new Group();
        for(int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE);

                rectangle.setOnMouseClicked(e -> {
                    lastSelected = selected;

                    for (int i = 0; i < 64; i++) {
                        Group inGroup = (Group) g.getChildren().get(i);
                        if(inGroup.getChildren().contains(rectangle)) selected = i;
                        Rectangle inRectangle = (Rectangle) inGroup.getChildren().get(0);
                        if ((i + i/8)% 2 == 0) inRectangle.setFill(Color.BROWN);
                        else inRectangle.setFill(Color.WHITE);
                    }
                    if(lastSelected != -1) {
                        if(!(rowContent[lastSelected].get().equals(GameState.EMPTY))) {
                            pawns.get(lastSelected).move(selected);
                            gameState.updateBoard();
                            for (int i = 0; i < 64; i++) {
                                rowContent[i].setValue(gameState.getBoard()[i]);
                            }
                        }
                    }
                    System.out.println(selected);
                    System.out.println(lastSelected);
                    rectangle.setFill(Color.RED);



                });

                if ((x + y) % 2 == 0) rectangle.setFill(Color.BROWN);
                else rectangle.setFill(Color.WHITE);
                StringProperty s = rowContent[x + y * 8];


                Text t = new Text(CELL_SIZE/20, -3*CELL_SIZE/20, s.get());
                s.addListener((observable, oldValue, newValue) -> {
                    t.textProperty().setValue(newValue);
                });
                t.setStyle("-fx-font-size: "+(0.9d*CELL_SIZE)+";");
                t.setTextOrigin(VPos.TOP);
                t.setOnMouseClicked(e -> {
                    for (int i = 0; i < 64; i++) {
                        Group inGroup = (Group) g.getChildren().get(i);
                        if(inGroup.getChildren().contains(rectangle)) selected = i;
                        Rectangle inRectangle = (Rectangle) inGroup.getChildren().get(0);
                        if ((i + i / 8) % 2 == 0) inRectangle.setFill(Color.BROWN);
                        else inRectangle.setFill(Color.WHITE);
                    }
                    rectangle.setFill(Color.RED);
                });
                Group g1 = new Group(rectangle, t);
                g1.setTranslateX(x * CELL_SIZE);
                g1.setTranslateY(y * CELL_SIZE);
                g.getChildren().add(g1);
            }
        }
        return g;
    }

}
