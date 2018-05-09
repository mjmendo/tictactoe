package game.internal.messages;

public enum Messages {
    //status
    GAME_DRAW("Bad news: game is a draw"),
    GAME_OVER("We have a winner! Congratz, "),
    GAME_PLAYABLE("Nice! Game is still on, play!"),

    //messages
    COMPUTER_TURN("Computer turn! Playing automatically..."),
    NEXT_PLAYER("Next player: "),

    //errors
    BAD_ENTRY("Entries must be positive numbers separated by a comma"),
    OUTSIDE_THE_WORLD("Trying to move outside the board."),
    CELL_NOT_EMPTY("Cell is not empty. Pick another one."),

    ;

    public String getMessage() {
        return message;
    }

    private final String message;

    Messages(String message) {
        this.message = message;
    }
}
