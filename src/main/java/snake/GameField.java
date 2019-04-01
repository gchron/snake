package snake;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class GameField {
    int x;
    int y;

    public GameField getRight() {
        return new GameField(x + 1, y);
    }

    public GameField getLeft() {
        return new GameField(x - 1, y);
    }

    public GameField getUp() {
        return new GameField(x, y - 1);
    }

    public GameField getDown() {
        return new GameField(x, y + 1);
    }
}
