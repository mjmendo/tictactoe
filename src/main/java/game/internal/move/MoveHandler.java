package game.internal.move;

import game.internal.messages.Messages;
import game.internal.player.Player;

public class MoveHandler {

    /**
     *
     * @param move
     * @param player
     * @return valid move if creation possible
     *
     * @throws IllegalArgumentException when
     *   1) input string not separated by commas
     *   2) input are not numbers
     */
    public Move createMoveOrFail(String move, Player player) {
        if (!move.contains(",")) throw new IllegalArgumentException(Messages.BAD_ENTRY.getMessage());

        String x = move.split(",")[0];
        String y = move.split(",")[1];

        if (!isValidNumber(x) || !isValidNumber(y)){
            throw new IllegalArgumentException(Messages.BAD_ENTRY.getMessage());
        }

        return new Move(
                normalizeFromHumanCoordinates(x),
                normalizeFromHumanCoordinates(y),
                player
        );
    }

    private boolean isValidNumber(String x) {
        return x.trim().matches("^[1-9]\\d*$");
    }

    private int normalizeFromHumanCoordinates(String i){
        return Integer.valueOf(i) - 1;
    }
}
