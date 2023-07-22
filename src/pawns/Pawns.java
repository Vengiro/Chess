package pawns;

public abstract class Pawns {
    private boolean isDead;
    private int position;

    public Pawns() {
        isDead = false;
    }

    public boolean isDead() {
        return isDead;
    }

    public void die() {
        isDead = true;
    }
    public int getPosition() {
        return position;
    }
    public void move(int newPos) {
        if(canMove(newPos)) position = newPos;
    }

    public abstract boolean canMove(int newPos);

    public abstract String getSymbolBlack();
    public abstract String getSymbolWhite();

}
