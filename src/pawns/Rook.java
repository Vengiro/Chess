package pawns;

public class Rook extends Pawns{
    private final String RB = "♖";
    private final String RW = "♜";

    public Rook(boolean isWhite, int position) {
        super(isWhite, position);
    }

    @Override
    public boolean canMove(int newPos) {
        return false;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? RW : RB;
    }
}
