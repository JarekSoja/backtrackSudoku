import java.util.ArrayList;
import java.util.List;

public class Row {

    private final int rowNumber;
    private List<Cell> cellsInRow;

    public Row(int rowNumber) {
        this.rowNumber = rowNumber;
        cellsInRow = new ArrayList<>();
        for (int i = Board.BOARD_MIN; i <= Board.BOARD_MAX; i++) {
            cellsInRow.add(new Cell(rowNumber, i));
        }
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public List<Cell> getCellsInRow() {
        return cellsInRow;
    }
}
