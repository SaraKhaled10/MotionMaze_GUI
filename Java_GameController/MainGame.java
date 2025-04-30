package project;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainGame {
    public static void main(String[] args) {
        // Ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create the main application window
            JFrame frame = new JFrame("Motion Maze");

            // Create a GameEngine object which manages the game logic and panel
            GameEngine engine = new GameEngine();

            // Set the main content of the frame to the game's panel (UI)
            frame.setContentPane(engine.getPanel());

            // Size the frame so that all contents are at or above their preferred sizes
            frame.pack();

            // Exit the application when the window is closed
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Make the window visible
            frame.setVisible(true);

            // Start the game engine (begins game loop, communication, etc.)
            engine.start();
        });
    }
}
