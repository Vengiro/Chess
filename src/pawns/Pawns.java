package pawns;

public abstract class Pawns {
    private boolean isDead;

    public Pawns() {
        isDead = false;
    }

    public boolean isDead() {
        return isDead;
    }

    public void die() {
        isDead = true;
    }

    public abstract boolean canMove(int x, int y);
}
