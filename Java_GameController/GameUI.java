public class GameUI implements Observer {
    @Override
    public void update(String status) {
        System.out.println("Game Update: " + status);
    }
}
