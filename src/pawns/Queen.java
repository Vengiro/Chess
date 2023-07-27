package pawns;

public class Queen extends Pawns {

    private final String QB = "♕";
    private final String QW = "♛";

    public Queen(boolean isWhite, int position) {
        super(isWhite, position);
    }

    @Override
    public boolean canMove(int newPos) {
        return false;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? QW : QB;
    }

}
