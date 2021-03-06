import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import snake.*;

public class GameTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private GameField field(int x, int y) {
        return new GameField(x, y);
    }

    @Test
    public void shouldMoveForwardWhenThereIsNoAction() {
        //given
        Snake snake = new Snake(
                SnakeDirection.RIGHT,
                field(2, 1),
                field(1, 1),
                field(0, 1));

        //when
        Game game = new Game(snake);
        game.nextTurn();

        //then
        Snake expectedSnake = new Snake(
                SnakeDirection.RIGHT,
                field(3, 1),
                field(2, 1),
                field(1, 1));

        Assert.assertEquals(expectedSnake, snake);
    }

    @Test
    public void shouldMoveDownWhenThereIsDownAction() {
        //given
        Snake snake = new Snake(
                SnakeDirection.RIGHT,
                field(2, 1),
                field(1, 1),
                field(0, 1));

        //when
        Game game = new Game(snake);
        game.moveDown();
        game.nextTurn();

        //then
        Snake expectedSnake = new Snake(
                SnakeDirection.DOWN,
                field(2, 2),
                field(2, 1),
                field(1, 1));

        Assert.assertEquals(expectedSnake, snake);
    }

    @Test
    public void shouldMoveUPWhenThereIsUPAction() {
        //given
        Snake snake = new Snake(
                SnakeDirection.RIGHT,
                field(2, 1),
                field(1, 1),
                field(0, 1));

        //when
        Game game = new Game(snake);
        game.moveUp();
        game.nextTurn();

        //then
        Snake expectedSnake = new Snake(
                SnakeDirection.UP,
                field(2, 0),
                field(2, 1),
                field(1, 1));

        Assert.assertEquals(expectedSnake, snake);
    }

    @Test
    public void shouldMoveRightWhenThereIsRightAction() {
        //given
        Snake snake = new Snake(
                SnakeDirection.RIGHT,
                field(2, 2),
                field(2, 1),
                field(2, 0));
        Game game = new Game(snake);

        //when
        game.moveRight();
        game.nextTurn();

        //then
        Snake expectedSnake = new Snake(
                SnakeDirection.RIGHT,
                field(3, 2),
                field(2, 2),
                field(2, 1));

        Assert.assertEquals(expectedSnake, snake);
    }

    @Test
    public void shouldMoveLeftWhenThereIsLeftAction() {
        //given
        Snake snake = new Snake(
                SnakeDirection.DOWN,
                field(2, 2),
                field(2, 1),
                field(2, 0));
        Game game = new Game(snake);

        //when
        game.moveLeft();
        game.nextTurn();

        //then
        Snake expectedSnake = new Snake(
                SnakeDirection.LEFT,
                field(1, 2),
                field(2, 2),
                field(2, 1));

        Assert.assertEquals(expectedSnake, snake);
    }

    @Test
    public void shoulMoveForwardWhenThereIsOpposite() {
        //given
        Snake snake = new Snake(
                SnakeDirection.DOWN,
                field(2, 2),
                field(2, 1),
                field(2, 0));
        Game game = new Game(snake);

        //when
        game.moveUp();
        game.nextTurn();

        //then
        Snake expectedSnake = new Snake(
                SnakeDirection.DOWN,
                field(2, 3),
                field(2, 2),
                field(2, 1));

        Assert.assertEquals(expectedSnake, snake);
    }

    @Test
    public void shoulGameBeOverWhenBottomBorderIsReached() {
        //given
        Snake snake = new Snake(
                SnakeDirection.DOWN,
                field(2, 2),
                field(2, 1),
                field(2, 0));
        Game game = new Game(snake);
        game.setAreaHeight(2);

        //then
        expectedException.expect(GameOverException.class);
        game.nextTurn();
    }

    @Test
    public void shouldEatApple() {
        //given
        Snake snake = new Snake(
                SnakeDirection.DOWN,
                field(2, 2),
                field(2, 1),
                field(2, 0));
        Game game = new Game(snake);
        game.setApple(new GameField(2, 3));

        //when
        game.nextTurn();

        //expect
        Snake expectedSnake = new Snake(
                SnakeDirection.DOWN,
                field(2, 3),
                field(2, 2),
                field(2, 1),
                field(2, 0));

        Assert.assertEquals(expectedSnake, snake);
    }

    @Test
    public void shouldGenerateNewApple() {
        //given
        Snake snake = new Snake(
                SnakeDirection.DOWN,
                field(2, 2),
                field(2, 1),
                field(2, 0));
        Game game = new Game(snake);
        game.setApple(new GameField(2, 3));

        //when
        game.nextTurn();

        //then
        GameField apple = game.getApple();
        Assert.assertNotEquals(field(2, 3), apple);
        Assert.assertNotEquals(field(2, 3), apple);
        Assert.assertNotEquals(field(2, 3), apple);
        Assert.assertNotEquals(field(2, 3), apple);
    }

    @Test
    public void shoulGameBeOverWhenTailIsReached() {
        //given
        Snake snake = new Snake(
                SnakeDirection.LEFT,
                field(2, 3),
                field(3, 3),
                field(3, 2),
                field(3, 1),
                field(2, 1),
                field(1, 1),
                field(1, 2),
                field(1, 1),
                field(1, 3),
                field(1, 4));
        Game game = new Game(snake);

        //then
        expectedException.expect(GameOverException.class);
        game.nextTurn();
    }
    @Test
    public void shouldIgnoreSecondActionPerTurn() {
        //given
        Snake snake = new Snake(
                SnakeDirection.RIGHT,
                field(2, 1),
                field(1, 1),
                field(0, 1));
        Game game = new Game(snake);

        //when
        game.moveDown();
        game.moveLeft();
        game.moveUp();
        game.nextTurn();

        //then
        Snake expectedSnake = new Snake(
                SnakeDirection.DOWN,
                field(2, 2),
                field(2, 1),
                field(1, 1));

        Assert.assertEquals(expectedSnake, snake);
    }
}




















