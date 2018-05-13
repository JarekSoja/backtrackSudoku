import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Row {

    private final int rowNumber;
    private List<Cell> cellsInRow;

    public Row(int rowNumber) {
        this.rowNumber = rowNumber;
        cellsInRow = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            cellsInRow.add(new Cell(rowNumber, i));
        }
    }
}
