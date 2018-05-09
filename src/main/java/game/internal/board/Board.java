package game.internal.board;

import game.internal.cell.Cell;
import game.internal.messages.Messages;
import game.internal.move.Move;

import java.util.function.Function;
import java.util.stream.Stream;

public class Board {

    private final Cell[][] cells;

    private Function<Cell[][], Boolean> checkDiagonalWinner;
    private Function<Cell[][], Boolean> checkColumnWinner;
    private Function<Cell[][], Boolean> checkRowWinner;
    private Function<Cell[][], Boolean> checkGameDraw;


    public Board(Cell[][] cells, Function<Cell[][], Boolean> checkDiagonalWinner,
                 Function<Cell[][], Boolean> checkColumnWinner, Function<Cell[][], Boolean> checkRowWinner,
                 Function<Cell[][], Boolean> checkGameDraw) {
        this.checkDiagonalWinner = checkDiagonalWinner;
        this.checkColumnWinner = checkColumnWinner;
        this.checkRowWinner = checkRowWinner;
        this.checkGameDraw = checkGameDraw;
        this.cells = cells;
    }

    public GameStatus play(Move move) {
        checkValidMove(move);
        checkCellIsFree(move);
        cells[move.getX()][move.getY()] = new Cell(move.getPlayer());

        if(isThereAWinner(cells)){
            return GameStatus.gameOver;
        } else if(isGameDraw(cells)) {
            return GameStatus.draw;
        } else {
            return GameStatus.playable;
        }
    }

    public Cell[][] getCells() {
        return Stream.of(cells)
                .map(row -> Stream.of(row)
                        .map(cell -> cell == null ? cell : new Cell(cell))
                        .toArray(Cell[]::new))
                .toArray(Cell[][]::new);
    }

    /**
     * @param move representing x,y coordinates in a matrix
     *
     * @throws IllegalArgumentException when coordinates are ouside the matrix.
     */
    private void checkValidMove(Move move){
        if (move.getX() >= cells.length
                || move.getY() >= cells[0].length) {
            throw new IllegalArgumentException(Messages.OUTSIDE_THE_WORLD.getMessage());
        }
    }

    /**
     * @param move representing x,y coordinates in a matrix
     *
     * @throws IllegalArgumentException when coordinates point to a not empty cell.
     */
    private void checkCellIsFree(Move move) {
        if(cells[move.getX()][move.getY()] != null){
            throw new IllegalArgumentException(Messages.CELL_NOT_EMPTY.getMessage());
        }
    }

    private boolean isThereAWinner(Cell[][] status) {
        return checkRowWinner.apply(status)
                || checkColumnWinner.apply(status)
                || checkDiagonalWinner.apply(status);
    }

    private boolean isGameDraw(Cell[][] status) {
        return checkGameDraw.apply(status);
    }

}
