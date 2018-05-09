package game.internal.board.logic;

import game.internal.cell.Cell;
import org.junit.Assert;
import org.junit.Test;

import static game.TestUtils.getCellForPlayer;

public class CheckColumnWinnerTest {

    private CheckColumnWinner checkColumnWinner = new CheckColumnWinner();

    @Test
    public void nullCellsMeansNoWinner() {
        Cell[][] board = new Cell[3][3];

        board[0] = new Cell[]{getCellForPlayer('A'), null, null};
        board[1] = new Cell[]{getCellForPlayer('A'), null, null};
        board[2] = new Cell[]{null, null, null};

        Assert.assertFalse("There should be no winner", checkColumnWinner.apply(board));

    }

    @Test
    public void differentPlayersMeansNoWinner() {
        Cell[][] board = new Cell[3][3];

        board[0] = new Cell[]{getCellForPlayer('A'), null, null};
        board[1] = new Cell[]{getCellForPlayer('B'), null, null};
        board[1] = new Cell[]{getCellForPlayer('A'), null, null};

        Assert.assertFalse("There should be no winner", checkColumnWinner.apply(board));

    }


    @Test
    public void samePlayersMeansWinner() {
        Cell[][] board = new Cell[3][3];

        board[0] = new Cell[]{getCellForPlayer('A'), null, null};
        board[1] = new Cell[]{getCellForPlayer('A'), null, null};
        board[2] = new Cell[]{getCellForPlayer('A'), null, null};

        Assert.assertTrue("There should be a winner", checkColumnWinner.apply(board));

    }

}