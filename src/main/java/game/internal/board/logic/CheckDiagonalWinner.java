package game.internal.board.logic;

import game.internal.cell.Cell;

import java.util.function.Function;
import java.util.stream.IntStream;

public class CheckDiagonalWinner implements Function<Cell[][], Boolean> {

    @Override
    public Boolean apply(Cell[][] board) {

        return IntStream.range(0, board.length)
                    .allMatch(index -> board[index][index] != null
                        && board[index][index].getPlayer() != null
                        && board[index][index].getPlayer().equals(board[0][0].getPlayer()))
                || IntStream.range(0, board.length)
                     .allMatch(row -> board[row][board.length - 1 - row] != null
                                     && board[row][board.length - 1 - row].getPlayer() != null
                                     && board[row][board.length - 1 - row].getPlayer().equals(board[0][board.length - 1].getPlayer())

                     );
    }
}
