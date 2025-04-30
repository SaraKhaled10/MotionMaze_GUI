package project;

import javax.swing.SwingUtilities;
import java.awt.EventQueue;

public class GameEngine {
    private SerialManager ser;     // Handles serial communication
    private GamePanel panel;       // UI panel for the game
    private Thread gameThread;     // Background thread
    private volatile boolean running = false; // Flag to control loop

    public GameEngine() {
        ser = new SerialManager();
        panel = new GamePanel(this);
    }

    public void start() {
        try {
            System.out.println("Game Starting...");
            ser.open("/dev/cu.usbserial-A700eDqH"); // Port for Arduino
            running = true;

            // Background thread reads serial data
            gameThread = new Thread(() -> {
                while (running) {
                    try {
                        String line = ser.readLine(5000);

                        if (line != null && !line.isEmpty()) {

                            // Handle pause signal
                            if (line.equals("PAUSE")) {
                                SwingUtilities.invokeLater(() -> panel.pauseGame());
                                continue;
                            }

                            // Handle resume signal
                            if (line.equals("RESUME")) {
                                SwingUtilities.invokeLater(() -> panel.resumeGame());
                                continue;
                            }

                            // Update game with sensor data
                            SwingUtilities.invokeLater(() -> {
                                panel.update(line);
                                panel.repaint();
                            });
                        }

                    } catch (Exception e) {
                        System.out.println("Serial Read Error: " + e.getMessage());
                    }
                }
            });
            gameThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Stops game and closes everything
    public void stopGame() {
        running = false;

        try {
            if (ser != null) ser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gameThread != null) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Returns the game panel
    public GamePanel getPanel() {
        return panel;
    }

    // Sends a message to Arduino
    public void sendCommand(String cmd) {
        try {
            ser.writeLine(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
