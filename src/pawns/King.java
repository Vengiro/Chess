package pawns;

public class King extends Pawns {
    @Override
    public boolean canMove(int newPos) {
        return false;
    }

    @Override
    public String getSymbolBlack() {
        return null;
    }

    @Override
    public String getSymbolWhite() {
        return null;
    }
}
