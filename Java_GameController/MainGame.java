package project;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Motion Maze");
            GameEngine engine = new GameEngine();
            frame.setContentPane(engine.getPanel());
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            engine.start();
        });
    }
}