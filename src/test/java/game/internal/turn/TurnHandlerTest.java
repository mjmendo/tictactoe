package game.internal.turn;

import game.internal.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TurnHandlerTest {

    private TurnHandler turnHandler;

    @Before
    public void before(){
        turnHandler = new TurnHandler(new HashMap<Character, Player>() {{
            put('A', new Player('A', "Some name"));
            put('B', new Player('B', "Some name"));
            put('C', new Player('C', "Some name"));
        }});

    }

    @Test
    public void shouldAlternateTurns(){
        Player player1 = turnHandler.getCurrentPlayer();
        turnHandler.next();
        assertNotEquals("Current player is incorrect", player1, turnHandler.getCurrentPlayer());
    }

    @Test
    public void shouldStartOverWhenPlayerListIsDone(){
        Player player1 = turnHandler.getCurrentPlayer();
        turnHandler.next();
        Player player2 = turnHandler.getCurrentPlayer();
        turnHandler.next();
        Player player3 = turnHandler.getCurrentPlayer();
        Player player1Bis = turnHandler.peekNextPlayer();

        assertFalse("Current player is incorrect", player1.equals(player2));
        assertFalse("Current player is incorrect", player2.equals(player3));
        assertFalse("Current player is incorrect", player3.equals(player1Bis));
        assertEquals("Current player is incorrect", player1, player1Bis);
    }
}