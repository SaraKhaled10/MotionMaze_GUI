public class GameStateContext {
    private GameStateInterface currentState;

    public void setState(GameStateInterface state) {
        if (currentState != null) currentState.exitState();
        currentState = state;
        currentState.enterState();
    }

    public void handleInput(int input) {
        currentState.handleInput(input);
    }
}
