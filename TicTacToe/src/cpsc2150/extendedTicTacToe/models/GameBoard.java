/**
 * @author Tien Nguyen
 */
package cpsc2150.extendedTicTacToe.models;
/**
 * GameBoard class that creates the functionality for the board and 2D array to hold all the game markers
 *
 * @invariant
 * rows = #rows AND
 * columns = #columns AND
 * num_to_win = #num_to_win AND
 * [board dimensions remain unchanged]
 *
 * @correspondences
 * self = board[0...rows - 1][0... columns - 1]
 * num_of_rows = rows
 * num_of_columns = columns
 * num_to_win = num_to_win
 * game board = board
 *
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{
    private char[][] board;
    private int rows;
    private int columns;
    private int num_to_win;

    /**
     * the gameboard constructor that initializes a 2D array of blank space characters
     *
     * @param r is the number of rows passed into the constructor
     * @param c is the number of columns passed into the constructor
     * @param win is the number of tokens it takes to win passed into the constructor
     *
     * @pre
     * r > 0 AND c > 0 AND win > 0
     */
    public GameBoard(int r, int c, int win){
        rows = r;
        columns = c;
        num_to_win = win;
        board = new char[rows][columns];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                board[i][j] = ' ';
            }
        }
    }
    public int getNumRows(){
        return rows;
    }

    public int getNumColumns(){
        return columns;
    }

    public int getNumToWin(){
        return num_to_win;
    }


    public void placeMarker(BoardPosition marker, char player) {
        int r = marker.getRow(), c = marker.getColumn();
        board[r][c] = player;
    }



    public char whatsAtPos(BoardPosition pos){
        return board[pos.getRow()][pos.getColumn()];
    }
}
