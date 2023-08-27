package pawns;

public class Pawn extends Pawns {
    private final String PB = "♟";
    private final String PW = "♙";

    public Pawn(boolean isWhite, int position) {
        super(isWhite, position);
    }

    @Override
    public boolean canMove(int newPos) {
        if(isWhite()) return newPos == getPosition() + 8;
        else return newPos == getPosition() - 8;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? PW : PB;
    }

}
