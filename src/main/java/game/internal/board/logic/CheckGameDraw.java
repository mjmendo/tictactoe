package game.internal.board.logic;

import game.internal.cell.Cell;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CheckGameDraw implements Function<Cell[][], Boolean> {

    @Override
    public Boolean apply(Cell[][] board) {
        return areRowsDraw(board)
                && areColumnsDraw(board)
                && areDiagonalsDraw(board);
    }

    private boolean areRowsDraw(Cell[][] board) {
        return IntStream.range(0, board.length)
                .allMatch(row -> isADraw(Stream.of(board[row])));
    }

    private boolean areColumnsDraw(Cell[][] board){
        return IntStream.range(0, board.length)
                .allMatch(col -> isADraw(IntStream.range(0, board.length)
                        .mapToObj(row -> board[row][col]))
                );
    }

    private boolean areDiagonalsDraw(Cell[][] board){
        return isADraw(IntStream.range(0, board.length).mapToObj(index -> board[index][index]))
                && isADraw(IntStream.range(0, board.length).mapToObj(index -> board[board.length - 1 - index][index]));

    }

    private boolean isADraw(Stream stream){
        return stream.
                filter(Objects::nonNull)
                .distinct()
                .count() > 1;
    }
}
