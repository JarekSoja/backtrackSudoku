import java.util.Scanner;

public class Processor {

    private Board board = Board.getBoardInstance();
    private Scanner reader = new Scanner(System.in);
    private BacktrackSolver solver = new BacktrackSolver();

    public void parseInput() {
        while(true) {
            Commander.menu();
            String input = reader.next();
            switch (input.toLowerCase()) {
                case "sudoku":
                    solveSudoku();
                    break;
                case "n":
                    newGame();
                    break;
                case "x":
                    exitGame();
                default:
                    if (input.chars().allMatch(Character::isDigit)) {
                        if (input.length() == 3) {
                            char[] inputArr = input.toCharArray();
                            placeUSerDigitOnBoard(Character.getNumericValue(inputArr[0]), Character.getNumericValue(inputArr[1]), Character.getNumericValue(inputArr[2]));
                        } else {
                            Commander.errorMessage();
                        }
                    } else
                        Commander.errorMessage();
            }
        }
    }

    public void placeUSerDigitOnBoard(int column, int row, int value) {
        Cell parsedCell = board.getParticularCell(row - 1, column - 1);
        parsedCell.setValue(value);
        parsedCell.removeOtherPossibleValues(value);
    }

    public void solveSudoku() {
        System.out.println(board);
    }

    public void newGame() {
        //TODO New game
    }

    public void exitGame() {
        System.exit(1);
    }

}
