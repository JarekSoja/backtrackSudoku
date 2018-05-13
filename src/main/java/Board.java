import java.util.ArrayList;
import java.util.List;

public class Board extends Prototype {

    private static Board boardInstance = null;

    public final static int BOARD_MIN = 0;
    public final static int BOARD_MAX = 8;

    private List<Row> rows;

    private Board() {
        rows = new ArrayList<>();
        for (int i = BOARD_MIN; i <= BOARD_MAX; i++) {
            rows.add(new Row(i));
        }
    }

    public static Board getBoardInstance() {
        if (boardInstance == null) {
            boardInstance = new Board();
        }
        return boardInstance;
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

    public List<List<Cell>> getColumns() {
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

    public List<List<Cell>> getSections() {
        List<List<Cell>> result = new ArrayList<>();
        int startingRow = 0;
        int startingColumn = 0;
        for (int i = startingRow * 3; i < startingRow * 3 + 3; i++) {
            List<Cell> section = new ArrayList<>();
            for (int j = startingColumn * 3; j < startingColumn * 3 + 3; j++) {
                section.add(getParticularCell(i, j));
            }
            result.add(section);
        }
        return result;
    }

        public int getValueOfParticularCell ( int row, int column){
            return this.getRows().get(row).getCellsInRow().get(column).getValue();
        }

        public Cell getParticularCell ( int row, int column){
            return this.getRows().get(row).getCellsInRow().get(column);
        }

        public void setValueOfParticularCell ( int row, int column, int value){
            this.getRows().get(row).getCellsInRow().get(column).setValue(value);
        }
    }

