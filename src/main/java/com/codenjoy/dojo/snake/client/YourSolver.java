package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.List;

/**
 * User: name
 */
public class YourSolver implements Solver<Board> {

    private Direction doSolve(Board board) {
//        System.out.println(board.toString());
        if (!board.isGameOver()) {
            Point apple = board.getApples().get(0);
            Point head = board.getHead();
            List<Point> snake = board.getSnake();
            Point stone = board.getStones().get(0);
            List<Point> walls = board.getWalls();
            Elements at = board.getAt(apple);

            //         3            5
            if (apple.getX() < head.getX()) return Direction.LEFT;
            if (apple.getY() > head.getY()) return Direction.UP;
            if (apple.getX() > head.getX()) return Direction.RIGHT;
            if (apple.getY() < head.getY()) return Direction.DOWN;
        }
        return Direction.random();
    }

    @Override
    public String get(Board board) {
        return doSolve(board).toString();
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://206.81.21.158/codenjoy-contest/board/player/f6xu37pvn4jmb45uys6p?code=3261882916347567004",
                new YourSolver(),
                new Board());
    }

}
