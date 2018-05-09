package game;

import game.internal.cell.Cell;
import game.internal.player.Player;

public class TestUtils {

    public static Cell getCellForPlayer(char symbol) {
        return new Cell(new Player(symbol, "player"));
    }
}
