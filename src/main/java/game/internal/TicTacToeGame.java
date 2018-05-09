package game.internal;

import java.util.List;

public interface TicTacToeGame {
    List<String> getNextPlayer();

    List<String> play(String inputMove);

    boolean isDone();

    String getPrintablePlayersList();

    String getPrintableBoard();
}
