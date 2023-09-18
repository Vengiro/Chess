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
import pawns.Pawn;
import pawns.Pawns;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Board {

    private final GameState gameState;
    private final BorderPane pane;
    private int lastSelected = -1;
    private int selected = -1;

    private static final double CELL_SIZE = 80;

    private final Map<Text, Integer> map = new HashMap<>();




    public Board(GameState gs) throws IOException {
        this.gameState = gs;
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



                if ((x + y) % 2 == 0) rectangle.setFill(Color.BROWN);
                else rectangle.setFill(Color.WHITE);

                Text t = new Text(CELL_SIZE/20, -3*CELL_SIZE/20, rowContent[x + y * 8].get());
                map.put(t, x + y * 8);
                t.setStyle("-fx-font-size: "+(0.9d*CELL_SIZE)+";");
                t.setTextOrigin(VPos.TOP);
                t.setOnMouseClicked(e -> {
                    lastSelected = selected;
                    selected = map.get(t);
                    //reset color
                    if(lastSelected != -1){
                        Group lastGroup = (Group) g.getChildren().get(lastSelected);
                        Text lastText = (Text) lastGroup.getChildren().get(1);
                        Rectangle lastRectangle = (Rectangle) lastGroup.getChildren().get(0);
                        if ((lastSelected + lastSelected / 8) % 2 == 0) {
                            lastRectangle.setFill(Color.BROWN);
                        } else {
                            lastRectangle.setFill(Color.WHITE);
                        }
                        //move pawn
                        if(!lastText.textProperty().get().equals(GameState.EMPTY) &&
                          pawns.get(lastSelected).canMove(selected) &&
                          (pawns.get(lastSelected).isWhite() ^ gameState.isTurnEven()) &&
                          !isThereObstacle(lastSelected, selected, pawns.get(lastSelected).isWhite())){

                            if(pawns.get(lastSelected) instanceof Pawn) {

                                if (pawns.get(selected) != null &&
                                ((Pawn) pawns.get(lastSelected)).goDiagonal(selected)) {
                                    moveAndUpdate(pawns, lastSelected, selected, lastText, t);
                                }
                                else if(pawns.get(selected) == null &&
                                        !((Pawn) pawns.get(lastSelected)).goDiagonal(selected)){
                                    moveAndUpdate(pawns, lastSelected, selected, lastText, t);
                                }
                            }
                            else {
                                moveAndUpdate(pawns, lastSelected, selected, lastText, t);
                            }
                        }
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


    private boolean isThereObstacle(int pos, int des, boolean isWhite){


        Map<Integer, Pawns> pawns = gameState.getPawnsAndPos();

        if((des - pos)% 8 ==0){
            if(des > pos) {
                for (int i = pos + 8; i <= des; i += 8) {
                    if (pawns.get(i) != null && pawns.get(i).isWhite() == isWhite) {
                        return true;
                    }
                }
            }
            else{
                for(int i = pos - 8; des <= i; i-=8) {
                    if (pawns.get(i) != null && pawns.get(i).isWhite() == isWhite) {
                        return true;
                    }
                }
            }
        }
        else if((des - pos)% 9 ==0){
            if(des > pos) {
                for (int i = pos + 9; i <= des; i += 9) {
                    if (pawns.get(i) != null && pawns.get(i).isWhite() == isWhite) {
                        return true;
                    }
                }
            }
            else{
                for(int i = pos - 9; des <= i; i-=9) {
                    if (pawns.get(i) != null && pawns.get(i).isWhite() == isWhite) {
                        return true;
                    }
                }
            }
        }
        else if(((des %8 == 0 && des + 7 == pos)|| (des %8 == 7 && des - 7 == pos))) {
            if(des > pos) {
                for (int i = pos + 1; i <= des; i += 1) {
                    if (pawns.get(i) != null && pawns.get(i).isWhite() == isWhite) {
                        return true;
                    }
                }
            }
            else{
                for(int i = pos - 1; des <= i; i-=1) {
                    if (pawns.get(i) != null && pawns.get(i).isWhite() == isWhite) {
                        return true;
                    }
                }
            }
        }
        else if((des - pos)% 7 ==0 ){
            if(des > pos) {
                for (int i = pos + 7; i <= des; i += 7) {
                    if (pawns.get(i) != null && pawns.get(i).isWhite() == isWhite) {
                        return true;
                    }
                }
            }
            else{
                for(int i = pos - 7; des <= i; i-=7) {
                    if (pawns.get(i) != null && pawns.get(i).isWhite() == isWhite) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void moveAndUpdate(Map<Integer, Pawns> pawns, int lastSelected, int selected, Text lastText, Text t){
        pawns.get(lastSelected).move(selected);
        gameState.nextTurn();

        //kill pawn
        if(pawns.get(selected) != null){
            pawns.get(selected).die();
        }
        gameState.updateBoard();
        lastText.textProperty().setValue(gameState.getBoard()[lastSelected]);
        t.textProperty().setValue(gameState.getBoard()[selected]);

    }

}
