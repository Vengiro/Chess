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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public final class Board {

    private final GameState gameState = new GameState();
    private final BorderPane pane;
    private int lastSelected = -1;
    private int selected = -1;

    private static final double CELL_SIZE = 80;

    private final Map<Text, Integer> map = new HashMap<>();




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

        HashMap<Integer, Pawns> pawns =  (HashMap<Integer, Pawns>) gameState.getPawnsAndPos();
        Group g = new Group();
        for(int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE);

                /*rectangle.setOnMouseClicked(e -> {
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
                            pawns.sort(Comparator.comparingInt(Pawns::getPosition));

                            for (int i = 0; i < 64; i++) {
                                rowContent[i].setValue(gameState.getBoard()[i]);
                            }
                        }
                    }
                    System.out.println(selected);
                    System.out.println(lastSelected);
                    rectangle.setFill(Color.RED);



                });*/

                if ((x + y) % 2 == 0) rectangle.setFill(Color.BROWN);
                else rectangle.setFill(Color.WHITE);

                Text t = new Text(CELL_SIZE/20, -3*CELL_SIZE/20, rowContent[x + y * 8].get());
                map.put(t, x + y * 8);
                t.setStyle("-fx-font-size: "+(0.9d*CELL_SIZE)+";");
                t.setTextOrigin(VPos.TOP);
                t.setOnMouseClicked(e -> {
                    lastSelected = selected;
                    selected = map.get(t);
                    if(lastSelected != -1){
                        Group lastGroup = (Group) g.getChildren().get(lastSelected);
                        Text lastText = (Text) lastGroup.getChildren().get(1);
                        Rectangle lastRectangle = (Rectangle) lastGroup.getChildren().get(0);
                        if ((lastSelected + lastSelected / 8) % 2 == 0) {
                            lastRectangle.setFill(Color.BROWN);
                        } else {
                            lastRectangle.setFill(Color.WHITE);
                        }

                        if(!lastText.textProperty().get().equals(GameState.EMPTY) &&
                            pawns.get(lastSelected).move(selected)){
                            if(pawns.get(selected) != null && lastSelected != -1) {
                                pawns.get(selected).die();
                            }
                            gameState.updateBoard();
                            lastText.textProperty().setValue(gameState.getBoard()[lastSelected]);
                            t.textProperty().setValue(gameState.getBoard()[selected]);
                        }
                    }
                    rectangle.setFill(Color.RED);

                });
                /*for (int i = 0; i < 64; i++) {
                    rowContent[i].addListener((observable, oldValue, newValue) -> {
                        t.setText(newValue);
                    });

                }*/

                Group g1 = new Group(rectangle, t);
                g1.setTranslateX(x * CELL_SIZE);
                g1.setTranslateY(y * CELL_SIZE);
                g.getChildren().add(g1);
            }
        }
        return g;
    }

}
