package GameManagment;

import pawns.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {
    private final String[] board = new String[64];
    private final List<Pawns> pawns = new ArrayList<>();
    public static final String EMPTY = " ";

    public GameState() {
        initBoard();
    }

    public String[] getBoard() {
        return board;
    }

    public List<Pawns> getPawns() {
        return pawns;
    }

    public void updateBoard() {
        for (int i = 0; i < 64; i++) {
            board[i] = EMPTY;
        }
        for(Pawns pawn : pawns) {
            if(!pawn.isDead()) board[pawn.getPosition()] = pawn.getSymbol();
            else pawns.remove(pawn);
        }
    }
    private void initBoard() {
        for (int i = 0; i < 64; i++) {
            board[i] = EMPTY;
        }
        for (int i = 0; i < 8; i++) {
            pawns.add(new Pawn(true, i+8));
            pawns.add(new Pawn(false, i+48));
        }
        pawns.add(new Rook(true, 0));
        pawns.add(new Rook(true, 7));
        pawns.add(new Rook(false, 56));
        pawns.add(new Rook(false, 63));

        pawns.add(new Knight(true, 1));
        pawns.add(new Knight(true, 6));
        pawns.add(new Knight(false, 57));
        pawns.add(new Knight(false, 62));

        pawns.add(new Bishop(true, 2));
        pawns.add(new Bishop(true, 5));
        pawns.add(new Bishop(false, 58));
        pawns.add(new Bishop(false, 61));

        pawns.add(new Queen(true, 3));
        pawns.add(new Queen(false, 59));

        pawns.add(new King(true, 4));
        pawns.add(new King(false, 60));

        for(Pawns pawn : pawns) {
            board[pawn.getPosition()] = pawn.getSymbol();
        }
    }
}
