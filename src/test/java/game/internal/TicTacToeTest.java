package game.internal;

import game.AppTestContext;
import game.config.Config;
import game.internal.messages.Messages;
import game.internal.player.Player;
import game.internal.turn.TurnHandler;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicTacToeTest {

    private TicTacToe ticTacToe;
    private Config config;

    @Before
    public void before(){
        config = new Config();
        config.setBoardSize(3);
        config.setComputerSymbol('C');
        config.setPlayer1Symbol('A');
        config.setPlayer2Symbol('B');
        config.setComputerPlayerOn(false);

        ticTacToe = new AppTestContext().createTicTacToeApplication(config);
    }

    @Test
    public void shouldGetPrintablePlayers(){
        assertNotNull("Printable players should not be null", ticTacToe.getPrintablePlayersList());
    }

    @Test
    public void printablePlayerListShouldNotBeEmpty(){
        assertFalse("Printable list should not be empty", ticTacToe.getPrintablePlayersList().isEmpty());
    }

    @Test
    public void validMoveShouldPlay(){
        assertEquals("Incorrect message", Messages.GAME_PLAYABLE.getMessage(), ticTacToe.play("1,1").get(0));
    }

    @Test
    public void whenComputerIsNextShouldPlayeAutomatically(){
        AppTestContext contextTest = new AppTestContext();
        config.setComputerPlayerOn(true);

        TurnHandler turnHandler = mock(TurnHandler.class);
        Player computerPlayer = new Player('C', true, "notimportant");
        Player nonComputerPlayer = new Player('A', "notimportant");
        when(turnHandler.getCurrentPlayer()).thenReturn(nonComputerPlayer);
        when(turnHandler.peekNextPlayer()).thenReturn(computerPlayer);

        ticTacToe = new TicTacToe(
                contextTest.createBoard(config),
                contextTest.createPlayers(config),
                turnHandler,
                contextTest.createMoveHandler(),
                contextTest.createPrinter(),
                config.isComputerPlayerOn()
        );

        List<String> messageAfterComputerPlayed = ticTacToe.play("1,1");
        assertTrue("Incorrect message", messageAfterComputerPlayed.get(1).contains(Messages.COMPUTER_TURN.getMessage()));
    }

    @Test
    public void ifNextPlayerIsComputerShouldPlayAutomatically(){
        AppTestContext contextTest = new AppTestContext();
        config.setComputerPlayerOn(true);

        TurnHandler turnHandler = mock(TurnHandler.class);
        Player computerPlayer = new Player('C', true, "notimportant");
        Player nonComputerPlayer = new Player('A', "notimportant");

        when(turnHandler.getCurrentPlayer())
                .thenReturn(computerPlayer)
                .thenReturn(nonComputerPlayer);

        ticTacToe = new TicTacToe(
                contextTest.createBoard(config),
                contextTest.createPlayers(config),
                turnHandler,
                contextTest.createMoveHandler(),
                contextTest.createPrinter(),
                config.isComputerPlayerOn()
        );

        List<String> messageAfterComputerPlayed = ticTacToe.getNextPlayer();
        assertTrue("Incorrect message", messageAfterComputerPlayed.get(0).contains(Messages.COMPUTER_TURN.getMessage()));
    }

    @Test
    public void printableBoardShouldNotBeEmpty(){
        assertFalse("Printable board string should not be empty", ticTacToe.getPrintableBoard().isEmpty());
    }

}