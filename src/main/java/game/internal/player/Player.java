package game.internal.player;

public class Player {
    private boolean isComputer;
    private char symbol;
    private String name;

    public Player(char playerSymbol, String name){
        symbol = playerSymbol;
        isComputer = false;
        this.name = name;
    }

    public Player(Player original) {
        this.isComputer = original.isComputer;
        this.symbol = original.symbol;
        this.name = new String(original.name);
    }

    public Player(char playerSymbol, boolean isComputer, String name){
        symbol = playerSymbol;
        this.isComputer = isComputer;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isComputer() {
        return isComputer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (isComputer() != player.isComputer()) return false;
        if (getSymbol() != player.getSymbol()) return false;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        int result = isComputer() ? 1 : 0;
        result = 31 * result + (int) getSymbol();
        result = 31 * result + name.hashCode();
        return result;
    }
}
