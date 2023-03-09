package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.*;
import com.codenjoy.dojo.snake.model.Elements;
import com.codenjoy.dojo.snake.trace.BoardLee;
import com.codenjoy.dojo.snake.trace.PointLee;

import java.util.List;
import java.util.Optional;

/**
 * User: Oleksandr Bondarchuk
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    public int invertVervical(int val, int dimY) {
        return dimY - val - 1;
    }

    @Override
    public String get(Board board) {
        this.board = board;

        if (board.isGameOver()) return "";

        char[][] field = board.getField();
        int sizeX = field.length;
        int sizeY = field[0].length;
        BoardLee boardLee = new BoardLee(sizeX, sizeY);
        Point head = board.getHead();
        List<Point> snake = board.getSnake();
        List<Point> barriersAfterNoMoves = board.getBarriersWithoutStones();
        Point appleOrStone;

        if (snake.size() > 50) {
            appleOrStone = board.getStones().get(0);
            List<Point> barriersWithoutStone = board.getBarriersWithoutStones();
            barriersWithoutStone.forEach(p -> boardLee.setObstacle(p.getX(), invertVervical(p.getY(), sizeY)));
        } else if(snake.size() > 30){
            appleOrStone = board.getApples().get(0);
            List<Point> barriersWithoutStone = board.getBarriersWithoutStones();
            barriersWithoutStone.forEach(p -> boardLee.setObstacle(p.getX(), invertVervical(p.getY(), sizeY)));
        } else {
            appleOrStone = board.getApples().get(0);
            List<Point> barriers = board.getBarriers();
            barriers.forEach(p -> boardLee.setObstacle(p.getX(), invertVervical(p.getY(), sizeY)));
        }

        PointLee src = new PointLee(head.getX(), invertVervical(head.getY(), sizeY));
        PointLee dstApple = new PointLee(appleOrStone.getX(), invertVervical(appleOrStone.getY(), sizeY));
        Optional<List<PointLee>> solution = boardLee.trace(src, dstApple);

        if (solution.isPresent()) {
            List<PointLee> path = solution.get();
            PointLee p = path.stream().skip(1).findFirst().get();
            int to_x = p.x();
            int to_y = invertVervical(p.y(), sizeY);
            if (to_x < head.getX()) return Direction.LEFT.toString();
            if (to_y > head.getY()) return Direction.UP.toString();
            if (to_x > head.getX()) return Direction.RIGHT.toString();
            if (to_y < head.getY()) return Direction.DOWN.toString();

        } else {
            head.setX(head.getX() - 1);
            if (!barriersAfterNoMoves.contains(head)) return Direction.LEFT.toString();
            head.setX(head.getX() + 1);
            head.setY(head.getY() - 1);
            if (!barriersAfterNoMoves.contains(head)) return Direction.DOWN.toString();
            head.setX(head.getX() + 1);
            head.setY(head.getY() + 1);
            if (!barriersAfterNoMoves.contains(head)) return Direction.RIGHT.toString();
            head.setX(head.getX() - 1);
            head.setY(head.getY() + 1);
            if (!barriersAfterNoMoves.contains(head)) return Direction.UP.toString();
        }
        return Direction.ACT().toString();
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                "http://206.81.21.158/codenjoy-contest/board/player/f6xu37pvn4jmb45uys6p?code=3261882916347567004",
                new YourSolver(new RandomDice()),
                new Board());
    }
}

