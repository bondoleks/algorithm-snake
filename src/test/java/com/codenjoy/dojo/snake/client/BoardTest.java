package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.snake.model.Elements;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    @Test
    public void should() {
        Board board = (Board) new Board().forString(
                "☼☼☼☼" +
                "☼☺▲☼" +
                "☼☻╙☼" +
                "☼☼☼☼");
        assertEquals(
                "☼☼☼☼\n" +
                "☼☺▲☼\n" +
                "☼☻╙☼\n" +
                "☼☼☼☼\n", board.boardAsString());

        assertEquals("[[1,2]]", board.getApples().toString());
        assertEquals("[2,2]", board.getHead().toString());
        assertEquals(4, board.size());
        assertEquals(false, board.isGameOver());
        assertEquals("[[1,1]]", board.getStones().toString());

        assertTrue(board.isAt(0, 3, Elements.BREAK));
        assertTrue(board.isAt(0, 2, Elements.BREAK));
        assertTrue(board.isAt(0, 1, Elements.BREAK));
        assertTrue(board.isAt(0, 0, Elements.BREAK));

        assertTrue(board.isAt(3, 3, Elements.BREAK));
        assertTrue(board.isAt(3, 2, Elements.BREAK));
        assertTrue(board.isAt(3, 1, Elements.BREAK));
        assertTrue(board.isAt(3, 0, Elements.BREAK));

        assertTrue(board.isAt(0, 3, Elements.BREAK));
        assertTrue(board.isAt(1, 3, Elements.BREAK));
        assertTrue(board.isAt(2, 3, Elements.BREAK));
        assertTrue(board.isAt(3, 3, Elements.BREAK));

        assertTrue(board.isAt(0, 0, Elements.BREAK));
        assertTrue(board.isAt(1, 0, Elements.BREAK));
        assertTrue(board.isAt(2, 0, Elements.BREAK));
        assertTrue(board.isAt(3, 0, Elements.BREAK));

        assertTrue(board.isAt(1, 2, Elements.GOOD_APPLE));
        assertTrue(board.isAt(1, 1, Elements.BAD_APPLE));
        assertTrue(board.isAt(2, 2, Elements.HEAD_UP));
        assertTrue(board.isAt(2, 1, Elements.TAIL_END_DOWN));

        assertEquals(Direction.UP, board.getSnakeDirection());

        assertEquals("[[2,2], [2,1]]", board.getSnake().toString());

        assertEquals("[[2,2], [2,1], " +   // змейка
                "[1,1], " +         // камень
                "[0,0], [0,1], [0,2], [0,3], [1,0], [1,3], [2,0], [2,3], [3,0], [3,1], [3,2], [3,3]]",   // стены
                board.getBarriers().toString());

        assertEquals(
                "Board:\n" +
                "☼☼☼☼\n" +
                "☼☺▲☼\n" +
                "☼☻╙☼\n" +
                "☼☼☼☼\n" +
                "\n" +
                "Apple at: [[1,2]]\n" +
                "Stones at: [[1,1]]\n" +
                "Head at: [2,2]\n" +
                "Snake at: [[2,2], [2,1]]\n" +
                "Current direction: UP",
                board.toString());
    }

    @Test
    public void shouldWithoutStone() {
        Board board = (Board) new Board().forString(
                "☼☼☼☼" +
                "☼ ▲☼" +
                "☼☺╙☼" +
                "☼☼☼☼");
        assertEquals("[]", board.getStones().toString());
    }

    @Test
    public void shouldWith2Stone() {
        Board board = (Board) new Board().forString(
                "☼☼☼☼" +
                "☼☻▲☼" +
                "☼☻╙☼" +
                "☼☼☼☼");
        assertEquals("[[1,1], [1,2]]", board.getStones().toString());
    }

    @Test
    public void shouldIsGameOverTrue() {
        Board board = (Board) new Board().forString(
                "☼☼☼☼" +
                "☼ ╙☼" +
                "☼  ☼" +
                "☼☼☼☼");
        assertEquals(true, board.isGameOver());
    }
}
