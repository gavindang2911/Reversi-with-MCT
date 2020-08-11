// Heuristic AI used for version 2
package ca.AI;

import ca.reversi.Board;
import ca.reversi.Move;

import java.util.HashSet;
import java.util.Random;

import static java.lang.Math.abs;

public class HeuristicAI {
    // Variable used for AI
    private static HashSet<Move> moveList = new HashSet<>();
    private static final char coordinateX[] = new char[] {'A','B','C','D','E','F','G','H'};
    private static  final int weightedBoard [][] = new int[] [] {
            {100, -1, 5, 2, 2, 5, -1, 100,},
            {-1, -10, 1, 1, 1, 1, -10, -1,},
            {5, 1, 1, 1, 1, 1, 1, 5,},
            {2 , 1, 1, 0, 0, 1, 1, 2,},
            {2, 1, 1, 0, 0, 1, 1, 2,},
            {5, 1, 1, 1, 1, 1, 1, 1,},
            {-1, -10 , 1, 1, 1, 1, -10, -1,},
            {100, -1, 5, 2, 2, 5, -1, 100,},
    };
 // Helper Function Used for checking what will be the next move
    public static Move determineNextMove() {
        Move move = null;
        double highestScore = 0;

        for (Move index:moveList) {
            if (abs(index.win+weightedBoard[index.getX()][index.getY()])>=highestScore){
                move = index;
                highestScore = index.win+weightedBoard[index.getX()][index.getY()];
            }
        }
        System.out.println("The White player heuristic take the move at: "+ coordinateX[move.getY()]+(move.getX()+1));
        return move;
    }
// Helper Function Used for getting next move
    public static void placeNextMove(Board board) {
        Move nextMove = new Move(-1, -1);
        moveList = board.getValidateMoveList('W','B');

        int gameResult;
        int win = 5;

        for (Move move: moveList) {
            Board newBoard = new Board(board);
            newBoard.move(move,'W', 'B');
            for (int i = 0; i < 1000; i++) {
                Board newBoard2 = new Board(newBoard);
                while (true) {
                    HashSet<Move> validMovesBlack = newBoard2.getValidateMoveList('B', 'W');
                    HashSet<Move> validMovesWhite = newBoard2.getValidateMoveList('W', 'B');
                    gameResult = newBoard2.getResult();

                    if (gameResult != -1) {
                        if (gameResult == 0) {
                            win = 0;
                            break;
                        } else if (gameResult == 1) {
                            win = 1;
                            break;
                        } else if (gameResult == 2) {
                            win = 2;
                            break;
                        }
                        break;
                    }

                    if(!validMovesBlack.isEmpty()){
                        // https://stackoverflow.com/questions/124671/picking-a-random-element-from-a-set
                        int size = validMovesBlack.size();
                        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
                        int count = 0;
                        for(Move obj : validMovesBlack) {
                            if (count == item){
                                nextMove = obj;
                                break;
                            }
                            count++;
                        }
                        newBoard2.move(nextMove,'B','W');
                    }

                    validMovesWhite = newBoard2.getValidateMoveList('W', 'B');
                    validMovesBlack = newBoard2.getValidateMoveList('B', 'W');

                    gameResult = newBoard2.getResult();
                    if (gameResult != -1) {
                        if (gameResult == 0) {
                            win = 0;
                            break;
                        } else if (gameResult == 1) {
                            win = 1;
                            break;
                        } else if (gameResult == 2) {
                            win = 2;
                            break;
                        }
                        break;
                    }
                    if(!validMovesWhite.isEmpty()) {
                        int size = validMovesWhite.size();
                        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
                        int count = 0;
                        for(Move obj : validMovesWhite) {
                            if (count == item){
                                nextMove = obj;
                                break;
                            }
                            count++;
                        }
                        newBoard2.move(nextMove,'W','B');
                    }

                    gameResult = newBoard2.getResult();
                }

                // Break out of loop, already determine winner
                if (win == 0) {
                    move.draw++;
                } else if (win == 1) {
                    move.win++;
                } else {
                    move.lose++;
                }
            }
        }
        System.out.println("end simulate");
    }
}





