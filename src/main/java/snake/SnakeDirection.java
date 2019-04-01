package snake;

public enum SnakeDirection {
    LEFT, UP, DOWN, RIGHT;

    public boolean isOpposite(SnakeDirection direction) {
        switch (this) {
            case UP:
                return direction == DOWN;
            case RIGHT:
                return direction == LEFT;
            case DOWN:
                return direction == UP;
            case LEFT:
                return direction == RIGHT;
        }
        return false;
    }
}
