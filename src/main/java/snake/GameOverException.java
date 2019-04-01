package snake;

public class GameOverException extends RuntimeException {

    public GameOverException() {
        super("Gra Skończona");
    }
}
