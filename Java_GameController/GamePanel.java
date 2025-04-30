package project;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private GameEngine engine;                    // Reference to the main game engine
    private Player player;                        // Player object
    private List<Obstacle> obstacles;             // List of obstacles
    private List<PowerUp> powerUps;               // List of power-ups
    private List<MazeWall> mazeWalls;             // List of maze walls
    private int finishX = 500, finishY = 400;     // Position of finish point
    private int finishSize = 30;                  // Size of finish area
    private int level = 1;                        // Current game level
    private boolean protectedByPowerUp = false;   // Protection status
    private boolean isPaused = false;             // Pause flag

    public GamePanel(GameEngine e) {
        this.engine = e;
        this.player = new Player(50, 50);
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.WHITE);
        generateMazeWallsLevel1();  // Set walls for level 1
        generateObstacles();        // Add obstacles
        generatePowerUps();         // Add power-ups
    }

    public void pauseGame() {
        isPaused = true;
        System.out.println("GamePanel: paused");
    }

    public void resumeGame() {
        isPaused = false;
        System.out.println("GamePanel: resumed");
    }

    // Creates maze walls for level 1
    private void generateMazeWallsLevel1() {
        mazeWalls = new ArrayList<>();

        mazeWalls.add(new MazeWall(20, 20, 560, 10));     // Top wall
        mazeWalls.add(new MazeWall(20, 20, 10, 560));     // Left wall
        mazeWalls.add(new MazeWall(20, 570, 560, 10));    // Bottom wall
        mazeWalls.add(new MazeWall(570, 20, 10, 560));    // Right wall

        mazeWalls.add(new MazeWall(120, 20, 10, 10));     // Small vertical wall near start
        mazeWalls.add(new MazeWall(120, 120, 150, 10));   // Horizontal wall after turn
        mazeWalls.add(new MazeWall(270, 50, 10, 80));     // Vertical block to prevent shortcut
        mazeWalls.add(new MazeWall(270, 120, 100, 10));   // Middle horizontal
        mazeWalls.add(new MazeWall(370, 120, 10, 200));   // Center vertical
        mazeWalls.add(new MazeWall(270, 320, 110, 10));   // Lower horizontal
        mazeWalls.add(new MazeWall(270, 320, 10, 100));   // Guide vertical
        mazeWalls.add(new MazeWall(370, 320, 100, 10));   // Path to bottom-right
        mazeWalls.add(new MazeWall(570, 320, 10, 250));   // Final vertical wall
    }

    // Creates maze walls for level 2
    private void generateMazeWallsLevel2() {
        mazeWalls = new ArrayList<>();
        mazeWalls.add(new MazeWall(50, 100, 500, 10));
        mazeWalls.add(new MazeWall(100, 200, 10, 300));
        mazeWalls.add(new MazeWall(200, 50, 10, 400));
        mazeWalls.add(new MazeWall(300, 200, 10, 300));
        mazeWalls.add(new MazeWall(400, 100, 10, 400));
        mazeWalls.add(new MazeWall(150, 450, 250, 10));
    }

    // Creates maze walls for level 3
    private void generateMazeWallsLevel3() {
        mazeWalls = new ArrayList<>();
        mazeWalls.add(new MazeWall(100, 100, 400, 10));
        mazeWalls.add(new MazeWall(100, 100, 10, 400));
        mazeWalls.add(new MazeWall(100, 500, 400, 10));
        mazeWalls.add(new MazeWall(500, 100, 10, 400));
        mazeWalls.add(new MazeWall(250, 100, 10, 300));
        mazeWalls.add(new MazeWall(350, 200, 10, 300));
    }

    // Adds obstacles to the game
    private void generateObstacles() {
        obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(250, 150, 30, 30));
        obstacles.add(new Obstacle(300, 250, 30, 30));
        obstacles.add(new Obstacle(450, 250, 30, 30));
    }

    // Adds power-ups to the game
    private void generatePowerUps() {
        powerUps = new ArrayList<>();
        powerUps.add(new PowerUp(250, 100, 20));
        powerUps.add(new PowerUp(300, 250, 20));
        powerUps.add(new PowerUp(450, 250, 20));
    }

    // Updates game state based on sensor input
    public void update(String status) {
        try {
            String[] parts = status.split(",");
            int ax = Integer.parseInt(parts[0].split(":")[1]);
            int ay = Integer.parseInt(parts[1].split(":")[1]);

            int deltaX = 0;
            int deltaY = 0;

            if (Math.abs(ax) > 3000) {
                deltaX = (ax > 0) ? 2 : -2;
            }
            if (Math.abs(ay) > 3000) {
                deltaY = (ay > 0) ? 2 : -2;
            }

            movePlayer(deltaX, deltaY);       // Move player
            checkObstacleCollision();         // Check if player hit an obstacle
            checkPowerUpCollection();         // Check if power-up collected
            checkFinish();                    // Check if level is completed
        } catch (Exception e) {
            System.out.println("Bad input: " + status);
        }
    }

    // Moves the player and checks wall collision
    private void movePlayer(int dx, int dy) {
        int newX = player.getX() + dx;
        int newY = player.getY() + dy;

        if (!collidesWithWall(newX, player.getY())) {
            player.moveBy(dx, 0);
        } else {
            if (protectedByPowerUp) {
                protectedByPowerUp = false;
                System.out.println("Protected from wall collision (X axis)!");
            } else {
                resetPlayer();  // Restart if hit
                return;
            }
        }

        if (!collidesWithWall(player.getX(), newY)) {
            player.moveBy(0, dy);
        } else {
            if (protectedByPowerUp) {
                protectedByPowerUp = false;
                System.out.println("Protected from wall collision (Y axis)!");
            } else {
                resetPlayer();
            }
        }
    }

    // Checks if the player touches any maze wall
    private boolean collidesWithWall(int x, int y) {
        for (MazeWall wall : mazeWalls) {
            if (wall.collidesWith(x, y, Player.SIZE, Player.SIZE)) {
                return true;
            }
        }
        return false;
    }

    // Resets player to start position
    private void resetPlayer() {
        player.setPosition(50, 50);
    }

    // Checks if player hits an obstacle
    private void checkObstacleCollision() {
        for (Obstacle o : obstacles) {
            if (player.collidesWith(o)) {
                JOptionPane.showMessageDialog(this, "Game Over! You hit an obstacle!");
                engine.stopGame();
                System.exit(0);
            }
        }
    }

    // Checks if player collects a power-up
    private void checkPowerUpCollection() {
        List<PowerUp> collected = new ArrayList<>();
        for (PowerUp p : powerUps) {
            if (player.collidesWith(p)) {
                collected.add(p);
                protectedByPowerUp = true;
                System.out.println("Power-up collected! Protection activated.");
            }
        }
        powerUps.removeAll(collected);
    }

    // Checks if player reached the finish point
    private void checkFinish() {
        if (Math.abs(player.getX() - finishX) < 20 && Math.abs(player.getY() - finishY) < 20) {
            if (level == 1) {
                level = 2;
                startNextLevel();
            } else if (level == 2) {
                level = 3;
                startNextLevel();
            } else if (level == 3) {
                JOptionPane.showMessageDialog(this, "🎉 Congratulations! You finished all levels!");
                engine.stopGame();
                System.exit(0);
            }
        }
    }

    // Sets up the next level
    private void startNextLevel() {
        player.setPosition(50, 50);
        finishX = 500;
        finishY = 400;
        protectedByPowerUp = false;

        if (level == 2) {
            generateMazeWallsLevel2();
        } else if (level == 3) {
            generateMazeWallsLevel3();
        }

        generateObstacles();
        generatePowerUps();
        repaint();
    }

    // Draws all game elements
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawRect(20, 20, 560, 560); // Draw maze border

        for (MazeWall wall : mazeWalls) {
            wall.draw(g);
        }
        for (Obstacle o : obstacles) {
            o.draw(g);
        }
        for (PowerUp p : powerUps) {
            p.draw(g);
        }

        g.setColor(Color.GREEN);
        g.fillRect(finishX, finishY, finishSize, finishSize); // Draw finish zone

        player.draw(g);  // Draw player
    }
}
