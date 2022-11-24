package cpsc2150.extendedTicTacToe.models;
import java.util.*;

/**
 * @invariant
 * rows = #rows AND
 * columns = #columns AND
 * num_to_win = #num_to_win
 * @correspondences
 *  self = [map with the players as keys and the value as a list of BoardPositions of that player]
 */

public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    private Map<Character, List<BoardPosition>> board;
    private int rows;
    private int columns;
    private int num_to_win;

    public GameBoardMem(int r, int c, int win){
        rows = r;
        columns = c;
        num_to_win = win;
        board = new HashMap<>();
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
    public void placeMarker(BoardPosition marker, char player){
        if(!board.containsKey(player)){
            List<BoardPosition> newPlayer = new ArrayList<>();
            board.put(player, newPlayer);
        }
        board.get(player).add(marker);

    }

    public char whatsAtPos(BoardPosition pos){
        for (Map.Entry<Character, List<BoardPosition>> m: board.entrySet()){
            if(m.getValue().contains(pos))
                return m.getKey();
        }
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if(board.get(player).contains(pos))
            return true;
        return false;
    }
}
