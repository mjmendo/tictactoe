package game.internal.turn;

import game.internal.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TurnHandler {
    private Map<Character, Player> players;
    private List<Character> turns;
    private int turn;

    public TurnHandler(Map<Character, Player> players){
        this.players = players;
        turns = new ArrayList<>(players.keySet());
        Collections.shuffle(turns);
        turn = 0;
    }

    public Player getCurrentPlayer(){
        return players.get(turns.get(turn));
    }

    public Player peekNextPlayer(){
        return players.get(turns.get(normalizeTurn(turn + 1)));
    }

    public void next() {
        turn++;
        turn = normalizeTurn(turn);
    }

    private int normalizeTurn(int turn){
        if (turn == turns.size()) {
            return 0;
        }
        return turn;
    }
}
