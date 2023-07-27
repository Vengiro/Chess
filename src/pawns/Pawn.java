package pawns;

public class Pawn extends Pawns {
    private final String PB = "♟";
    private final String PW = "♙";

    public Pawn(boolean isWhite, int position) {
        super(isWhite, position);
    }

    @Override
    public boolean canMove(int newPos) {
        return newPos == getPosition() + 1;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? PW : PB;
    }

}
