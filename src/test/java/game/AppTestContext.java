package game;

import game.config.Config;
import game.internal.TicTacToe;
import game.internal.board.Board;
import game.internal.board.logic.CheckColumnWinner;
import game.internal.board.logic.CheckDiagonalWinner;
import game.internal.board.logic.CheckGameDraw;
import game.internal.board.logic.CheckRowWinner;
import game.internal.cell.Cell;
import game.internal.move.MoveHandler;
import game.internal.player.Player;
import game.internal.printer.Printer;
import game.internal.turn.TurnHandler;

import java.util.HashMap;
import java.util.Map;

public class AppTestContext {

    public TicTacToe createTicTacToeApplication(Config config) {
        Map<Character, Player> players = createPlayers(config);

        return new TicTacToe(
                createBoard(config),
                players,
                createTurnHandler(players),
                createMoveHandler(),
                createPrinter(),
                config.isComputerPlayerOn()
        );
    }

    public MoveHandler createMoveHandler() {
        return new MoveHandler();
    }

    public Printer createPrinter() {
        return new Printer();
    }

    public TurnHandler createTurnHandler(Map<Character, Player> players) {
        return new TurnHandler(players);
    }

    public Map<Character, Player> createPlayers(Config config) {
        return new HashMap<Character, Player>(){{
            put(config.getComputerSymbol(), new Player(config.getComputerSymbol(), true, "Computer"));
            put(config.getPlayer1Symbol(), new Player(config.getPlayer1Symbol(), "Player 1"));
            put(config.getPlayer2Symbol(), new Player(config.getPlayer2Symbol(), "Player 2"));
        }};
    }

    public Board createBoard(Config config){
        return new Board(
                new Cell[config.getBoardSize()][config.getBoardSize()],
                new CheckDiagonalWinner(),
                new CheckColumnWinner(),
                new CheckRowWinner(),
                new CheckGameDraw()
        );
    }
}
