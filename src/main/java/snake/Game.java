package snake;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class Game {
    @Setter(AccessLevel.NONE)
    private Snake snake;
    private int areaHeight = 20;
    private int areaWidth = 20;
    private GameField apple;
    private boolean isAlreadyMoved;

    public Game(Snake snake) {
        this.snake = snake;
    }

    public void moveDown() {
        move(SnakeDirection.DOWN);
    }

    public void moveUp() {
        move(SnakeDirection.UP);
    }

    public void moveRight() {
        move(SnakeDirection.RIGHT);
    }

    private void move(SnakeDirection direction) {
        if (!isAlreadyMoved) {
            snake.setDirection(direction);
            isAlreadyMoved = true;
        }
    }

    public void moveLeft() {
        move(SnakeDirection.LEFT);
    }

    public void nextTurn() {
        GameField nextFiled = snake.getNextField();
        if (isXOutOfArea(nextFiled) || isYOutOfArea(nextFiled)) {
            throw new GameOverException();
        }
        if (nextFiled.equals(apple)) {
            snake.eatApple();
            generateNewApple();
        }
        if (snake.getTail().contains(nextFiled)) {
            throw new GameOverException();
        }
        snake.move();
        isAlreadyMoved = false;
    }

    private void generateNewApple() {
        List<GameField> allFields = new ArrayList<>(areaHeight * areaWidth);
        for (int y = 0; y <= areaHeight; y++) {
            for (int x = 0; x <= areaWidth; x++) {
                allFields.add(new GameField(x, y));
            }
        }
        allFields.removeAll(snake.getTail());
        allFields.remove(apple);
        Collections.shuffle(allFields); //tasowanie, mieszanie
        apple = allFields.get(0);
    }

    private boolean isYOutOfArea(GameField nextFiled) {
        return nextFiled.getY() < 0 || nextFiled.getY() > areaHeight;
    }

    private boolean isXOutOfArea(GameField nextFiled) {
        return nextFiled.getX() < 0 || nextFiled.getX() > areaWidth;
    }
}
