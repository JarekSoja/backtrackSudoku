import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class BacktrackSolver {

    boolean isSolved = false;

    void solve() {
        while (true) {
            fillCellsWithoutGuessing();
            System.out.println(Board.getBoardInstance());
            if (Board.isBoardSolved(Board.getBoardInstance())) {
                Commander.boardSolved();
                isSolved = true;
                return;
            } else {
                Cell testedCell = Board.getBoardInstance().returnFirstEmptyCell();
                List<Integer> excludedValues = new ArrayList<>();
                for (Integer value : testedCell.getPossibleValues()) {
                    try {
                        try {
                            Board clonedBoard = Board.getBoardInstance().deepCopy();
                            testedCell.setValue(value);
                            solve();
                            if (isSolved) {
                                Commander.boardSolved();
                                return;
                            } else {
                                Board.setBoardInstance(clonedBoard);
                                excludedValues.add(value);
                            }
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                            Commander.errorMessage();
                        }
                        Commander.errorMessage();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Commander.errorMessage();
                    }
                }
                testedCell.getPossibleValues().removeAll(excludedValues);
            }
        }
    }


    private void fillCellsWithoutGuessing() {
        boolean anythingFilled;
        do {
            System.out.println(Board.getBoardInstance());
            anythingFilled = fillCellsWithOneSolution();
            anythingFilled = anythingFilled || solveRows();
            anythingFilled = anythingFilled || solveColumns();
            anythingFilled = anythingFilled || solveSections();
        } while (anythingFilled);
    }

    private boolean fillCellsWithOneSolution() {
        boolean anythingFilled = false;
        for (int i = Board.BOARD_MIN; i <= Board.BOARD_MAX; i++) {
            for (int j = Board.BOARD_MIN; j <= Board.BOARD_MAX; j++) {
                if (Board.getBoardInstance().getValueOfParticularCell(i, j) == 0) {
                    if (Board.getBoardInstance().getParticularCell(i, j).getPossibleValues().size() == 0) {
                        //TODO ERROR empty possible values while cell has no value
                    } else if (Board.getBoardInstance().getParticularCell(i, j).getPossibleValues().size() == 1) {
                        Board.getBoardInstance().setValueOfParticularCell(i, j, Board.getBoardInstance().getParticularCell(i, j).getPossibleValues().iterator().next());
                        anythingFilled = true;
                    }
                }
            }

        }
        return anythingFilled;
    }

    private boolean solveRows() {
        boolean filledAnyCell = false;
        for (int i = Board.BOARD_MIN; i <= Board.BOARD_MAX; i++) {
            filledAnyCell = filledAnyCell || solveCollection(Board.getBoardInstance().getRows().get(i).getCellsInRow());
        }
        return filledAnyCell;
    }

    private boolean solveColumns() {
        boolean filledAnyCell = false;
        for (int i = Board.BOARD_MIN; i <= Board.BOARD_MAX; i++) {
            filledAnyCell = filledAnyCell || solveCollection(Board.getBoardInstance().getColumns().get(i));
        }
        return filledAnyCell;
    }

    private boolean solveSections() {
        boolean filledAnyCell = false;
        for (int i = Board.BOARD_MIN; i <= Board.BOARD_MAX; i++) {
            filledAnyCell = filledAnyCell || solveCollection(Board.getBoardInstance().getSections().get(i));
        }
        return filledAnyCell;
    }

    //    private boolean solveCollection(List<Cell> collection) {
//        boolean filledAnyCell = false;
//        Cell[] collectionToArray = new Cell[collection.size()];
//        collectionToArray = collection.toArray(collectionToArray);
//        for (int i = 0; i < collectionToArray.length; i++) {
//            int possibleValuesOfCheckedCell = i;
//            Set<Integer> possibleValuesInCollection;
//            possibleValuesInCollection = collectionToArray.stream()
//                    .filter(cell -> collection.indexOf(cell) != possibleValuesOfCheckedCell)
//                    .map(cell -> cell.getValue())
//                    .collect(Collectors.toSet());
//            for (Integer value : collection.get(i).getPossibleValues()) {
//                if (!possibleValuesInCollection.contains(value)) {
//                    Board.getBoardInstance().setValueOfParticularCell(collection.get(i).getRowNumber(), collection.get(i).getColumnNumber(), value);
//                    filledAnyCell = true;
//                    break;
//                }
//            }
//        }
//        return filledAnyCell;
//    }
    private boolean solveCollection(List<Cell> collection) {
        boolean filledAnyCell = false;
        Cell[] cells = new Cell[collection.size()];
        cells = collection.toArray(cells);
        Set<Integer> examinedValues = new HashSet<>();
        for (int i = 0; i < cells.length; i++) {
            for (Integer value : cells[i].getPossibleValues()) {
                if (!examinedValues.contains(value)) {
                    boolean found = false;
                    for (int j = i + 1; j < cells.length; j++) {
                        if (cells[j].getPossibleValues().contains(value)) {
                            examinedValues.add(value);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        Board.getBoardInstance().setValueOfParticularCell(cells[i].getRowNumber(), cells[i].getColumnNumber(), value);
                        filledAnyCell = true;
                        break;
                    }
                }
            }
        }
        return filledAnyCell;
    }
}