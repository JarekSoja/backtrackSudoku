import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BacktrackSolver {


    Board fillCellsWithoutGuessing(Board board) {
        boolean anythingFilled;
        do {
            anythingFilled = fillCellsWithOneSolution(board);
            anythingFilled = anythingFilled || solveRows(board);
            anythingFilled = anythingFilled || solveColumns(board);
            anythingFilled = anythingFilled || solveSections(board);
        } while (anythingFilled);

        return board;
    }

    private boolean fillCellsWithOneSolution(Board board) {
        boolean anythingFilled = false;
        for (int i = Board.BOARD_MIN; i <= Board.BOARD_MAX; i++) {
            for (int j = Board.BOARD_MIN; j <= Board.BOARD_MAX; j++) {
                if (board.getValueOfParticularCell(i, j) == 0) {
                    if (board.getParticularCell(i, j).getPossibleValues().size() == 0) {
                        //TODO ERROR empty possible values while cell has no value
                    } else if (board.getParticularCell(i, j).getPossibleValues().size() == 1) {
                        board.setValueOfParticularCell(i, j, board.getParticularCell(i, j).getPossibleValues().iterator().next());
                        anythingFilled = true;
                    }
                }
            }

        }
        return anythingFilled;
    }

    private boolean solveRows(Board board) {
        boolean filledAnyCell = false;
        for (int i = Board.BOARD_MIN; i <= Board.BOARD_MAX; i++) {
            filledAnyCell = filledAnyCell || solveCollection(board.getRows().get(i).getCellsInRow());
        }
        return filledAnyCell;
    }

    private boolean solveColumns(Board board) {
        boolean filledAnyCell = false;
        for (int i = Board.BOARD_MIN; i <= Board.BOARD_MAX; i++) {
            filledAnyCell = filledAnyCell || solveCollection(board.getColumns().get(i));
        }
        return filledAnyCell;
    }

    private boolean solveSections(Board board) {
        boolean filledAnyCell = false;
        for (int i = Board.BOARD_MIN; i <= Board.BOARD_MAX; i++) {
            filledAnyCell = filledAnyCell || solveCollection(board.getSections().get(i));
        }
        return filledAnyCell;
    }

    private boolean solveCollection(List<Cell> collection) {
        boolean filledAnyCell = false;
        for (int i = 0; i <= collection.size(); i++) {
            int possibleValuesOfCheckedCell = i;
            Set<Integer> possibleValuesInCollection;
            possibleValuesInCollection = collection.stream()
                    .filter(cell -> collection.indexOf(cell) != possibleValuesOfCheckedCell)
                    .map(cell -> cell.getValue())
                    .collect(Collectors.toSet());
            for (Integer value : collection.get(i).getPossibleValues()) {
                if (!possibleValuesInCollection.contains(value)) {
                    Board.getBoardInstance().setValueOfParticularCell(collection.get(i).getRowNumber(), collection.get(i).getColumnNumber(), value);
                    filledAnyCell = true;
                    break;
                }
            }
        }
        return filledAnyCell;
    }
}