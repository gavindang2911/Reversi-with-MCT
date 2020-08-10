package ca.reversi;

import java.util.HashSet;

public class Board {
    private final int BOARD_SIZE = 8;
    private final char BLACK = 'B';
    private final char WHITE = 'W';
    private final char EMPTY = '_';
    public int whiteTotal, blackTotal, rest;

    private char[][] board;

    public Board() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        board[3][3] = WHITE;
        board[4][4] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
                    board[i][j] = WHITE;
                }
                if (i == 3 && j == 4) {
                    board[i][j] = BLACK;
                }
                if (i == 4 && j == 3) {
                    board[i][j] = BLACK;
                }
                board[i][j] = EMPTY;
            }
        }
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
        System.out.print("\n\n");
        System.out.print("A B C D E G G H");
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

    private HashSet<Move> getValidateMoveList(char currentPlayer, char opponentPlayer) {
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

    public void displayLocation(HashSet<Move> playerList, char currentPlayer, char opponentPlayer){
        for(Move p : playerList)
            board[p.getX()][p.getY()] = '*';
        displayBoard(this);
        for(Move p : playerList)
            board[p.getX()][p.getY()]='_';
    }

    public void move(Move move, char currentPlayer, char opponentPlayer) {
        int row = move.getX();
        int column = move.getY();
        int currentRow = move.getX();
        int currentColumn = move.getY();
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
                    board[currentRow-1][currentColumn-1] = currentPlayer;
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
                    board[currentRow-1][currentColumn+1] = currentPlayer;
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
                    board[currentRow+1][currentColumn-1] = currentPlayer;
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
                    board[currentRow+1][currentColumn+1] = currentPlayer;
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
                    board[currentRow-1][column] = currentPlayer;
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
                    board[currentRow+1][column] = currentPlayer;
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
                    board[row][currentColumn+1] = currentPlayer;
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
            if(currentColumn <= BOARD_SIZE - 1 && board[row][currentColumn] == currentPlayer) {
                while(currentColumn != column + 1) {
                    board[row][currentColumn-1] = currentPlayer;
                }
            }
        }

    }

    /**
     * Counts the number of black and white pieces and determines a winner. Doesn't check if the game has actually ended.
     * @return array [nbrBlack, nbrWhite, winner]
     */
    public int[] getResult() {
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
        return new int[] {whiteTotal,blackTotal};
    }

}
