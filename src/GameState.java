import java.util.HashMap;
import java.util.Map;

public class GameState {
    private final String[] board = new String[64];

    private final String PB = "♟";
    private final String PW = "♙";
    private final String KB = "♔";
    private final String KW = "♚";
    private final String QB = "♕";
    private final String QW = "♛";
    private final String RB = "♖";
    private final String RW = "♜";
    private final String BB = "♗";
    private final String BW = "♝";
    private final String NB = "♘";
    private final String NW = "♞";

    public GameState() {
        for(int i = 0; i < 8; i++) {
            board[i] = PB;
            board[8 + i] = PW;
            board[48 + i] = PW;
            board[56 + i] = PB;
        }
    }

    public String[] getBoard() {
        return board;
    }

    public void updateBoard() {

    }
}
