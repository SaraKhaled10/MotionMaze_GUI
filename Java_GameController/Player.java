package project;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    private int x, y;
    public static final int SIZE = 20; // Diameter of player circle

    // Constructor sets initial position
    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    // Moves player by delta values, keeps inside maze bounds
    public void moveBy(int dx, int dy) {
        x += dx;
        y += dy;
        x = Math.max(20, Math.min(x, 580)); // Limit x within bounds
        y = Math.max(20, Math.min(y, 580)); // Limit y within bounds
    }

    // Sets player to new position
    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    // Draws player as a red circle
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, SIZE, SIZE);
    }

    // Checks collision with a rectangular obstacle
    public boolean collidesWith(Obstacle o) {
        return (x + SIZE > o.getX() && x < o.getX() + o.getWidth() &&
                y + SIZE > o.getY() && y < o.getY() + o.getHeight());
    }

    // Checks collision with a circular power-up
    public boolean collidesWith(PowerUp p) {
        int centerX = x + SIZE / 2;
        int centerY = y + SIZE / 2;
        int distX = centerX - p.getX();
        int distY = centerY - p.getY();
        return Math.sqrt(distX * distX + distY * distY) < p.getRadius();
    }

    // Getter for x-coordinate
    public int getX() { return x; }

    // Getter for y-coordinate
    public int getY() { return y; }
}
