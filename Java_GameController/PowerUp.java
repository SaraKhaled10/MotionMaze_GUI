package project;

import java.awt.Color;
import java.awt.Graphics;

public class PowerUp {
    private int x, y, radius; // Position and size

    // Constructor sets position and size
    public PowerUp(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    // Draws power-up as a blue circle
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x - radius / 2, y - radius / 2, radius, radius);
    }

    // Getter for x-coordinate
    public int getX() { return x; }

    // Getter for y-coordinate
    public int getY() { return y; }

    // Getter for radius
    public int getRadius() { return radius; }
}
