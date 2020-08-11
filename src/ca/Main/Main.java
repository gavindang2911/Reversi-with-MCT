//Assignment 5
//Project Topic: Reversi with Monte-Carlo Tree Search
// Project Members: Hoai Dang, Manav Sharma(301368441)

//--------------------------------------------------------------------------------------------------------------

// Main Class for the whole programm
package ca.Main;

import ca.reversi.Board;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ca.reversi.Reversi.startGame;

public class Main {

    // Variables declaired
    public static int point_white = 0;
    public static int point_black = 0;
    public static int draw = 0;

    public static void main(String[] args) {
        System.out.println("--------- R E V E R S I ---------- \n");
        System.out.println("--------- Brace Yourself ---------- \n");
        //Choose from the options of Version1 and version2
        System.out.println("Choose from given menu:\n1.AI (Black) vs Player (White)\n2.AI (non-heuristic-Black) vs AI (heuristic-White)");
        int userInput;
        while (true) {
            System.out.print("> ");
            Scanner scan = new Scanner(System.in);

            try {
                userInput = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException exc) {
                System.out.println("Caution!! Choose a valid option");
                continue;
            }
            if (userInput == 1 || userInput == 2) {
                break;
            }
            System.out.println("Caution!! Choose a valid option");
        }

        int sampleSize = 1;
        if (userInput == 2) {
            //Extra condition for play-out
            System.out.print("Input the size of sample for matches: ");
            Scanner scanner = new Scanner(System.in);
            sampleSize = scanner.nextInt();
            scanner.nextLine();
        }
        for (int i = 0; i < sampleSize; i++) {
            Board b = new Board();
            startGame(b, userInput);
        }
        System.out.println("End Result: White " + point_white + "-" + point_black + " Black\nDraw: "+ draw);
        System.out.print("Thanks for Playing, Continued---");

    }
}
