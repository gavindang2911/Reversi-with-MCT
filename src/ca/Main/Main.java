package ca.Main;

import ca.reversi.Board;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ca.reversi.Reversi.startGame;

public class Main {

    public static int white_win = 0;
    public static int black_win = 0;
    public static int draw = 0;

    public static void main(String[] args) {
        System.out.println("--------- R E V E R S I ---------- \n");
        System.out.println("Please choose an option:\n1.AI (Black) vs Player (White)\n2.AI (non-heuristic-Black) vs AI (heuristic-White)");
        int userInput;
        while (true) {
            System.out.print("> ");
            Scanner scan = new Scanner(System.in);

            try {
                userInput = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException exc) {
                System.out.println("Please choose an appropiate option");
                continue;
            }
            if (userInput == 1 || userInput == 2) {
                break;
            }
            System.out.println("Please choose an appropiate option");
        }

        int sampleSize = 1;
        if (userInput == 2) {
            System.out.print("Please choose the sample size (how match matches): ");
            Scanner scanner = new Scanner(System.in);
            sampleSize = scanner.nextInt();
            scanner.nextLine();
        }
        for (int i = 0; i < sampleSize; i++) {
            Board b = new Board();
            startGame(b, userInput);
        }
        System.out.println("Summary: White " + white_win + "-" + black_win + " Black\nDraw: "+ draw);

    }
}
