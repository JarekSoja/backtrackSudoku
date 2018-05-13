import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cell {

    private final static int EMPTY = -1;

    private int rowNumber;
    private int columnNumber;
    private int value = EMPTY;
    private List<Integer> possibleValues = new ArrayList<Integer>() {{
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
        add(6);
        add(7);
        add(8);
        add(9);
    }};

    public Cell(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }


}
