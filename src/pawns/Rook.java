package pawns;

public class Rook extends Pawns{
    private final String RB = "♖";
    private final String RW = "♜";

    public Rook(boolean isWhite, int position) {
        super(isWhite, position);
    }

    @Override
    public boolean canMove(int newPos) {
        return (getPosition() - newPos) % 8 == 0 || (getPosition() - newPos) / 8 == 0;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? RW : RB;
    }
}
