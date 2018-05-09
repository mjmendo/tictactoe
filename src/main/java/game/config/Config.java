package game.config;

public class Config {
    private int boardSize;
    private char Player1Symbol;
    private char Player2Symbol;
    private char ComputerSymbol;

    public boolean isComputerPlayerOn() {
        return isComputerPlayerOn;
    }

    public void setComputerPlayerOn(boolean computerPlayerOn) {
        isComputerPlayerOn = computerPlayerOn;
    }

    private boolean isComputerPlayerOn;

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public char getPlayer1Symbol() {
        return Player1Symbol;
    }

    public void setPlayer1Symbol(char player1Symbol) {
        Player1Symbol = player1Symbol;
    }

    public char getPlayer2Symbol() {
        return Player2Symbol;
    }

    public void setPlayer2Symbol(char player2Symbol) {
        Player2Symbol = player2Symbol;
    }

    public char getComputerSymbol() {
        return ComputerSymbol;
    }

    public void setComputerSymbol(char computerSymbol) {
        ComputerSymbol = computerSymbol;
    }
}
