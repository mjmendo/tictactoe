package game.internal.board.logic;

import game.internal.cell.Cell;

import java.util.function.Function;
import java.util.stream.Stream;

public class CheckRowWinner implements Function<Cell[][], Boolean>{

    @Override
    public Boolean apply(Cell[][] board) {
        return Stream.of(board)
                .anyMatch(row -> Stream.of(row)
                        .allMatch(cell -> cell != null && cell.getPlayer().equals(row[0].getPlayer()))
                );
    }
}
