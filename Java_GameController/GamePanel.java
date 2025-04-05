import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private GameEngine engine;

    public GamePanel(GameEngine engine) {
        this.engine = engine;
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMaze(g);
        drawPlayer(g);
        drawPowerUps(g);
    }

    private void drawMaze(Graphics g) {
        g.setColor(Color.BLACK);
        // Draw maze boundaries and walls
        g.drawRect(20, 20, 560, 560); // outer boundary
        g.drawLine(100, 20, 100, 400);
        g.drawLine(200, 100, 500, 100);
        g.drawLine(300, 200, 300, 500);
    }

    private void drawPlayer(Graphics g) {
        Player p = engine.getPlayer();
        g.setColor(Color.BLUE);
        g.fillOval(p.x, p.y, 20, 20);
    }

    private void drawPowerUps(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(100, 100, 15, 15);  // Example power-up position
    }
}
