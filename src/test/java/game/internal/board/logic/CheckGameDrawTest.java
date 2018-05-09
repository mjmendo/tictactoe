package game.internal.board.logic;

import game.internal.cell.Cell;
import org.junit.Assert;
import org.junit.Test;

import static game.TestUtils.getCellForPlayer;

public class CheckGameDrawTest {

    private CheckGameDraw checkGameDraw = new CheckGameDraw();

    @Test
    public void shouldBeDraw(){
        Cell[][] board = new Cell[3][3];

        board[0] = new Cell[]{
                getCellForPlayer('A'),
                getCellForPlayer('A'),
                getCellForPlayer('B')
        };
        board[1] = new Cell[]{
                getCellForPlayer('B'),
                getCellForPlayer('C'),
                null
        };
        board[2] = new Cell[]{
                getCellForPlayer('B'),
                null,
                getCellForPlayer('C')
        };

        Assert.assertTrue("There should be row draw", checkGameDraw.apply(board));
    }


}