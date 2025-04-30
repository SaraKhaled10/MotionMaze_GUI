package project;

import javax.swing.SwingUtilities;
import java.awt.EventQueue;

public class GameEngine {
    private SerialManager ser;
    private GamePanel panel;
    private Thread gameThread;
    private volatile boolean running = false;

    public GameEngine() {
        ser = new SerialManager();
        panel = new GamePanel(this);
    }

    public void start() {
        try {
            System.out.println("Game Starting...");
            ser.open("/dev/cu.usbserial-A700eDqH"); // Update your port
            running = true;

            // ─── Background thread always listening for serial ───
            gameThread = new Thread(() -> {
                while (running) {
                    try {
                        String line = ser.readLine(5000);
                        if (line != null && !line.isEmpty()) {
                            // Intercept PAUSE/RESUME commands
                            if (line.equals("PAUSE")) {
                                SwingUtilities.invokeLater(() -> panel.pauseGame());
                                continue;
                            }
                            if (line.equals("RESUME")) {
                                SwingUtilities.invokeLater(() -> panel.resumeGame());
                                continue;
                            }
                            // Otherwise treat as sensor data
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

    public void stopGame() {
        running = false;
        try {
            if (ser != null) ser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gameThread != null) {
            try { gameThread.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public GamePanel getPanel() {
        return panel;
    }

    // New helper for Java→Arduino
    public void sendCommand(String cmd) {
        try { ser.writeLine(cmd); }
        catch (Exception e) { e.printStackTrace(); }
    }
}
