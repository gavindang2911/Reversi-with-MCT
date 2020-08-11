package ca.reversi;

import java.util.HashSet;

public class Board {
    private final int BOARD_SIZE = 8;
    private final char BLACK = 'B';
    private final char WHITE = 'W';
    private final char EMPTY = '_';

    public int getWhiteTotal() {
        return whiteTotal;
    }

    public int getBlackTotal() {
        return blackTotal;
    }

    public int getRest() {
        return rest;
    }

    private int whiteTotal, blackTotal, rest;

    private char[][] board;

    public Board() {
        board = new char[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        board[3][3] = WHITE;
        board[4][4] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
    }

    /**
     * Creates a new board with the same state as {@code toCopy}
     * @param copy the board state to clone
     */
    public Board(Board copy) {
        char[][] copyBoard = copy.board;
        this.board = new char[copyBoard.length][];
        for (int i = 0; i < copyBoard.length; i++) {
            this.board[i] = copyBoard[i].clone();
        }
    }

    public void displayBoard(Board board){              //Print the whole current board
        System.out.print("\n  ");
        System.out.print("A B C D E F G H");
        System.out.println();
        for(int row = 0; row < BOARD_SIZE; row++){
            System.out.print((row+1) + " ");
            for(int column = 0; column < BOARD_SIZE; column++) {
                System.out.print(board.board[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public HashSet<Move> getValidateMoveList(char currentPlayer, char opponentPlayer) {
        HashSet<Move> validMoveList = new HashSet<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0 ; column < BOARD_SIZE; column++) {
                if (this.board[row][column] == opponentPlayer) {
                    // UP RIGHT TO LEFT - -
                    int currentRow = row;
                    int currentColumn = column;
                    if (currentRow - 1 >= 0 && currentColumn - 1 >= 0 && board[currentRow-1][currentColumn-1] == EMPTY) {
                        currentRow++;
                        currentColumn++;
                        while (currentRow < BOARD_SIZE -1 && currentColumn < BOARD_SIZE - 1 && board[currentRow][currentColumn] == opponentPlayer) {
                            currentRow++;
                            currentColumn++;
                        }
                        if (currentRow <= BOARD_SIZE -1 && currentColumn <= BOARD_SIZE -1 && board[currentRow][currentColumn] == currentPlayer) {
                            validMoveList.add(new Move(row - 1, column - 1));
                        }
                    }

                    // UP LEFT TO RIGHT - +
                    currentRow = row;
                    currentColumn = column;
                    if (currentRow - 1 >= 0 && currentColumn + 1 <= BOARD_SIZE - 1 && board[currentRow][currentColumn] == EMPTY) {
                        currentRow++;
                        currentColumn--;
                        while (currentRow < BOARD_SIZE - 1 && currentColumn > 0 && board[currentRow][currentColumn] == opponentPlayer) {
                            currentRow++;
                            currentColumn--;
                        }
                        if (currentRow <= BOARD_SIZE - 1 && currentColumn >= 0 && board[currentRow][currentColumn] == currentPlayer) {
                            validMoveList.add(new Move(row -1, column + 1));
                        }
                    }

                    // DOWN LEFT TO RIGHT + +
                    currentRow = row;
                    currentColumn = column;
                    if(currentRow + 1 <= BOARD_SIZE - 1 && currentColumn + 1 <= BOARD_SIZE - 1 && board[currentRow+1][currentColumn+1] == EMPTY){
                        currentRow--;
                        currentColumn--;
                        while(currentRow > 0 && currentColumn > 0 && board[currentRow][currentColumn] == opponentPlayer) {
                            currentRow--;
                            currentColumn--;
                        }
                        if(currentRow >= 0 && currentColumn >= 0 && board[currentRow][currentColumn] == currentPlayer) {
                            validMoveList.add(new Move(row + 1, column + 1));
                        }
                    }

                    // DOWN RIGHT TO LEFT + -
                    currentRow = row;
                    currentColumn = column;
                    if(currentRow + 1 <= BOARD_SIZE - 1 && currentColumn - 1 >=0 && board[currentRow+1][currentColumn-1] == EMPTY){
                        currentRow--;
                        currentColumn++;
                        while(currentRow > 0 && currentColumn < BOARD_SIZE - 1 && board[currentRow][currentColumn] == opponentPlayer){
                            currentRow--;
                            currentColumn++;
                        }
                        if (currentRow >= 0 && currentColumn <= BOARD_SIZE - 1 && board[currentRow][currentColumn] == currentPlayer) {
                            validMoveList.add(new Move(row + 1, column - 1));
                        }
                    }

                    // UP -
                    currentRow = row;
                    currentColumn = column;
                    if(currentRow - 1 >= 0 && board[currentRow-1][column] == EMPTY){
                        currentRow++;
                        while(currentRow < BOARD_SIZE - 1 && board[currentRow][column] == opponentPlayer) {
                            currentRow++;
                        }
                        if(currentRow <= BOARD_SIZE - 1 && board[currentRow][column] == currentPlayer) {
                            validMoveList.add(new Move(row - 1, column));
                        }
                    }

                    // DOWN +
                    currentRow = row;
                    currentColumn = column;
                    if(currentRow + 1 <= BOARD_SIZE - 1 && board[currentRow+1][column] == EMPTY){
                        currentRow--;
                        while(currentRow > 0 && board[currentRow][column] == opponentPlayer) {
                            currentRow--;
                        }
                        if(currentRow >= 0 && board[currentRow][column] == currentPlayer) {
                            validMoveList.add(new Move(row + 1, column));
                        }
                    }

                    // RIGHT  ,+
                    currentRow = row;
                    currentColumn = column;
                    if(currentColumn + 1 <= BOARD_SIZE - 1 && board[row][currentColumn + 1] == EMPTY){
                        currentColumn--;
                        while(currentColumn > 0 && board[row][currentColumn] == opponentPlayer) {
                            currentColumn--;
                        }
                        if(currentColumn >= 0 && board[row][currentColumn] == currentPlayer) {
                            validMoveList.add(new Move(row, column + 1));
                        }
                    }

                    // LEFT ,-
                    currentRow = row;
                    currentColumn = column;
                    if(currentColumn - 1 >= 0 && board[row][currentColumn-1] == EMPTY){
                        currentColumn++;
                        while(currentColumn < BOARD_SIZE - 1 && board[row][currentColumn] == opponentPlayer) {
                            currentColumn++;
                        }
                        if(currentColumn <= BOARD_SIZE - 1 && board[row][currentColumn] == currentPlayer) {
                            validMoveList.add(new Move(row, column - 1));
                        }
                    }

                }
            }
        }
        return validMoveList;
    }

    public void displayNextValidMoves(HashSet<Move> playerList){
        for(Move p : playerList)
            board[p.getX()][p.getY()] = '*';
        displayBoard(this);
        for(Move p : playerList)
            board[p.getX()][p.getY()]='_';
    }

    public void move(Move move, char currentPlayer, char opponentPlayer) {
        int row = move.getX();
        int column = move.getY();
        int currentRow = row;
        int currentColumn = column;
        this.board[row][column] = currentPlayer;

        // UP RIGHT TO LEFT - -
        if(currentRow + 1 <= BOARD_SIZE - 1 && currentColumn + 1 <= BOARD_SIZE - 1 && board[currentRow+1][currentColumn+1] == opponentPlayer) {
            currentRow++;
            currentColumn++;
            while (currentRow < BOARD_SIZE - 1 && currentColumn < BOARD_SIZE - 1 && board[currentRow][currentColumn] == opponentPlayer) {
                currentRow++;
                currentColumn++;
            }
            if (currentRow <= BOARD_SIZE - 1 && currentColumn <= BOARD_SIZE - 1 && board[currentRow][currentColumn] == currentPlayer) {
                while (currentRow != row + 1 && currentColumn != column + 1) {
                    board[--currentRow][--currentColumn] = currentPlayer;
                }
            }
        }

        // UP LEFT TO RIGHT - +
        currentRow = move.getX();
        currentColumn = move.getY();
        if (currentRow + 1 <= BOARD_SIZE - 1 && currentColumn - 1 >=0 && board[currentRow+1][currentColumn-1] == opponentPlayer){

            currentRow++;
            currentColumn--;
            while (currentRow < BOARD_SIZE - 1 && currentColumn > 0 && board[currentRow][currentColumn] == opponentPlayer) {
                currentRow++;
                currentColumn--;
            }
            if (currentRow<=BOARD_SIZE - 1 && currentColumn>=0 && board[currentRow][currentColumn] == currentPlayer) {
                while(currentRow != row+1 && currentColumn!=column-1){
                    board[--currentRow][++currentColumn] = currentPlayer;
                }}
        }

        // DOWN RIGHT TO LEFT + -
        currentRow = move.getX();
        currentColumn = move.getY();
        if (currentRow - 1 >= 0 && currentColumn + 1 <= BOARD_SIZE - 1 && board[currentRow-1][currentColumn+1] == opponentPlayer){

            currentRow--;
            currentColumn++;
            while (currentRow > 0 && currentColumn < BOARD_SIZE - 1 && board[currentRow][currentColumn] == opponentPlayer) {
                currentRow--;
                currentColumn++;
            }
            if (currentRow >= 0 && currentColumn <= BOARD_SIZE - 1 && board[currentRow][currentColumn] == currentPlayer) {
                while (currentRow != row - 1 && currentColumn != column + 1) {
                    board[++currentRow][--currentColumn-1] = currentPlayer;
                }
            }
        }

        // DOWN LEFT TO RIGHT + +
        currentRow = move.getX();
        currentColumn = move.getY();
        if (currentRow - 1 >= 0 && currentColumn - 1 >= 0 && board[currentRow-1][currentColumn-1] == opponentPlayer) {

            currentRow--;
            currentColumn--;
            while (currentRow > 0 && currentColumn > 0 && board[currentRow][currentColumn] == opponentPlayer) {
                currentRow--;
                currentColumn--;
            }
            if (currentRow >= 0 && currentColumn >= 0 && board[currentRow][currentColumn] == currentPlayer) {
                while (currentRow != row - 1 && currentColumn != column - 1) {
                    board[++currentRow][++currentColumn] = currentPlayer;
                }
            }
        }

        // UP
        currentRow = move.getX();
        currentColumn = move.getY();
        if(currentRow + 1 <= BOARD_SIZE - 1 && board[currentRow+1][column] == opponentPlayer){

            currentRow++;
            while(currentRow < BOARD_SIZE - 1 && board[currentRow][column] == opponentPlayer) {
                currentRow++;
            }
            if(currentRow <= BOARD_SIZE - 1 && board[currentRow][column] == currentPlayer) {
                while(currentRow != row + 1) {
                    board[--currentRow][column] = currentPlayer;
                }
            }
        }

        // DOWN +,
        currentRow = move.getX();
        currentColumn = move.getY();
        if(currentRow - 1 >=0 && board[currentRow-1][column] == opponentPlayer){

            currentRow--;
            while(currentRow > 0 && board[currentRow][column] == opponentPlayer) {
                currentRow--;
            }
            if(currentRow >= 0 && board[currentRow][currentColumn] == currentPlayer) {
                while(currentRow != row - 1) {
                    board[++currentRow][column] = currentPlayer;
                }
            }
        }

        
        // Right ,+
        currentRow = move.getX();
        currentColumn = move.getY();
        if(currentColumn -1 >= 0 && board[row][currentColumn-1] == opponentPlayer){

            currentColumn--;
            while(currentColumn > 0 && board[row][currentColumn] == opponentPlayer) {
                currentColumn--;
            }
            if(currentColumn >= 0 && board[row][currentColumn] == currentPlayer) {
                while(currentColumn != column - 1) {
                    board[row][++currentColumn] = currentPlayer;
                }
            }
        }
        
        // LEFT ,-
        currentRow = move.getX();
        currentColumn = move.getY();
        if(currentColumn + 1 <= BOARD_SIZE - 1 && board[row][currentColumn+1] == opponentPlayer){

            currentColumn++;
            while(currentColumn < BOARD_SIZE - 1 && board[row][currentColumn] == opponentPlayer) {
                currentColumn++;
            }
            if(currentColumn <= BOARD_SIZE - 1 && board[currentRow][currentColumn] == currentPlayer) {
                while(currentColumn != column + 1) {
                    board[currentRow][--currentColumn] = currentPlayer;
                }
            }
        }

    }

    /**
     * Counts the number of black and white pieces and determines a winner. Doesn't check if the game has actually ended.
     * @return array [nbrBlack, nbrWhite, winner]
     * 1 white, 2 black, 0 draw
     */
    public int getResult() {
        whiteTotal = 0;
        blackTotal = 0;
        rest = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (board[row][column] == BLACK) {
                    blackTotal++;
                } else if (board[row][column] == WHITE) {
                    whiteTotal++;
                } else {
                    rest++;
                }
            }
        }
        int winner = -1;
        if (rest == 0) {
            if (blackTotal < whiteTotal) {
                winner = 1;
            } else if (blackTotal > whiteTotal) {
                winner = 2;
            }
            else {
                winner = 0;
            }
        }

        if (blackTotal == 0 || whiteTotal == 0) {

            if (blackTotal != 0) {
                winner = 2;
            } else if (whiteTotal != 0) {
                winner = 1;
            }
        }

        if(getValidateMoveList('W','B').isEmpty() && getValidateMoveList('B', 'W').isEmpty()){

            if (blackTotal < whiteTotal) {
                winner = 1;
            } else if (blackTotal > whiteTotal) {
                winner = 2;
            }
            else {
                winner = 0;
            }
        }
        return winner;
    }

    public int checkTheXCoordinate(char x){             //check the legality of the input
        if ((Character.toLowerCase(x) == 'A') || (Character.toUpperCase(x) == 'A'))
            return 0;
        else if ((Character.toLowerCase(x) == 'B') || (Character.toUpperCase(x) == 'B'))
            return 1;
        else if ((Character.toLowerCase(x) == 'C') || (Character.toUpperCase(x) == 'C'))
            return 2;
        else if ((Character.toLowerCase(x) == 'D') || (Character.toUpperCase(x) == 'D'))
            return 3;
        else if ((Character.toLowerCase(x) == 'E') || (Character.toUpperCase(x) == 'E'))
            return 4;
        else if ((Character.toLowerCase(x) == 'F') || (Character.toUpperCase(x) == 'F'))
            return 5;
        else if ((Character.toLowerCase(x) == 'G') || (Character.toUpperCase(x) == 'G'))
            return 6;
        else if ((Character.toLowerCase(x) == 'H') || (Character.toUpperCase(x) == 'H'))
            return 7;
        return -1; // Illegal move received
    }

}
