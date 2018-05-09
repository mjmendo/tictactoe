package game.internal.board.logic;

import game.internal.cell.Cell;
import org.junit.Assert;
import org.junit.Test;

import static game.TestUtils.getCellForPlayer;

public class CheckDiagonalWinnerTest {

    private CheckDiagonalWinner checkDiagonalWinner = new CheckDiagonalWinner();

    @Test
    public void samePlayerMeansWinner() {
        Cell[][] board = new Cell[3][3];

        board[0] = new Cell[]{getCellForPlayer('A'), null, null};
        board[1] = new Cell[]{null, getCellForPlayer('A'), null};
        board[2] = new Cell[]{null, null, getCellForPlayer('A')};

        Assert.assertTrue("There should be a winner", checkDiagonalWinner.apply(board));

    }

    @Test
    public void samePlayerInvertedMeansWinner() {
        Cell[][] board = new Cell[3][3];

        board[0] = new Cell[]{null, null, getCellForPlayer('A')};
        board[1] = new Cell[]{null, getCellForPlayer('A'), null};
        board[2] = new Cell[]{getCellForPlayer('A'), null, null};

        Assert.assertTrue("There should be a winner", checkDiagonalWinner.apply(board));
    }

    @Test
    public void differentPlayerMeansNoWinner() {
        Cell[][] board = new Cell[3][3];

        board[0] = new Cell[]{null, null, getCellForPlayer('A')};
        board[1] = new Cell[]{null, getCellForPlayer('B'), null};
        board[2] = new Cell[]{getCellForPlayer('A'), null, null};

        Assert.assertFalse("There should be no winner", checkDiagonalWinner.apply(board));
    }

    @Test
    public void nullDiagonalMeansNoWinner() {
        Cell[][] board = new Cell[3][3];

        board[0] = new Cell[]{getCellForPlayer('A'), null, null};
        board[1] = new Cell[]{null, null, null};
        board[2] = new Cell[]{null, null, getCellForPlayer('A')};

        Assert.assertFalse("There should be no winner", checkDiagonalWinner.apply(board));

    }

    @Test
    public void nullDiagonalInvertedMeansNoWinner() {
        Cell[][] board = new Cell[3][3];

        board[0] = new Cell[]{null, null, getCellForPlayer('A')};
        board[1] = new Cell[]{null, null, null};
        board[2] = new Cell[]{getCellForPlayer('A'), null, null};

        Assert.assertFalse("There should be no winner", checkDiagonalWinner.apply(board));
    }

}