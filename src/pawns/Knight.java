package pawns;

public class Knight extends Pawns {
    private final String NB = "♘";
    private final String NW = "♞";

    public Knight(boolean isWhite, int position) {
        super(isWhite, position);
    }


    @Override
    public boolean canMove(int newPos) {
        return Math.abs(getPosition() - newPos) == 6 || Math.abs(getPosition() - newPos) == 10 ||
                Math.abs(getPosition() - newPos) == 15 || Math.abs(getPosition() - newPos) == 17;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? NW : NB;
    }
}
