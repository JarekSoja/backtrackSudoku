public class Main {

    public static void main(String[] args) {

        Board board = Board.getBoardInstance();
        Processor processor = new Processor();
        Commander.welcomeMessage();

        processor.parseInput();

    }
}
