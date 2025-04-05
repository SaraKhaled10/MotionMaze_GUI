public class PlayState implements GameStateInterface {
    @Override
    public void enterState() {
        System.out.println("Game Started - Playing...");
    }

    @Override
    public void handleInput(int input) {
        if (input == 5) {
            System.out.println("Obstacle Hit!");
        } else if (input == 6) {
            System.out.println("Power-Up Collected!");
        }
    }

    @Override
    public void exitState() {
        System.out.println("Exiting Play State...");
    }
}
