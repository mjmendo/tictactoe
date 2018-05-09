package game.internal.move;

import game.internal.player.Player;

public class Move {
    private int x;
    private int y;
    private Player player;

    public Move(Integer x, Integer y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
