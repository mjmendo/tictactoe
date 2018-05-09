package game.internal.board;

import game.internal.cell.Cell;
import game.internal.move.Move;
import game.internal.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BoardTest {

    private Board board;
    private Player player;

    private Function<Cell[][], Boolean> checkerAlwaysFalse = cells -> false;
    private Function<Cell[][], Boolean> checkerAlwaysTrue = cells -> true;

    @Before
    public void before(){
        board = new Board(new Cell[3][3], checkerAlwaysFalse, checkerAlwaysFalse, checkerAlwaysFalse, checkerAlwaysFalse);
        player = new Player('A', "Some name");
    }

    @Test
    public void shouldPersistMove(){
        board.play(new Move(0, 0, player));
        assertEquals("Persisted player is incorrect", player, board.getCells()[0][0].getPlayer());
    }

    @Test
    public void statusShouldBePlayable(){
        assertEquals("Game status is incorrect", GameStatus.playable, board.play(new Move(0, 0, player)));
    }

    @Test
    public void statusShouldBeDraw(){
        board = new Board(new Cell[3][3], checkerAlwaysFalse, checkerAlwaysFalse, checkerAlwaysFalse, checkerAlwaysTrue);
        assertEquals("Game status is incorrect", GameStatus.draw, board.play(new Move(0, 0, player)));
    }

    @Test
    public void statusShouldBeOver(){
        board = new Board(new Cell[3][3], checkerAlwaysFalse, checkerAlwaysFalse, checkerAlwaysTrue, checkerAlwaysFalse);
        assertEquals("Game status is incorrect", GameStatus.gameOver, board.play(new Move(0, 0, player)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidMoveShouldThrowException(){
        board.play(new Move(4, 0, player));
    }

    @Test(expected = IllegalArgumentException.class)
    public void alreadyUsedCellShouldThrowException(){
        board.play(new Move(1, 0, player));
        board.play(new Move(1, 0, player));
    }

    @Test
    public void cellsForPrintingShouldBeACopy(){
        Cell[][] cells = new Cell[3][3];
        board = new Board(cells, checkerAlwaysFalse, checkerAlwaysFalse, checkerAlwaysFalse, checkerAlwaysFalse);
        board.play(new Move(0, 1, player));
        assertFalse("Cells should not be the same object", cells == board.getCells());
        assertFalse("Cells should not be the same object", cells[0] == board.getCells()[0]);
        assertFalse("Player should not be the same object", player == board.getCells()[0][1].getPlayer());
    }
}