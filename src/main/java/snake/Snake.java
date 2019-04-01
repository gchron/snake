package snake;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import snake.GameField;

import java.util.Arrays;
import java.util.LinkedList;

import static snake.SnakeDirection.*;

@ToString
@EqualsAndHashCode
public class Snake {


    @Getter
    private SnakeDirection direction;
    @Getter
    private LinkedList<GameField> tail = new LinkedList();
    private boolean isEating;

    public void setDirection(SnakeDirection direction) {
        if (!direction.isOpposite(this.direction)) {
            this.direction = direction;
        }
    }

    public Snake(SnakeDirection direction, GameField... gameFileds) {
        this.direction = direction;
        tail.addAll(Arrays.asList(gameFileds));
    }

    public void move() {
        GameField nextField = getNextField();
        tail.addFirst(nextField);
        if (!isEating) {
            tail.removeLast();
        } else {
            isEating = false;
        }
    }

    public GameField getNextField() {
        switch (direction) {
            case RIGHT:
                return getHead().getRight();
            case LEFT:
                return getHead().getLeft();
            case DOWN:
                return getHead().getDown();
            case UP:
                return getHead().getUp();
        }
        throw new IllegalArgumentException("Brak kierunku węża");
    }

    public GameField getHead() {
        return tail.getFirst();
    }

    public void eatApple() {
        isEating = true;
    }
}
