package pawns;

public class Bishop extends Pawns {
    private final String BB = "♗";
    private final String BW = "♝";

    public Bishop(boolean isWhite, int position) {
        super(isWhite, position);
    }


    @Override
    public boolean canMove(int newPos) {
        int min = Math.min(getPosition(), newPos);
        int max = Math.max(getPosition(), newPos);
        int diff = max - min;
        return diff % 7 == 0 || diff % 9 == 0 &&
                ! ((getPosition()%8 == 0 && getPosition() + 8 == newPos)
                || (getPosition()%8 == 7 && getPosition() - 8 == newPos));
    }

    @Override
    public String getSymbol() {
        return isWhite() ? BW : BB;
    }


}
