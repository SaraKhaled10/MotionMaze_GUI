public class GameEngine {
    private GamePanel panel;
    private GameState state;
    private Player player;
    private SerialManager serialManager;

    public GameEngine() {
        this.player = new Player(50, 50); // Start position
        this.state = new GameState();
        this.panel = new GamePanel(this);
        this.serialManager = new SerialManager(this);
    }

    public void start() {
        serialManager.initialize();
        panel.repaint();
    }

    public void updatePlayerPosition(int direction) {
        player.move(direction);
        panel.repaint();
    }

    public void resetGame() {
        player.setPosition(50, 50);
        state.reset();
        panel.repaint();
    }

    public Player getPlayer() {
        return player;
    }

    public GamePanel getPanel() {
        return panel;
    }

    public SerialManager getSerialManager() {
        return serialManager;
    }
}
