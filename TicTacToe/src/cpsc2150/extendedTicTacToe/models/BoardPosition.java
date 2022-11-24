package cpsc2150.extendedTicTacToe.models;


/**
 * The BoardPosition class contains the row index and column index of a marker
 *
 * @invariant row = #row AND column = #column
 */
public class BoardPosition {
    private int row;
    private int column;
    /**
     * BoardPosition constructor that instantiates the BoardPosition object and initializes its attributes
     *
     * @param r is the row index passed in from the GameScreen class, r should be checked for validity before being passed in
     * @param col is the column index passed in from the GameScreen class, col is also checked for validity before being passed in
     *
     * @pre
     * [r and c are integers]
     *
     * @post
     * Row = r AND Column = col
     */
    public BoardPosition(int r, int col){
        row = r;
        column = col;
    }


    /**
     * Returns the row number of the BoardPosition object
     *
     * @return returns Row
     *
     * @post
     * getRow() = #Row
     */
    public int getRow(){
        //returns the row
        return row;
    }

    /**
     * Returns the column of the BoardPosition instance
     *
     * @return returns Column
     *
     * @post
     * getColumn() = #Column AND
     */
    public int getColumn(){
        //returns the column
        return column;
    }

    /**
     * Method compares the Row and Column of the current object and the obj param
     *
     * @param obj is the BoardPosition object being passed in
     *
     * @return returns true is the object passed in is an instance of BoardPosition and if its attributes match those of 'this'
     *
     * @post
     * (equals() = true iff ((obj instanceOf BoardPosition) AND [the attributes of obj equal the attributes of this]))
     */
    @Override
    public boolean equals (Object obj){
        if(!(obj instanceof BoardPosition))
        {
            return false;
        }
        BoardPosition bp = (BoardPosition) obj;
        if(this.row == bp.getRow() && this.column == bp.getColumn())
            return true;
        return false;
    }

    /**
     * Method returns a string representation of BoardPosition
     *
     * @return returns a string in the format of "<Row>, <Column>"
     *
     * @post
     * [a string containing "<row>,<column>." is returned]
     */
    @Override
    public String toString (){
        String str = row + "," + column;
        return str;
    }
}
