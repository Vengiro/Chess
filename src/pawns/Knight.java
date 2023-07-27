package pawns;

public class Knight extends Pawns {
    private final String NB = "♘";
    private final String NW = "♞";

    public Knight(boolean isWhite, int position) {
        super(isWhite, position);
    }


    @Override
    public boolean canMove(int newPos) {
        return false;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? NW : NB;
    }
}
