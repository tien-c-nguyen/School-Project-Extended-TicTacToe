package cpsc2150.extendedTicTacToe.models;

import cpsc2150.extendedTicTacToe.models.BoardPosition;
import cpsc2150.extendedTicTacToe.models.GameBoard;
import cpsc2150.extendedTicTacToe.models.IGameBoard;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard {
    private char [][] arr;
    private IGameBoard makeAGB(int r, int c, int numToWin){
        IGameBoard gB = new GameBoard(r , c, numToWin);
        arr = new char[r][c];

        for(int rows = 0; rows < arr.length; rows++){
            for(int cols = 0; cols < arr[0].length; cols++){
                arr[rows][cols] = ' ';
            }
        }

        return gB;
    }

    private String gBString (char [][] arr){
        //miniTab properly formats the top header
        String miniTab = "   ";
        String indexNum;
        String finalStr = miniTab;
        for(int i = 0; i < arr[0].length; i++){
            //left pads single digit numbers with a single space
            indexNum = String.format("%2d", i);
            finalStr += indexNum + "|";
        }
        finalStr += "\n";

        for(int r = 0; r < arr.length; r++){
            indexNum = String.format("%2d", r);
            finalStr += indexNum + "|";
            for(int c = 0; c < arr[0].length; c++){
                finalStr += arr[r][c] + " |";
            }
            finalStr += "\n";
        }
        return finalStr;

    }

    private static double EPSILON = 0.0001;

    //testing constructor
    @Test
    public void testConstructor_rows(){
        IGameBoard gB = makeAGB(5, 4, 3);
        assertEquals(5, gB.getNumRows(), EPSILON);
    }

    @Test
    public void testConstructor_columns(){
        IGameBoard gB = makeAGB(5, 4, 3);
        assertEquals(4, gB.getNumColumns(), EPSILON);
    }

    @Test
    public void testNumToWin_columns(){
        IGameBoard gB = makeAGB(5, 4, 3);
        assertEquals(3, gB.getNumToWin(), EPSILON);
    }

    //testing checkSpace
    @Test
    public void testCheckSpace_filled_spot(){
        IGameBoard gB = makeAGB(4,4,3);
        BoardPosition pos = new BoardPosition(0, 0);

        arr[0][0] = 'X';
        gB.placeMarker(pos, 'X');

        assertFalse(gB.checkSpace(pos));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testCheckSpace_available_spot(){
        IGameBoard gB = makeAGB(4,4,3);
        BoardPosition pos = new BoardPosition(0, 0);

        assertTrue(gB.checkSpace(pos));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testCheckSpace_out_of_bounds(){
        IGameBoard gB = makeAGB(4,4,3);
        BoardPosition pos = new BoardPosition(45, -2);

        assertFalse(gB.checkSpace(pos));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testCheckHorizontalWin_win_more_tokens_to_win(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(2, 0);
        gB.placeMarker(pos, 'x');
        arr[2][0] = 'x';
        pos = new BoardPosition(2, 1);
        gB.placeMarker(pos, 'x');
        arr[2][1] = 'x';
        pos = new BoardPosition(2, 3);
        gB.placeMarker(pos, 'x');
        arr[2][3] = 'x';
        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertTrue(gB.checkHorizontalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testCheckHorizontalWin_win_token_at_the_end(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(2, 0);
        gB.placeMarker(pos, 'x');
        arr[2][0] = 'x';

        pos = new BoardPosition(2, 1);
        gB.placeMarker(pos, 'x');
        arr[2][1] = 'x';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertTrue(gB.checkHorizontalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testCheckHorizontalWin_win_token_in_middle(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(2, 1);
        gB.placeMarker(pos, 'x');
        arr[2][1] = 'x';

        pos = new BoardPosition(2, 3);
        gB.placeMarker(pos, 'x');
        arr[2][3] = 'x';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertTrue(gB.checkHorizontalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testCheckHorizontalWin_wrong_token(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(2, 1);
        gB.placeMarker(pos, 'x');
        arr[2][1] = 'x';

        pos = new BoardPosition(2, 3);
        gB.placeMarker(pos, 'x');
        arr[2][3] = 'x';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'o');
        arr[2][2] = 'o';

        assertFalse(gB.checkHorizontalWin(pos, 'o'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testCheckVerticalWin_win_more_tokens_to_win(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(0, 2);
        gB.placeMarker(pos, 'x');
        arr[0][2] = 'x';
        pos = new BoardPosition(1, 2);
        gB.placeMarker(pos, 'x');
        arr[1][2] = 'x';
        pos = new BoardPosition(3, 2);
        gB.placeMarker(pos, 'x');
        arr[3][2] = 'x';
        pos = new BoardPosition(4, 2);
        gB.placeMarker(pos, 'x');
        arr[4][2] = 'x';
        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertTrue(gB.checkVerticalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testWhatsAtPos_first_spot(){
        IGameBoard gB = makeAGB(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gB.placeMarker(pos, 'x');
        arr[0][0] = 'x';

        assertEquals('x', gB.whatsAtPos(pos));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testIsPlayerAtPos_empty_board() {
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;
        pos = new BoardPosition(0,0);

        assertFalse(gB.isPlayerAtPos(pos, 'x'));
    }

    @Test
    public void testIsPlayerAtPos_correct_token(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(1, 1);
        gB.placeMarker(pos, 'x');
        arr[1][1] = 'x';

        pos = new BoardPosition(2, 1);
        gB.placeMarker(pos, 'o');
        arr[2][1] = 'o';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertTrue(gB.isPlayerAtPos(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testIsPlayerAtPos_incorrect_token(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(1, 1);
        gB.placeMarker(pos, 'x');
        arr[1][1] = 'x';

        pos = new BoardPosition(2, 1);
        gB.placeMarker(pos, 'o');
        arr[2][1] = 'o';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertFalse(gB.isPlayerAtPos(pos, 'A'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testIsPlayerAtPos_different_token(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(1, 1);
        gB.placeMarker(pos, 'x');
        arr[1][1] = 'x';

        pos = new BoardPosition(1, 2);
        gB.placeMarker(pos, 'o');
        arr[1][2] = 'o';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'A');
        arr[2][2] = 'A';

        assertTrue(gB.isPlayerAtPos(pos, 'A'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testIsPlayerAtPos_win_met(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(1, 1);
        gB.placeMarker(pos, 'x');
        arr[1][1] = 'x';

        pos = new BoardPosition(3, 3);
        gB.placeMarker(pos, 'x');
        arr[3][3] = 'x';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertTrue(gB.isPlayerAtPos(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testPlaceMarker_empty_board(){
        IGameBoard gB = makeAGB(4,4,3);
        BoardPosition pos;

        pos = new BoardPosition(1, 2);
        gB.placeMarker(pos, 'x');
        arr[1][2] = 'x';

        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testPlaceMarker_new_token_empty_column(){
        IGameBoard gB = makeAGB(4,4,3);
        BoardPosition pos;

        pos = new BoardPosition(0, 0);
        gB.placeMarker(pos, 'o');
        arr[0][0] = 'o';

        pos = new BoardPosition(1, 0);
        gB.placeMarker(pos, 'x');
        arr[1][0] = 'x';

        pos = new BoardPosition(0, 2);
        gB.placeMarker(pos, 'A');
        arr[0][2] = 'A';

        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testPlaceMarker_new_token_empty_row(){
        IGameBoard gB = makeAGB(4,4,3);
        BoardPosition pos;

        pos = new BoardPosition(0, 0);
        gB.placeMarker(pos, 'o');
        arr[0][0] = 'o';

        pos = new BoardPosition(0, 1);
        gB.placeMarker(pos, 'x');
        arr[0][1] = 'x';

        pos = new BoardPosition(1, 0);
        gB.placeMarker(pos, 'A');
        arr[1][0] = 'A';

        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testPlaceMarker_same_token_same_column(){
        IGameBoard gB = makeAGB(4,4,3);
        BoardPosition pos;

        pos = new BoardPosition(0, 0);
        gB.placeMarker(pos, 'x');
        arr[0][0] = 'x';

        pos = new BoardPosition(1, 0);
        gB.placeMarker(pos, 'x');
        arr[1][0] = 'x';

        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testPlaceMarker_same_token_same_row(){
        IGameBoard gB = makeAGB(4,4,3);
        BoardPosition pos;

        pos = new BoardPosition(0, 0);
        gB.placeMarker(pos, 'x');
        arr[0][0] = 'x';

        pos = new BoardPosition(0, 1);
        gB.placeMarker(pos, 'x');
        arr[0][1] = 'x';

        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testCheckVerticalWin_win_token_at_the_end(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(1, 2);
        gB.placeMarker(pos, 'x');
        arr[1][2] = 'x';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        pos = new BoardPosition(3, 2);
        gB.placeMarker(pos, 'x');
        arr[3][2] = 'x';

        assertTrue(gB.checkVerticalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testCheckVerticalWin_win_token_in_middle(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(1, 2);
        gB.placeMarker(pos, 'x');
        arr[1][2] = 'x';

        pos = new BoardPosition(3, 2);
        gB.placeMarker(pos, 'x');
        arr[3][2] = 'x';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertTrue(gB.checkVerticalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testCheckVerticalWin_wrong_token(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(1, 2);
        gB.placeMarker(pos, 'x');
        arr[1][2] = 'x';

        pos = new BoardPosition(3, 2);
        gB.placeMarker(pos, 'x');
        arr[3][2] = 'x';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'o');
        arr[2][2] = 'o';

        assertFalse(gB.checkVerticalWin(pos, 'o'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testCheckDiagonal_one_token(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertFalse(gB.checkDiagonalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testDiagonalWin_more_tokens_to_win_left(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        for(int r = 0; r < 5; r++){
            if(r != 2) {
                pos = new BoardPosition(r, r);
                gB.placeMarker(pos, 'x');
                arr[r][r] = 'x';
            }
        }
        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertTrue(gB.checkDiagonalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testDiagonalWin_more_tokens_to_win_right(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        for(int r = 4; r >= 0; r--){
            if(r != 2) {
                pos = new BoardPosition(r, r);
                gB.placeMarker(pos, 'x');
                arr[r][r] = 'x';
            }
        }
        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertTrue(gB.checkDiagonalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testDiagonalWin_last_token_win_top_left(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        pos = new BoardPosition(3, 3);
        gB.placeMarker(pos, 'x');
        arr[3][3] = 'x';

        pos = new BoardPosition(1, 1);
        gB.placeMarker(pos, 'x');
        arr[1][1] = 'x';

        assertTrue(gB.checkDiagonalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testDiagonalWin_last_token_win_bottom_right(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(1, 1);
        gB.placeMarker(pos, 'x');
        arr[1][1] = 'x';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        pos = new BoardPosition(3, 3);
        gB.placeMarker(pos, 'x');
        arr[3][3] = 'x';

        assertTrue(gB.checkDiagonalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testDiagonalWin_last_token_win_top_right(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(3, 1);
        gB.placeMarker(pos, 'x');
        arr[3][1] = 'x';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        pos = new BoardPosition(1, 3);
        gB.placeMarker(pos, 'x');
        arr[1][3] = 'x';

        assertTrue(gB.checkDiagonalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }
    @Test
    public void testDiagonalWin_last_token_win_bottom_left(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(1, 3);
        gB.placeMarker(pos, 'x');
        arr[1][3] = 'x';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        pos = new BoardPosition(3, 1);
        gB.placeMarker(pos, 'x');
        arr[3][1] = 'x';

        assertTrue(gB.checkDiagonalWin(pos, 'x'));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testDraw_empty_board(){
        IGameBoard gB = makeAGB(5,5,3);
        assertFalse(gB.checkForDraw());
    }

    @Test
    public void testDraw_full_board_same_token(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        for(int r = 0; r < gB.getNumRows(); r++){
            for(int c = 0; c < gB.getNumColumns(); c++){
                pos = new BoardPosition(r, c);
                gB.placeMarker(pos, 'x');
                arr[r][c] = 'x';
            }
        }

        assertTrue(gB.checkForDraw());
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testDraw_full_board_different_tokens(){
        IGameBoard gB = makeAGB(3,3,3);
        BoardPosition pos;

        pos = new BoardPosition(0, 0);
        gB.placeMarker(pos, 'o');
        arr[0][0] = 'o';

        pos = new BoardPosition(0, 1);
        gB.placeMarker(pos, 'x');
        arr[0][1] = 'x';

        pos = new BoardPosition(0, 2);
        gB.placeMarker(pos, 'o');
        arr[0][2] = 'o';

        pos = new BoardPosition(1, 0);
        gB.placeMarker(pos, 'o');
        arr[1][0] = 'o';

        pos = new BoardPosition(1, 1);
        gB.placeMarker(pos, 'x');
        arr[1][1] = 'x';

        pos = new BoardPosition(1, 2);
        gB.placeMarker(pos, 'o');
        arr[1][2] = 'o';

        pos = new BoardPosition(2, 0);
        gB.placeMarker(pos, 'x');
        arr[2][0] = 'x';

        pos = new BoardPosition(2, 1);
        gB.placeMarker(pos, 'o');
        arr[2][1] = 'o';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertTrue(gB.checkForDraw());
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testDraw_partially_full_board(){
        IGameBoard gB = makeAGB(3,3,3);
        BoardPosition pos;

        pos = new BoardPosition(0, 0);
        gB.placeMarker(pos, 'o');
        arr[0][0] = 'o';

        pos = new BoardPosition(0, 1);
        gB.placeMarker(pos, 'x');
        arr[0][1] = 'x';

        pos = new BoardPosition(0, 2);
        gB.placeMarker(pos, 'o');
        arr[0][2] = 'o';

        pos = new BoardPosition(1, 1);
        gB.placeMarker(pos, 'x');
        arr[1][1] = 'x';

        pos = new BoardPosition(1, 2);
        gB.placeMarker(pos, 'o');
        arr[1][2] = 'o';

        pos = new BoardPosition(2, 0);
        gB.placeMarker(pos, 'x');
        arr[2][0] = 'x';

        pos = new BoardPosition(2, 1);
        gB.placeMarker(pos, 'o');
        arr[2][1] = 'o';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'x');
        arr[2][2] = 'x';

        assertFalse(gB.checkForDraw());
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testWhatsAtPos_out_of_bounds(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(4, 4);
        gB.placeMarker(pos, 'x');
        arr[4][4] = 'x';

        assertEquals('x', gB.whatsAtPos(pos));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testWhatsAtPos_different_token(){
        IGameBoard gB = makeAGB(5,5,3);
        BoardPosition pos;

        pos = new BoardPosition(2, 0);
        gB.placeMarker(pos, 'o');
        arr[2][0] = 'o';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'o');
        arr[2][2] = 'o';

        pos = new BoardPosition(1, 1);
        gB.placeMarker(pos, 'x');
        arr[1][1] = 'x';

        pos = new BoardPosition(3, 1);
        gB.placeMarker(pos, 'x');
        arr[3][1] = 'x';

        pos = new BoardPosition(2, 1);
        gB.placeMarker(pos, 'A');
        arr[2][1] = 'A';

        assertEquals('A', gB.whatsAtPos(pos));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testWhatsAtPos_win_met(){
        IGameBoard gB = makeAGB(3,3,3);
        BoardPosition pos;

        pos = new BoardPosition(0, 0);
        gB.placeMarker(pos, 'o');
        arr[0][0] = 'o';

        pos = new BoardPosition(0, 1);
        gB.placeMarker(pos, 'x');
        arr[0][1] = 'x';

        pos = new BoardPosition(0, 2);
        gB.placeMarker(pos, 'x');
        arr[0][2] = 'x';

        pos = new BoardPosition(1, 0);
        gB.placeMarker(pos, 'x');
        arr[1][0] = 'x';

        pos = new BoardPosition(1, 2);
        gB.placeMarker(pos, 'o');
        arr[1][2] = 'o';

        pos = new BoardPosition(2, 0);
        gB.placeMarker(pos, 'o');
        arr[2][0] = 'o';

        pos = new BoardPosition(2, 1);
        gB.placeMarker(pos, 'o');
        arr[2][1] = 'o';

        pos = new BoardPosition(2, 2);
        gB.placeMarker(pos, 'o');
        arr[2][2] = 'o';

        pos = new BoardPosition(1, 1);
        gB.placeMarker(pos, 'x');
        arr[1][1] = 'x';

        assertEquals('x', gB.whatsAtPos(pos));
        assertEquals(gBString(arr), gB.toString());
    }

    @Test
    public void testWhatsAtPos_empty_board(){
        IGameBoard gB = makeAGB(3,3,3);
        BoardPosition pos;
        pos = new BoardPosition(1,1);

        assertEquals(' ', gB.whatsAtPos(pos));
        assertEquals(gBString(arr), gB.toString());
    }

}
