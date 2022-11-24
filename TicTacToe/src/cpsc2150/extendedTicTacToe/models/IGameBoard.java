package cpsc2150.extendedTicTacToe.models;

/**
 * IGameBoard is an abstract representation of a game board
 *
 * Initialization ensures:
 *  a board the size of num_of_rows by the num_of_columns
 *  filled with a blank space character
 * Defines:
 *  num_of_rows = Z
 *  num_of_columns = Z
 *  num_to_win = Z
 * Constraints:
 *  num_of_rows > 0 AND [the number does not change]
 *  num_of_columns > 0 AND [the number does not change]
 */
public interface IGameBoard {
    /**
     * returns the number of rows for the board
     * @return returns the number of rows
     *
     * @post
     * getNumRows() = num_of_rows AND
     * self = #self
     */
     int getNumRows();
    /**
     * returns the number of columns for the board
     * @return returns the number of columns
     *
     * @post
     * getNumColumns() = num_of_cols AND
     * self = #self
     */
    int getNumColumns();
    /**
     * returns the number of consecutive markers need to win
     * @return returns the number to win
     *
     * @post
     * getNumToWin() = num_to_win AND
     * self = #self
     */
    int getNumToWin();
    /**
     * method determines if the position passed in by the user is available
     *
     * @param pos is the position passed in to be checked for validity
     *
     * @return true is position specified by pos is available and false otherwise
     *
     * @pre
     * pos != null
     * @post
     * true iff ((pos.Row < num_of_rows AND pos.Row >= 0) AND (pos.Column < num_of_columns AND pos.Column >= 0) AND
     * (self[pos.Row][pos.Column] = ' ')
     *
     */
     default boolean checkSpace(BoardPosition pos){
         int r = pos.getRow();
         int c = pos.getColumn();

         if(((r < getNumRows() && r >= 0) && (c < getNumColumns() && c >= 0))){
             if(whatsAtPos(pos) == ' ')
                 return true;
             return false;
         }
         return false;
     }
    /**
     * places the character in marker on the position specified by
     * marker and should not be called if the space is unavailable.
     *
     * @param marker is the position of the marker that will be placed
     * @param player is the marker that indicates which player is which
     *
     * @return none
     *
     * @pre
     * marker != null AND player = [valid game marker]
     * @post
     * self[marker.Row][marker.Column] = [valid game marker]
     */
    void placeMarker(BoardPosition marker, char player);
    /**
     * this function will check to see if the lastPos placed resulted
     * in a winner. If so it will return true, otherwise false.
     *
     * @param lastPos is the last position that the user placed their marker
     *
     * @return will return true if num_to_win markers are found to placed num_to_win times consecutively
     *
     * @pre
     * lastPos != null AND [lastPos is the most recent game board position]
     * @post
     * true iff (checkVerticalWin() || checkHorizontalWin() || checkDiagonalWin())
     */
    default boolean checkForWinner(BoardPosition lastPos) {
        char marker = whatsAtPos(lastPos);
        if(checkHorizontalWin(lastPos, marker)){
            return true;
        }else if(checkVerticalWin(lastPos, marker)){ 
            return true;
        } else if (checkDiagonalWin(lastPos, marker)) {
            return true;
        }
        return false;
    }
    /**
     * this function will check to see if the game has resulted in a
     * tie. A game is tied if there are no free board positions remaining.
     *
     * @return will return true if game board is completely full
     *
     * @pre
     * [a winner has not been declared yet]
     * @post
     * [every space in self is a blank character] AND
     * self = #self
     */
    default boolean checkForDraw(){
        int r, c;
        for(r = 0; r < getNumRows(); r++){
            for(c = 0; c < getNumColumns();c++){
                BoardPosition b = new BoardPosition(r, c);
                if(whatsAtPos(b) == ' ')
                    return false;
            }
        }
        return true;
    }

    /**
     * checks to see if the last marker placed resulted in num_to_win in a row
     * diagonally
     *
     * @param lastPos represents the last BoardPosition object placed on the game board
     * @param player is the marker that indicates which player is placing the marker
     *
     * @return returns true if there is 5 in a row diagonally
     *
     * @pre
     * lastPos != null AND
     * player = [valid game marker] AND
     * [a winner has not been declared yet]
     * @post
     * true iff ([there is a num_to_win in a row diagonally] AND self = #self)
     */
     default boolean checkDiagonalWin(BoardPosition lastPos, char player){
         int r = lastPos.getRow();
         int c = lastPos.getColumn();
         int markerCounter = 0;
         int diagonalLength = r + getNumToWin();
         c -= (getNumToWin() - 1);
         //scans the diagonal from the left
         for(r = r - (getNumToWin() - 1); r < diagonalLength && r < getNumRows(); r++){
             if(r >= 0 && c >= 0 && c < getNumColumns()) {
                 BoardPosition b = new BoardPosition(r, c);
                 if(isPlayerAtPos(b, player)){
                     markerCounter++;
                     if(markerCounter >= getNumToWin())
                         return true;
                 }else{
                     markerCounter = 0;
                 }
             }
             else
                markerCounter = 0;
             c++;
         }

         markerCounter = 0;
         r = lastPos.getRow();
         c = lastPos.getColumn();
         c += (getNumToWin() - 1);
         //scans through the diagonal from the right
         for(r = r - (getNumToWin() - 1); r < getNumRows() && r < diagonalLength; r++) {
             if (c < getNumColumns() && r >= 0 && c >= 0) {
                 BoardPosition b = new BoardPosition(r, c);
                 if (isPlayerAtPos(b, player)) {
                     markerCounter++;
                     if (markerCounter >= getNumToWin())
                         return true;
                 } else {
                     markerCounter = 0;
                 }
             }
             else
                markerCounter = 0;
             c--;
         }
         return false;
     }
    /**
     * checks to see if the last marker placed resulted in num_to_win in a row
     * horizontally
     *
     * @param lastPos is the last position the user placed their marker
     * @param player is the marker that differentiates each player
     *
     * @return returns true if there is a num_to_win in a row horizontally
     *
     * @pre
     * lastPos != null AND
     * player = [valid game marker] AND
     * [a winner has not been declared yet]
     * @post
     * true iff ([lastPos.Row contains num_to_win in a row] AND self = #self)
     */
     default boolean checkHorizontalWin(BoardPosition lastPos, char player){
         int r = lastPos.getRow(), c = lastPos.getColumn() - getNumToWin();
         int markerCounter = 0;
         if(c < 0)
             c = 0;
         for(int itr = c; itr < lastPos.getColumn() + getNumToWin() && itr < getNumColumns(); itr++){
             BoardPosition b = new BoardPosition(r, itr);
             if(isPlayerAtPos(b, player)){
                 markerCounter++;
                 if(markerCounter >= getNumToWin())
                     return true;
             }else{
                 markerCounter = 0;
             }
         }
         return false;
     }
    /**
     * checks to see if the last marker placed resulted in num_to_win in a row
     * vertically
     *
     * @param lastPos represents the last BoardPosition object placed on the game board
     * @param player is the marker that indicates which player is placing the marker
     *
     * @return returns true if the last marker place resulted in a num_to_win in a row vertically
     *
     * @pre
     * lastPos != null AND
     * player = [valid game marker] AND
     * [a winner has not been declared yet]
     * @post
     * true iff ([lastPos.Column contains num_to_win in a row] AND self = #self)
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player){
        int r = lastPos.getRow() - getNumToWin(), c = lastPos.getColumn();
        int markerCounter = 0;
        if(r < 0)
            r = 0;
        for(int itr = r; itr < lastPos.getRow() + getNumToWin() && itr < getNumRows(); itr++){
            BoardPosition b = new BoardPosition(itr, c);
            if(isPlayerAtPos(b, player)){
                markerCounter++;
                if(markerCounter >= getNumToWin())
                    return true;
            }else{
                markerCounter = 0;
            }
        }
        return false;
    }

    /**
     * returns what is in the GameBoard at position pos
     * If no marker is there, it returns a blank space char.
     *
     * @param pos is the BoardPosition object passed in
     *
     * @return returns the character on the board, returns a blank space char if there is no marker at the position
     *
     * @pre
     * pos != null
     *
     * @post
     * self = #self AND whatsAtPos = [character of the game board or a blank space char]
     */
     char whatsAtPos(BoardPosition pos);

    /**
     * indicates which player is at a certain position on a board
     *
     * @param pos is the BoardPosition object that indicates the player's
     * @param player indicates whose marker it is
     *
     * @return returns true if the marker at the position matches player
     *
     * @pre
     * pos != null AND player = [valid game marker]
     *
     * @post
     * self = #self AND
     * true iff [space at pos.Row and pos.Column] = player
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player){
        if(player == whatsAtPos(pos))
            return true;
        return false;
    }
}
