package game.internal.printer;

import game.internal.cell.Cell;
import game.internal.player.Player;

import java.util.Map;
import java.util.stream.Collectors;

public class Printer {

    private static String NEW_LINE = "\n";

    public String getPrintablePlayers(Map<Character, Player> players){
        return new StringBuffer()
                .append("Players of this Tic-Tac-Toe game are: ").append(NEW_LINE)
                .append(players.keySet().stream()
                        .map( key -> players.get(key))
                        .map(player -> player.getName() + ": " + player.getSymbol())
                        .sorted()
                        .collect(Collectors.joining(NEW_LINE)))
                .toString();
    }

    public String getPrintableBoard(Cell[][] cells ){
        StringBuffer printableBoard = new StringBuffer();
        for (int row = 0; row < cells.length; row++){
            String line = " ";
            for (int col = 0; col < cells.length; col++){
                line += cells[row][col] == null ? " " : cells[row][col].getPlayer().getSymbol();
                if (col + 1  < cells.length) {
                    line += " | ";
                } else {
                    line += "\n";
                }
            }
            printableBoard.append(line);
            if (row + 1 < cells.length){
                printableBoard.append(new String(new char[line.length()]).replace("\0", "-") + "\n");
            }
        }

        return printableBoard.toString();
    }
}
