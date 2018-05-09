package game.internal.move;

import game.internal.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MoveHandlerTest {

    private MoveHandler moveHandler;
    private Player player;

    @Before
    public void before() {
        player = new Player('A', "Some name");
        moveHandler = new MoveHandler();
    }
    @Test
    public void shouldCreateMove(){
        assertNotNull(moveHandler.createMoveOrFail("1,1", player));
    }

    @Test
    public void moveShouldBeNormalized(){
        Move move = moveHandler.createMoveOrFail("1,1", player);
        assertTrue("Coordinates should be normalized", move.getX() == 0 && move.getY() == 0);
    }

    @Test
    public void moveShouldContainPlayer(){
        assertEquals("Player in the move is not correct", player,
                moveHandler.createMoveOrFail("1,1", player).getPlayer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfValuesNotSeparatedByComma() {
        moveHandler.createMoveOrFail("1;1", player);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfNumbersAreNegative() {
        moveHandler.createMoveOrFail("-1;1", player);
        moveHandler.createMoveOrFail("1;-1", player);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfValuesNotNumbers() {
        moveHandler.createMoveOrFail("1,a", player);
        moveHandler.createMoveOrFail("a,1", player);
    }
}