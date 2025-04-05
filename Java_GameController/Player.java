public class Player {
    public int x, y;
    private final int step = 10;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int direction) {
        switch (direction) {
            case 1: y -= step; break; // Up
            case 2: y += step; break; // Down
            case 3: x -= step; break; // Left
            case 4: x += step; break; // Right
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
