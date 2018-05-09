package game.internal.cell;

import game.internal.player.Player;

public class Cell {
    private Player player;

    public Cell(Cell original) {
        player = new Player(original.player);
    }

    public Cell(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return getPlayer() != null ? getPlayer().equals(cell.getPlayer()) : cell.getPlayer() == null;
    }

    @Override
    public int hashCode() {
        return getPlayer() != null ? getPlayer().hashCode() : 0;
    }
}
