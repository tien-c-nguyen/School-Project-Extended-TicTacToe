package cpsc2150.extendedTicTacToe.models;

public abstract class AbsGameBoard implements IGameBoard{
    /**
     * creates and returns a string representation of the gameBoard
     * @return returns a string representation of the gameBoard
     *
     * @post
     * toString = [string representation of the game board] AND self = #self
     */
    @Override
    public String toString(){
        //miniTab properly formats the top header
        String miniTab = "   ";
        String indexNum;
        String finalStr = miniTab;
        for(int i = 0; i < getNumColumns(); i++){
            //left pads single digit numbers with a single space
            indexNum = String.format("%2d", i);
            finalStr += indexNum + "|";
        }
        finalStr += "\n";

        for(int r = 0; r < getNumRows(); r++){
            indexNum = String.format("%2d", r);
            finalStr += indexNum + "|";
            for(int c = 0; c < getNumColumns(); c++){
                BoardPosition bP = new BoardPosition(r, c);
                finalStr += whatsAtPos(bP) + " |";
            }
            finalStr += "\n";
        }
        return finalStr;
    }
}
