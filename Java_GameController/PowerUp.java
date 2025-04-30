package project;



import java.awt.Color;
import java.awt.Graphics;

public class PowerUp {
    private int x, y, radius;

    public PowerUp(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x - radius / 2, y - radius / 2, radius, radius);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadius() { return radius; }
}
