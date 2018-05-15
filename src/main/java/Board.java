import java.util.ArrayList;
import java.util.List;

public class Board extends Prototype {

    private static Board boardInstance = null;

    final static int BOARD_MIN = 0;
    final static int BOARD_MAX = 8;

    private List<Row> rows = new ArrayList<>();
    private List<List<Cell>> columns;
    private List<List<Cell>> sections;


    private Board() {
        for (int i = BOARD_MIN; i <= BOARD_MAX; i++) {
            rows.add(new Row(i));
        }
        columns = setColumns();
        setSections();
        sections = setSectionsOnceMore();
    }

    static Board getBoardInstance() {
        if (boardInstance == null) {
            boardInstance = new Board();
        }
        return boardInstance;
    }

    static boolean isBoardSolved(Board board) {
        long emptyCells = board.getRows().stream()
                .flatMap(v -> v.getCellsInRow().stream())
                .map(v -> v.getValue())
                .filter(v -> v == 0)
                .count();
        return emptyCells == 0;
    }


    @Override
    public String toString() {

        final String PIPE = "|";
        final String SPACE = " ";
        final String EMPTY_ROW = "\n";

        StringBuilder sb = new StringBuilder();
        for (int i = BOARD_MIN; i <= BOARD_MAX; i++) {
            sb.append(PIPE)
                    .append(SPACE);
            for (int j = BOARD_MIN; j <= BOARD_MAX; j++) {
                sb.append(SPACE)
                        .append(getValueOfParticularCell(i, j))
                        .append(SPACE)
                        .append(PIPE);
            }
            sb.append(EMPTY_ROW)
                    .append(EMPTY_ROW);

        }
        return sb.toString();
    }

    public List<Row> getRows() {
        return rows;
    }

    public List<List<Cell>> setColumns() {
        List<List<Cell>> result = new ArrayList<>();
        for (int i = Board.BOARD_MIN; i <= Board.BOARD_MAX; i++) {
            List<Cell> column = new ArrayList<>();
            for (Row r : getRows()) {
                column.add(getParticularCell(r.getRowNumber(), i));
            }
            result.add(column);
        }
        return result;
    }

    public int getValueOfParticularCell(int row, int column) {
        return this.getRows().get(row).getCellsInRow().get(column).getValue();
    }

    public Cell getParticularCell(int row, int column) {
        return this.getRows().get(row).getCellsInRow().get(column);
    }

    public void setValueOfParticularCell(int row, int column, int value) {
        this.getRows().get(row).getCellsInRow().get(column).setValue(value);
    }


    public Board deepCopy() throws CloneNotSupportedException {
        Board clonedBoard = (Board) super.clone();
//        List<Row> clonedRows = new ArrayList<>();
//        for (int r = Board.BOARD_MIN; r <= Board.BOARD_MAX; r++) {
//            clonedRows.add(Board.getBoardInstance().rows.get(r));
//            for (int c = Board.BOARD_MIN; c <= Board.BOARD_MAX; c++) {
//                clonedRows.get(r).getCellsInRow().add(Board.getBoardInstance().getParticularCell(r, c));
//                List<Integer> clonedPossibleValues = Board.getBoardInstance().getParticularCell(r, c).getPossibleValues();
//                clonedRows.get(r).getCellsInRow().get(c).setPossibleValues(clonedPossibleValues);
//                }
//            }
//        clonedBoard.setRows(clonedRows);
        return clonedBoard;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    Cell returnFirstEmptyCell() {
        for (int i = Board.BOARD_MIN; i <= Board.BOARD_MAX; i++) {
            for (int j = Board.BOARD_MIN; j <= Board.BOARD_MAX; j++) {
                Cell testedCell = getParticularCell(i, j);
                if (testedCell.getValue() == 0) {
                    return testedCell;
                }
            }
        }
        return null;
    }

    public List<List<Cell>> getColumns() {
        return columns;
    }

    public List<List<Cell>> getSections() {
        return sections;
    }

    public void setSections() {
        for (Row row : rows) {
            int indexOfRow = rows.indexOf(row);
            for (Cell cell : row.getCellsInRow()) {
                int indexOfCell = row.getCellsInRow().indexOf(cell);
                if (indexOfCell == 0 || indexOfCell == 1 || indexOfCell == 2) {
                    switch (indexOfRow) {
                        case 0:
                        case 1:
                        case 2:
                            cell.setSectionNumber(0);
                            break;
                        case 3:
                        case 4:
                        case 5:
                            cell.setSectionNumber(3);
                            break;
                        case 6:
                        case 7:
                        case 8:
                            cell.setSectionNumber(4);
                            break;
                    }
                } else if ((indexOfCell == 3 || indexOfCell == 4 || indexOfCell == 5)) {
                    switch (indexOfRow) {
                        case 0:
                        case 1:
                        case 2:
                            cell.setSectionNumber(1);
                            break;
                        case 3:
                        case 4:
                        case 5:
                            cell.setSectionNumber(4);
                            break;
                        case 6:
                        case 7:
                        case 8:
                            cell.setSectionNumber(7);
                            break;
                    }
                } else {
                    switch (indexOfRow) {
                        case 0:
                        case 1:
                        case 2:
                            cell.setSectionNumber(2);
                            break;
                        case 3:
                        case 4:
                        case 5:
                            cell.setSectionNumber(5);
                            break;
                        case 6:
                        case 7:
                        case 8:
                            cell.setSectionNumber(8);
                            break;
                    }
                }
            }
        }
    }

    private List<List<Cell>> setSectionsOnceMore() {
        List<List<Cell>> result = new ArrayList<>();
        for (int k = BOARD_MIN; k <= BOARD_MAX; k++) {
            List<Cell> section = new ArrayList<>();
            for (int j = BOARD_MIN; j <= BOARD_MAX; j++) {
                for (int i = BOARD_MIN; i <= BOARD_MAX; i++) {
                    if (getParticularCell(i, j).getSectionNumber() == j) {
                        section.add(getParticularCell(i, j));
                    }
                }
            }
            result.add(section);
        }
        return result;
    }

    static void setBoardInstance(Board board) {
        boardInstance = board;
    }
}

