package game.internal.printer;

import game.internal.cell.Cell;
import game.internal.player.Player;
import org.junit.Test;

import java.util.HashMap;

import static game.TestUtils.getCellForPlayer;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class PrinterTest {

    @Test
    public void shouldGetPrintablePlayers(){
        String line = new Printer().getPrintablePlayers(new HashMap<Character, Player>(){{
            put('A', new Player('A', "Player 2"));
        }});

        assertNotNull("Line to print should not be null", line);
        assertTrue("Line should contain player and symbol", line.contains("A") && line.contains("Player 2"));
    }

    @Test
    public void shouldGetPrintableBoard(){
        Cell[][] board = new Cell[3][3];
        board[0] = new Cell[]{
                getCellForPlayer('A'),
                null,
                null
        };
        String line = new Printer().getPrintableBoard(board);

        assertNotNull("Line to print should not be null", line);
        assertTrue("Line should contain player and symbol", line.contains("A"));

    }
}