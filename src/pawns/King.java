package pawns;

public class King extends Pawns {
    private final String KB = "♔";
    private final String KW = "♚";

    public King(boolean isWhite, int position) {
        super(isWhite, position);
    }


    @Override
    public boolean canMove(int newPos) {
        return Math.abs(getPosition() - newPos) == 1 || Math.abs(getPosition() - newPos) == 8 ||
                Math.abs(getPosition() - newPos) == 7 || Math.abs(getPosition() - newPos) == 9;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? KW : KB;
    }


}
