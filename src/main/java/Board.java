import java.util.ArrayList;
import java.util.List;

public class Board extends Prototype {

    private static Board boardInstance = null;

    private List<Row> rows;

    private Board() {
        rows = new ArrayList<>();
        for (int i = 1; i < 10; i ++) {
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
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                sb.append(rows.get(i).getCellsInRow().get(j).getValue());
                sb.append("-");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
