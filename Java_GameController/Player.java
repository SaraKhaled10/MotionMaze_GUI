package project;


import java.awt.Color;
import java.awt.Graphics;

public class Player {
    private int x, y;
    public static final int SIZE = 20;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void moveBy(int dx, int dy) {
        x += dx;
        y += dy;
        x = Math.max(20, Math.min(x, 580)); // Stay inside maze borders
        y = Math.max(20, Math.min(y, 580));
    }

    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public boolean collidesWith(Obstacle o) {
        return (x + SIZE > o.getX() && x < o.getX() + o.getWidth() &&
                y + SIZE > o.getY() && y < o.getY() + o.getHeight());
    }

    public boolean collidesWith(PowerUp p) {
        int centerX = x + SIZE / 2;
        int centerY = y + SIZE / 2;
        int distX = centerX - p.getX();
        int distY = centerY - p.getY();
        return Math.sqrt(distX * distX + distY * distY) < p.getRadius();
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
