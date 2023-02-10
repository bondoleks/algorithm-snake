package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.*;
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

            List<Point> head2 = board.getHead2();
            List<Point> barriers = board.getBarriers();


            if (apple.getX() < head.getX()) {
                head.setX(head.getX() - 1);
                if (!barriers.contains(head)) return Direction.LEFT;
                head.setX(head.getX() + 1);
                head.setY(head.getY() - 1);
                if (!barriers.contains(head)) return Direction.DOWN;
                head.setX(head.getX() + 1);
                head.setY(head.getY() + 1);
                if (!barriers.contains(head)) return Direction.RIGHT;
                head.setX(head.getX() - 1);
                head.setY(head.getY() + 1);
                if (!barriers.contains(head)) return Direction.UP;
            }
            if (apple.getY() > head.getY()) {
                head.setY(head.getY() + 1);
                if (!barriers.contains(head)) return Direction.UP;
                head.setX(head.getX() - 1);
                head.setY(head.getY() - 1);
                if (!barriers.contains(head)) return Direction.LEFT;
                head.setX(head.getX() + 1);
                head.setY(head.getY() - 1);
                if (!barriers.contains(head)) return Direction.DOWN;
                head.setX(head.getX() + 1);
                head.setY(head.getY() + 1);
                if (!barriers.contains(head)) return Direction.RIGHT;
            }
            if (apple.getX() > head.getX()) {
                head.setX(head.getX() + 1);
                if (!barriers.contains(head)) return Direction.RIGHT;
                head.setX(head.getX() - 1);
                head.setY(head.getY() + 1);
                if (!barriers.contains(head)) return Direction.UP;
                head.setX(head.getX() - 1);
                head.setY(head.getY() - 1);
                if (!barriers.contains(head)) return Direction.LEFT;
                head.setX(head.getX() + 1);
                head.setY(head.getY() - 1);
                if (!barriers.contains(head)) return Direction.DOWN;
            }
            if (apple.getY() < head.getY()) {
                head.setY(head.getY() - 1);
                if (!barriers.contains(head)) return Direction.DOWN;
                head.setX(head.getX() + 1);
                head.setY(head.getY() + 1);
                if (!barriers.contains(head)) return Direction.RIGHT;
                head.setX(head.getX() - 1);
                head.setY(head.getY() + 1);
                if (!barriers.contains(head)) return Direction.UP;
                head.setX(head.getX() - 1);
                head.setY(head.getY() - 1);
                if (!barriers.contains(head)) return Direction.LEFT;
            }
        }

        return Direction.random();
    }

    @Override
    public String get(Board board) {
        return doSolve(board).toString();
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                "http://206.81.21.158/codenjoy-contest/board/player/f6xu37pvn4jmb45uys6p?code=3261882916347567004",
                new YourSolver(),
                new Board());
    }

    public Direction testMoving(Point head, List<Point> barriers, Board board){
        Point left = head;
        left.setX(left.getX() - 1);

        Point right = head;
        right.setX(right.getX() + 1);

        Point up = head;
        up.setY(up.getY() - 1);

        Point down = head;
        down.setY(down.getY() + 1);

        for(Point x : barriers){
            if (x.equals(left))return Direction.UP;
            if (x.equals(right))return Direction.UP;
            if (x.equals(up))return Direction.LEFT;
            if (x.equals(down))return Direction.LEFT;

        }
        System.out.println("false");
        Direction direction = doSolve((Board) board);
        return direction;
    }

//    public Direction test(List<Point> snake) {
//        for (Point x : snake) {
//            if (x.equals(Direction.LEFT) && x.equals(Direction.UP) && x.equals(Direction.RIGHT)) return Direction.DOWN;
//            if (x.equals(Direction.LEFT) && x.equals(Direction.DOWN) && x.equals(Direction.RIGHT)) return Direction.UP;
//            if (x.equals(Direction.LEFT) && x.equals(Direction.DOWN) && x.equals(Direction.UP)) return Direction.RIGHT;
//            if (x.equals(Direction.UP) && x.equals(Direction.DOWN) && x.equals(Direction.RIGHT)) return Direction.LEFT;
//        }
//        return Direction.random();
//    }
//
//    public boolean testLeft(List<Point> snake) {
//        for (Point x : snake) {
//            if (x.equals(Direction.getValues().get(0))) {
//                System.out.println("false");
//                return false;
//            }
//        }
//        System.out.println("true");
//        return true;
//    }
//
//    public boolean testRight(List<Point> snake) {
//        for (Point x : snake) {
//            if (x.equals(Direction.getValues().get(1))) {
//                System.out.println("false");
//                return false;
//            }
//        }
//        System.out.println("true");
//        return true;
//    }
//
//    public boolean testUP(List<Point> snake) {
//        for (Point x : snake) {
//            //
//            if (x.equals(Direction.getValues().get(2))) {
//                System.out.println("false");
//                return false;
//            }
//        }
//        System.out.println("true");
//        return true;
//    }
//
//    public boolean testDown(List<Point> snake) {
//        for (Point x : snake) {
//            if (x.equals(Direction.getValues().get(3))) {
//                System.out.println("false");
//                return false;
//            }
//        }
//        System.out.println("true");
//        return true;
//    }

}
