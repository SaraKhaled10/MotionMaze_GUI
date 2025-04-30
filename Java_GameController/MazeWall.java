package project;

import java.awt.Color;
import java.awt.Graphics;

public class MazeWall {
    // Position and size of the wall
    private int x, y, width, height;

    // Constructor to initialize wall position and size
    public MazeWall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Draw the wall as a black filled rectangle
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    // Check for collision between this wall and a rectangular object
    public boolean collidesWith(int px, int py, int pWidth, int pHeight) {
        return (px + pWidth > x && px < x + width &&
                py + pHeight > y && py < y + height);
    }
}
