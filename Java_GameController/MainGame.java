import javax.swing.*;

public class MainGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameEngine engine = new GameEngine();
            JFrame frame = new JFrame("Motion Maze");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(engine.getPanel());
            frame.pack();
            frame.setVisible(true);
            engine.start();
        });
    }
}
