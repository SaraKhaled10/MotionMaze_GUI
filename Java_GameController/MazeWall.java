package project;

import java.awt.Color;
import java.awt.Graphics;

public class MazeWall {
    private int x, y, width, height;

    public MazeWall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    public boolean collidesWith(int px, int py, int pWidth, int pHeight) {
    
        return (px + pWidth > x && px < x + width &&
                py + pHeight > y && py < y + height);
    }
}
