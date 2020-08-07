import java.util.Scanner;

public class Game {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the difficulty level\n" +
                "Press 0 for BEGINNER(9 * 9 Cells and 10 Mines)\n" +
                "Press 1 for INTERMEDIATE(16 * 16 Cells and 40 Mines)\n" +
                "Press 2 for ADVANCED(24 * 24 Cells and 99 Mines):\n-> ");


        Mines mines = new Mines(scanner.nextInt());
        mines.setup();
        mines.placeMines();
        mines.finalPrintMines();

        boolean boom = false;
        boolean firstChoice = true;
        while (mines.hasUnRevealed()) {
            System.out.println("Enter your move, (rol,col)\n-> ");

            String line = scanner.next();
            String[] line1 = line.split(",");

            int x = Integer.valueOf(line1[0]);
            int y = Integer.valueOf(line1[1]);
            if (mines.mines[x][y] == 1) {
                if (firstChoice) {
                    firstChoice = false;
                    System.out.println("Try again.");
                    continue;
                }
                boom = true;
                break;
            }
            mines.reveal(x, y);
            mines.printMines();
        }

        if (boom) {
            System.out.println("You lose!");
            mines.finalPrintMines();

        } else {
            System.out.println("You win");
            mines.finalPrintMines();
        }


    }
}
