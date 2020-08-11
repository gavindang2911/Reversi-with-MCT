package ca.reversi;

import ca.AI.AI;
import ca.AI.HeuristicAI;
import ca.Main.Main;

import java.util.HashSet;
import java.util.Scanner;

public class Reversi {
    private static Scanner scanner = new Scanner(System.in);
    private static Move nextMove = new Move(-1, -1);

    public static void startGame(Board board, int twoAIPlayers) {
        System.out.println("Black player moves first in the game");

        int gameResult;
        String userInput;

        while (true) {
            HashSet<Move> validMovesBlack = board.getValidateMoveList('B', 'W');
            HashSet<Move> validMovesWhite = board.getValidateMoveList('W', 'B');

            board.displayNextValidMoves(validMovesBlack);
            gameResult = board.getResult();

            if (gameResult != -1) {
                if (gameResult == 0) {
                    System.out.println("It is a draw.");
                    Main.draw++;
                    break;
                } else if (gameResult == 1) {
                    System.out.println("White wins: " + board.getWhiteTotal() + " to " + board.getBlackTotal());
                    Main.white_win++;
                    break;
                } else if (gameResult == 2) {
                    System.out.println("Black wins: " + board.getBlackTotal() + ":" + board.getWhiteTotal());
                    Main.black_win++;
                    break;
                }
                break;
            }

            if(validMovesBlack.isEmpty()){
                System.out.println("There is no valid moves for Black player, Switch to White player");
            } else {
                Board newBoard = new Board(board);
                AI nonHeuristicAI = new AI();

                nonHeuristicAI.placeNextMove(newBoard);
                nextMove = nonHeuristicAI.determineNextMove();
                board.move(nextMove, 'B', 'W');
                gameResult = board.getResult();
                System.out.println("\nBlack: "+ board.getBlackTotal()+" White: "+board.getWhiteTotal());
            }

            validMovesWhite = board.getValidateMoveList('W', 'B');
            validMovesBlack = board.getValidateMoveList('B', 'W');

            board.displayNextValidMoves(validMovesWhite);
            gameResult = board.getResult();
            if (gameResult != -1) {
                if (gameResult == 0) {
                    System.out.println("It is a draw.");
                    Main.draw++;
                    break;
                } else if (gameResult == 1) {
                    System.out.println("White wins: " + board.getWhiteTotal() + " to " + board.getBlackTotal());
                    Main.white_win++;
                    break;
                } else if (gameResult == 2) {
                    System.out.println("Black wins: " + board.getBlackTotal() + ":" + board.getWhiteTotal());
                    Main.black_win++;
                    break;
                }
                break;
            }

            if(validMovesWhite.isEmpty()){
                System.out.println("There is no valid moves for White player, Switch to White player");
            } else {
                if (twoAIPlayers == 2) {
                    Board newBoard = new Board(board);
                    HeuristicAI heuristicAI = new HeuristicAI();
                    heuristicAI.placeNextMove(newBoard);
                    nextMove = heuristicAI.determineNextMove();
                } else {
                    System.out.println("Place your move (White player): ");
                    userInput = scanner.next();
                    int x = board.checkTheXCoordinate(userInput.charAt(0));
                    nextMove.setY(x);
                    int y = Integer.parseInt(userInput.charAt(1) + "") - 1;
                    nextMove.setX(y);

                    System.out.println("next moves" + validMovesWhite);
                    while (!validMovesWhite.contains(nextMove)) {
                        System.out.println("You cannot go there! Invalid move!\n Place new move (White Player): ");
                        userInput = scanner.next();
                        nextMove.setY(board.checkTheXCoordinate(userInput.charAt(0)));
                        nextMove.setX(Integer.parseInt(userInput.charAt(1) + "") - 1);
                    }
                }

                board.move(nextMove, 'W', 'B');
                gameResult = board.getResult();
                System.out.println("\nWhite: "+ board.getWhiteTotal() +" Black: "+board.getBlackTotal());
            }


        }
    }
}
