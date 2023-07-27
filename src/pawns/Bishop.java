package pawns;

public class Bishop extends Pawns {
    private final String BB = "♗";
    private final String BW = "♝";

    public Bishop(boolean isWhite, int position) {
        super(isWhite, position);
    }


    @Override
    public boolean canMove(int newPos) {
        return false;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? BW : BB;
    }


}
