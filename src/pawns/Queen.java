package pawns;

public class Queen extends Pawns {

    private final String QB = "♕";
    private final String QW = "♛";

    public Queen(boolean isWhite, int position) {
        super(isWhite, position);
    }

    @Override
    public boolean canMove(int newPos) {
        return (getPosition() - newPos) % 8 == 0 || (getPosition() - newPos) / 8 == 0 ||
               (getPosition() - newPos)%7 == 0 || (getPosition() - newPos)%9 == 0;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? QW : QB;
    }

}
