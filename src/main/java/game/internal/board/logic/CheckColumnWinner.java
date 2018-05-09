package game.internal.board.logic;

import game.internal.cell.Cell;

import java.util.function.Function;
import java.util.stream.IntStream;

public class CheckColumnWinner implements Function<Cell[][], Boolean> {
    @Override
    public Boolean apply(Cell[][] board) {

        return IntStream.range(0, board.length)
                .anyMatch(col -> IntStream.range(0, board.length)
                        .allMatch(
                                row -> board[row][col] != null
                                        && board[row][col].getPlayer() != null
                                        && board[row][col].getPlayer().equals(board[0][col].getPlayer()))
                );
    }
}
