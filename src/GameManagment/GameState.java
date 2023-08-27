package GameManagment;

import pawns.*;

import java.util.*;

public class GameState {
    private final String[] board = new String[64];
    //Map<position, pawn>
    private final Map< Integer ,Pawns> pawns = new HashMap<>();
    public static final String EMPTY = "   ";

    public GameState() {
        initBoard();
    }

    public String[] getBoard() {
        return board;
    }

    public Map<Integer, Pawns> getPawnsAndPos() {
        return pawns;
    }

    public void updateBoard() {
        for (int i = 0; i < 64; i++) {
            board[i] = EMPTY;
        }
        Set<Integer> posSet = pawns.keySet();
        int posChange = -1;
        int posDead = -1;
        //update board
        for (Integer pos : posSet) {
            Pawns pawn = pawns.get(pos);
            if(!pawn.isDead()) board[pawn.getPosition()] = pawn.getSymbol();
            else posDead = pos;
            if(pos != pawn.getPosition()){
                posChange = pos;
            }
        }
        //update map
        if(posDead != -1) pawns.remove(posDead);
        if(posChange != -1) {
            Pawns pawn = pawns.get(posChange);
            pawns.remove(posChange);
            pawns.put(pawn.getPosition(), pawn);
        }
    }

    private void initBoard() {
        for (int i = 0; i < 64; i++) {
            board[i] = EMPTY;
        }
        for (int i = 0; i < 8; i++) {
            pawns.put(i+8, new Pawn(true, i+8));
            pawns.put(i+48, new Pawn(false, i+48));

        }
        pawns.put(0, new Rook(true, 0));
        pawns.put(7, new Rook(true, 7));
        pawns.put(56, new Rook(false, 56));
        pawns.put(63, new Rook(false, 63));

        pawns.put(1, new Knight(true, 1));
        pawns.put(6, new Knight(true, 6));
        pawns.put(57, new Knight(false, 57));
        pawns.put(62, new Knight(false, 62));

        pawns.put(2, new Bishop(true, 2));
        pawns.put(5, new Bishop(true, 5));
        pawns.put(58, new Bishop(false, 58));
        pawns.put(61, new Bishop(false, 61));

        pawns.put(3, new Queen(true, 3));
        pawns.put(59, new Queen(false, 59));

        pawns.put(4, new King(true, 4));
        pawns.put(60, new King(false, 60));

        Set<Integer> posSet = pawns.keySet();
        for (Integer pos : posSet) {
            Pawns pawn = pawns.get(pos);
            board[pos] = pawn.getSymbol();
        }
    }
}
