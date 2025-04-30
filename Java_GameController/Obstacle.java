package project;

import java.awt.Color;
import java.awt.Graphics;

public class Obstacle {
    private int x, y, width, height;

    // Constructor to initialize position and size
    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Draws the obstacle as a black rectangle
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    // Getter for x-coordinate
    public int getX() { return x; }

    // Getter for y-coordinate
    public int getY() { return y; }

    // Getter for width
    public int getWidth() { return width; }

    // Getter for height
    public int getHeight() { return height; }
}
