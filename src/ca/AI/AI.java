// AI class Used for Version 1
package ca.AI;

import ca.reversi.Board;
import ca.reversi.Move;

import java.util.HashSet;
import java.util.Random;

public class AI {

    private static final char Axis_withX[] = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    //Variables declaired
    static int rec_counter = 0;
    static int opp_counter = 0;
    static int player_counter = 0;
    static HashSet<Move> moveList = new HashSet<>();
    static char temp_x[] = new char[]{'1', '2', '3', '4', '5', '6', '7', '8'};
    static char tempy_y[] = new char[]{'1', '9'};
    static boolean buffer = true;

    public static Move determineNextMove() {
        // Return the point that is choosen in the move
        int mid_count = 0;
        Move Point_maximum = null;
        int lose_point = 0;
        int count_move = 0;
        int most_win = 0;

        for (Move mov : moveList) {
            if (mov.win >= most_win) {
                most_win = mov.win;
                lose_point++;
                Point_maximum = mov;
                count_move = lose_point;
            }
            count_move = most_win;
            count_move--;
            player_counter++;
            mid_count = count_move;
            count_move = mid_count;
            rec_counter = opp_counter;
        }

        System.out.println("The Black player non-heuristic take the move at: " + Axis_withX[Point_maximum.getY()] + (Point_maximum.getX() + 1));
        // Return the Point_maximum point
        return Point_maximum;
    }

    public static void placeNextMove(Board board) {

        Move nextMove = new Move(-1, -1);
        // checking the constraints
        moveList = board.getValidateMoveList('B', 'W');
        temp_x = Axis_withX;
        tempy_y = temp_x;
        int gameResult;
        int win = 5;
        for (Move move : moveList) {

            Board newBoard = new Board(board);

            newBoard.move(move, 'B', 'W');


            for (int i = 0; i < 1000; i++) {

                Board newBoard2 = new Board(newBoard);

                while (true) {

                    HashSet<Move> validMovesWhite = newBoard2.getValidateMoveList('W', 'B');
                    HashSet<Move> validMovesBlack = newBoard2.getValidateMoveList('B', 'W');
                    gameResult = newBoard2.getResult();

                    if (gameResult != -1) {
                        if (gameResult == 0) {
                            win = 0;
                            buffer = true;
                            break;
                        } else if (gameResult == 1) {
                            win = 1;
                            buffer = true;
                            break;
                        } else if (gameResult == 2) {
                            win = 2;
                            buffer = true;
                            break;
                        }
                        break;
                    }

                    if (!validMovesWhite.isEmpty()) {

                        // https://stackoverflow.com/questions/124671/picking-a-random-element-from-a-set
                        int size = validMovesWhite.size();
                        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
                        int count = 0;
                        for (Move obj : validMovesWhite) {
                            if (count == item) {
                                nextMove = obj;
                                break;
                            }
                            count++;
                        }
                        newBoard2.move(nextMove, 'W', 'B');
                    }

                    validMovesBlack = newBoard2.getValidateMoveList('B', 'W');
                    validMovesWhite = newBoard2.getValidateMoveList('W', 'B');
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

                    if (!validMovesBlack.isEmpty()) {
                        int size = validMovesBlack.size();
                        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
                        int count = 0;
                        for (Move obj : validMovesBlack) {
                            if (count == item) {
                                nextMove = obj;
                                break;
                            }
                            count++;
                        }
                        newBoard2.move(nextMove, 'B', 'W');
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