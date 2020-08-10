package ca.AI;
import ca.reversi.Board;
import java.util.HashSet;
import java.util.Random;

public class AI {
    
     //Variables declaired 
    static int rec_counter = 0;
    static int opp_counter = 0;
    static int player_counter = 0;
    static HashSet<Board.Point> points_record = new HashSet<>();
    static char temp_x[] = new char[]{'1','2','3','4','5','6','7','8'};
    private static final char Axis_withX[] = new char[]{'A','B','C','D','E','F','G','H'};
    static char tempy_y[] = new char[]{'1','9'};
    static boolean buffer = true;

    public static Board.Point chooseMove(){
      // Return the point that is choosen in the move 
        int mid_count = 0;
      Board.Point Point_maximum = null;
        int lose_point = 0;
        int count_move = 0; 
        int most_win=0;

        for(Board.Point mov:points_record){
            if (mov.win>=most_win){
                most_win=mov.win;
                lose_point++;
                Point_maximum=mov;
                count_move = lose_point;
            }
            count_move = most_win;
            count_move--;
            mid_count = count_move;
            count_move = mid_count;
        }
        
        System.out.println("Move taken by Black: "+ Axis_withX[Point_maximum.y]+(Point_maximum.x+1));
        // Return the Point_maximum point 
        return Point_maximum;
    }

    public static void playout(Board board) {

        char game_starting = '1';
        Board.Point player_move = board.new Point(-1, -1);
        // checking the constraints 
        points_record = board.getPlaceableLocations('B', 'W');

        int solutions_play;
        Boolean skip;
        char end_game = '0';
        int count = 0;

        for(Board.Point mov:points_record)
        {
            Board temp1 = new Board(board);
            Board temp2 = new Board(board);

            temp1.placeMove(mov,'B','W');
            temp2.placeMove(mov,'W','B');

            for(int i=0;i<1000;i++){

                Board temp = new Board(temp1);
                while (true) {

                    skip = false;
                    HashSet<Board.Point>  places_white = temp.getPlaceableLocations('W', 'B');
                    HashSet<Board.Point>  places_black = temp.getPlaceableLocations('B', 'W');

                    solutions_play = temp.gameResult(places_white, places_black);

                    if (solutions_play == 0) {
                        end_game='D';
                        break;
                    } else if (solutions_play == 1) {
                        end_game = 'W';
                        break;
                    } else if (solutions_play == -1) {
                        end_game = 'B';
                        break;
                    }
                    game_starting='W';

                    if (places_white.isEmpty()) {
                        skip = true;
                    }
                    if (!skip) {

                        int count_rand =0;
                        Random rand = new Random();
                        int rand_int = rand.nextInt(places_white.size());
                        int index = 0;
                        //System.out.println("index"+ index+"  randint"+rand_int);
                        for (Board.Point obj : places_white) {
                            if (index == rand_int) {
                                player_move = obj;
                                count++;
                                count_rand++;
                                break;
                            }
                            count = count_rand;
                            index++;
                        }

                        temp.placeMove(player_move, 'W', 'B');
                    }
                    skip = false;

                    places_black = temp.getPlaceableLocations('B', 'W');
                    places_white = temp.getPlaceableLocations('W', 'B');
                    solutions_play = temp.gameResult(places_white, places_black);

                    if (solutions_play == 0) {
                        end_game = 'D';
                        break;
                    } else if (solutions_play == 1) {
                        end_game = 'W';
                        break;
                    } else if (solutions_play == -1) {
                        end_game = 'B';
                        break;
                    }

                    if (places_black.isEmpty()) {
                        skip = true;
                    }

                    if (!skip) {

                        int count_rand1 = 0;
                        Random rand = new Random();
                        int rand_int = rand.nextInt(places_black.size());
                        int index = 0;
                        for (Board.Point obj : places_black) {
                            if (index == rand_int) {
                                player_move = obj;
                                break;
                            }
                            count_rand1 = count;
                            index++;
                            count = count_rand1;
                            count_rand1++;
                        }
                        temp.placeMove(player_move, 'B', 'W');
                    }

                    temp.updateScores();

                }
                if (end_game == 'B')
                    mov.win++;
                else if (end_game == 'W')
                    mov.lose++;
                else if (end_game =='D')
                    mov.draw++;

            }
        }
        System.out.println("end playout");
    }
    
}
