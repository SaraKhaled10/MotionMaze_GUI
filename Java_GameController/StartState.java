public class StartState implements GameStateInterface {
    @Override
    public void enterState() {
        System.out.println("Game is starting...");
    }

    @Override
    public void handleInput(int input) {
        if (input != 7) {
            System.out.println("Switching to Play State...");
            GameStateContext ctx = new GameStateContext();
            ctx.setState(new PlayState());
            ctx.handleInput(input);
        }
    }

    @Override
    public void exitState() {
        System.out.println("Exiting Start State...");
    }
}
