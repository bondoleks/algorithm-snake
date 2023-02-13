package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.RandomDice;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SolverTest {

    private Dice dice;
    private Solver ai;

    @Before
    public void setup() {
        dice = mock(Dice.class);
        ai = new YourSolver(new RandomDice());
    }

    private Board board(String board) {
        return (Board) new Board().forString(board);
    }

    @Test
    public void should() {
        asertAI("☼☼☼☼☼☼☼" +
                "☼     ☼" +
                "☼  ☺  ☼" +
                "☼     ☼" +
                "☼ ☻▲  ☼" +
                "☼  ╙  ☼" +
                "☼☼☼☼☼☼☼", Direction.UP);

        asertAI("☼☼☼☼☼☼☼" +
                "☼     ☼" +
                "☼  ☺  ☼" +
                "☼  ▲  ☼" +
                "☼ ☻╙  ☼" +
                "☼     ☼" +
                "☼☼☼☼☼☼☼", Direction.UP);

        asertAI("☼☼☼☼☼☼☼" +
                "☼     ☼" +
                "☼  ▲  ☼" +
                "☼  ╙  ☼" +
                "☼ ☻   ☼" +
                "☼    ☺☼" +
                "☼☼☼☼☼☼☼", Direction.UP);

        asertAI("☼☼☼☼☼☼☼" +
                "☼     ☼" +
                "☼  ╔ ► ☼" +
                "☼  ╙  ☼" +
                "☼ ☻   ☼" +
                "☼    ☺☼" +
                "☼☼☼☼☼☼☼", Direction.RIGHT);

        asertAI("☼☼☼☼☼☼☼" +
                      "☼    ▲☼" +
                      "☼ ☺╓ ╝☼" +
                      "☼     ☼" +
                      "☼ ☻   ☼" +
                      "☼     ☼" +
                      "☼☼☼☼☼☼☼", Direction.UP);

    }

    private void asertAI(String board, Direction expected) {
        String actual = ai.get(board(board));
        assertEquals(expected.toString(), actual);
    }

    private void dice(Direction direction) {
        when(dice.next(anyInt())).thenReturn(direction.value());
    }
}
