package pawns;

public class King extends Pawns {
    private final String KB = "♔";
    private final String KW = "♚";

    public King(boolean isWhite, int position) {
        super(isWhite, position);
    }


    @Override
    public boolean canMove(int newPos) {
        return false;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? KW : KB;
    }


}
