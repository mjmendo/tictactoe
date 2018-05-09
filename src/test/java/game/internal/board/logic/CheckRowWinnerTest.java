package game.internal.board.logic;

import game.internal.cell.Cell;
import org.junit.Assert;
import org.junit.Test;

import static game.TestUtils.getCellForPlayer;

public class CheckRowWinnerTest {

    CheckRowWinner checkRowWinner = new CheckRowWinner();

    @Test
    public void nullCellsMeansNoWinner() {
        Cell[][] board = new Cell[3][3];

        board[0] = new Cell[]{getCellForPlayer('A'), null, null};

        Assert.assertFalse("There should be no winner", checkRowWinner.apply(board));

    }

    @Test
    public void samePlayerInAllCellsInRowIsWinner() {
        Cell[][] board = new Cell[3][3];
        board[0] = new Cell[]{
                getCellForPlayer('A'),
                getCellForPlayer('A'),
                getCellForPlayer('A')
        };
        Assert.assertTrue("There should be a winner", checkRowWinner.apply(board));

    }

    @Test
    public void differentPlayersInRowIsNoWinner() {
        Cell[][] board = new Cell[3][3];
        board[0] = new Cell[]{
                getCellForPlayer('A'),
                getCellForPlayer('B'),
                getCellForPlayer('A')
        };
        Assert.assertFalse("There should be a winner", checkRowWinner.apply(board));
    }
}