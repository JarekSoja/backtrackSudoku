
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cell {

    private final static int EMPTY = 0;

    private int rowNumber;
    private int columnNumber;
    private int sectionNumber;
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

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        removeOtherPossibleValues(value);
    }


    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(List<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }

    void removeOtherPossibleValues(int value) {
        List<Integer> possibleValuesAfterTesting = possibleValues.stream()
                .filter(v -> v == value)
                .collect(Collectors.toList());
        possibleValues.clear();
        possibleValues.addAll(possibleValuesAfterTesting);
    }

    void setSectionNumber(int number) {
        sectionNumber = number;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }
}
