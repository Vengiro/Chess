package pawns;

public abstract class Pawns {
    private boolean isDead;
    private int position;
    private boolean isWhite;

    public Pawns(boolean isWhite, int position) {
        isDead = false;
        this.position = position;
        this.isWhite = isWhite;
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

    public boolean isWhite() {
        return isWhite;
    }

    public abstract boolean canMove(int newPos);

    public abstract String getSymbol();

}
