package Game.GameTypes;

import Game.Enums.Direction;
import Game.Interfaces.Checks;
import Game.Point;

import java.util.Random;

public class GridGame implements Checks {

    private String[][] board;
    private final int MAX_BOARD_SIZE = 15;

    public GridGame() {
        this.board = new String[6][6];
        for(int i = 0; i < board.length;i++) {
            for(int j = 0; j < board[i].length;j++) {
                board[i][j] = "-";
            }
        }
    }

    public void printBoard() {
        for(int i = 0; i < board.length;i++) {
            for(int j = 0; j < board[i].length;j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public void generateBoard() {

        Random rand = new Random();
        int tileCount = 1;
        int y = 0;
        int prevX = rand.nextInt(board.length - 1);
        Direction[] directionChoices = {
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT
        };
        board[y][prevX] = tileCount++ + "";
        while (y < board.length) {
            boolean directionFound = false;
            while (!directionFound) {
                Direction randomDirection = directionChoices[rand.nextInt(directionChoices.length)];
                System.out.println(randomDirection);
                if (randomDirection == Direction.LEFT && prevX - 1 >= 0 && board[y][prevX - 1].equals("-")) {
                    board[y][prevX - 1] = tileCount + "";
                    prevX -= 1;
                    tileCount++;
                    directionFound = true;
                } else if (randomDirection == Direction.RIGHT && prevX + 1 < board.length && board[y][prevX + 1].equals("-")) {
                    board[y][prevX + 1] = tileCount + "";
                    prevX += 1;
                    tileCount++;
                    directionFound = true;
                } else if (randomDirection == Direction.DOWN) {
                    if (y + 1 >= board.length) {
                        y++;
                        break;
                    }
                    board[++y][prevX] = tileCount + "";
                    tileCount++;
                    directionFound = true;
                }
                if (y + 1 >= board.length) {
                    y++;
                    break;
                }
            }
        }
    }

    @Override
    public boolean isValidMovement(Point givenPoint) {
        int x = givenPoint.getX();
        int y = givenPoint.getY();
        return y < 0 || y > board.length-1 || x < 0 || x > board.length-1;
    }

    @Override
    public boolean isWinner() {
        return false;
    }
}