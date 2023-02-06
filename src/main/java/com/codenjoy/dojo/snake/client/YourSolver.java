package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.RandomDice;

/**
 * User: name
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        System.out.println(board.toString());

        return Direction.UP.toString();
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://206.81.21.158/codenjoy-contest/board/player/f6xu37pvn4jmb45uys6p?code=3261882916347567004",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
