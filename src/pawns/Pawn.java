package pawns;

public class Pawn extends Pawns {
    private final String PB = "♙";
    private final String PW = "♟";

    public Pawn(boolean isWhite, int position) {
        super(isWhite, position);
    }

    @Override
    public boolean canMove(int newPos) {
        if(isWhite()) return newPos == getPosition() + 8 ||
                (newPos == getPosition() + 16 && getPosition() < 16)
                || (newPos == getPosition() + 7 && getPosition()%8 != 0) ||
                (newPos == getPosition() + 9 && getPosition()%8 != 7);
        else return newPos == getPosition() - 8 ||
                (newPos == getPosition() - 16 && getPosition() > 47)
                || (newPos == getPosition() - 7 && getPosition()%8 != 7) ||
                (newPos == getPosition() - 9 && getPosition()%8 != 0);
    }

    @Override
    public String getSymbol() {
        return isWhite() ? PW : PB;
    }
    public boolean goDiagonal(int newPos){
        return (newPos == getPosition() + 7 && getPosition()%8 != 0) ||
                (newPos == getPosition() + 9 && getPosition()%8 != 7) ||
                (newPos == getPosition() - 7 && getPosition()%8 != 7) ||
                (newPos == getPosition() - 9 && getPosition()%8 != 0);
    }

}
