package game.internal;

import game.internal.board.Board;
import game.internal.board.GameStatus;
import game.internal.messages.Messages;
import game.internal.move.Move;
import game.internal.move.MoveHandler;
import game.internal.player.Player;
import game.internal.printer.Printer;
import game.internal.turn.TurnHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TicTacToe implements TicTacToeGame {

    private final boolean isComputerPlayerOn;
    private Board board;
    private GameStatus gameStatus;
    private Map<Character, Player> players;
    private TurnHandler turnHandler;
    private Printer printer;
    private MoveHandler moveHandler;

    public TicTacToe(Board board, Map<Character, Player> players,
                     TurnHandler turnHandler, MoveHandler moveHandler,
                     Printer printer, boolean computerPlayerOn) {
        this.board = board;
        this.players = players;
        this.turnHandler = turnHandler;
        this.printer = printer;
        this.moveHandler = moveHandler;
        this.isComputerPlayerOn = computerPlayerOn;
    }

    @Override
    public List<String> getNextPlayer() {
        List<String> messages = new ArrayList<>();
        if (turnHandler.getCurrentPlayer().isComputer()){
            messages.add(playComputerIfEnabled(turnHandler.getCurrentPlayer()));
        }
        messages.add(Messages.NEXT_PLAYER.getMessage() + turnHandler.getCurrentPlayer().getSymbol());
        return messages;
    }

    @Override
    public List<String> play(String inputMove){
        Move move = moveHandler.createMoveOrFail(inputMove, turnHandler.getCurrentPlayer());
        List<String> messagesToTheExterior = new ArrayList<>();

        messagesToTheExterior.add(play(move));
        if (turnHandler.peekNextPlayer().isComputer() && isComputerPlayerOn){
            messagesToTheExterior.add(playComputerIfEnabled(turnHandler.peekNextPlayer()));
        }
        turnHandler.next();
        return messagesToTheExterior;
    }

    @Override
    public String getPrintablePlayersList() {
        return printer.getPrintablePlayers(players);
    }

    @Override
    public String getPrintableBoard() {
        return printer.getPrintableBoard(board.getCells());
    }

    @Override
    public boolean isDone() {
        return gameStatus == GameStatus.draw ||
                gameStatus == GameStatus.gameOver;
    }

    private String play(Move move) {
        gameStatus = board.play(move);

        switch (gameStatus){
            case draw:
                return Messages.GAME_DRAW.getMessage();
            case gameOver:
                return Messages.GAME_OVER.getMessage() + turnHandler.getCurrentPlayer().getName();
            case playable:
            default:
                return Messages.GAME_PLAYABLE.getMessage();
        }
    }

    private String playComputerIfEnabled(Player currentPlayer){
        turnHandler.next();
        return computerPlay(currentPlayer);
    }

    private int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }

    private String computerPlay(Player currentPlayer){
        while (true){
            try {
                Move move = new Move(
                        getRandomNumberInRange(1,board.getCells().length),
                        getRandomNumberInRange(0,board.getCells().length),
                        currentPlayer
                );

                return Messages.COMPUTER_TURN.getMessage() + "\n" + play(move);
            } catch(Exception e){
                //shhh....
            }
        }
    }
}
