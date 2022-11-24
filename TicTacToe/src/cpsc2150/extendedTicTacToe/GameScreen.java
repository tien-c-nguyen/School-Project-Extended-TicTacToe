package cpsc2150.extendedTicTacToe;

import cpsc2150.extendedTicTacToe.models.BoardPosition;
import cpsc2150.extendedTicTacToe.models.GameBoard;
import cpsc2150.extendedTicTacToe.models.GameBoardMem;
import cpsc2150.extendedTicTacToe.models.IGameBoard;
import java.util.Scanner;

public class GameScreen {
    private static final int MAX_SIZE = 100;
    private static final int MIN_SIZE = 3;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 10;

    public static void main(String [] args){
        int playerAmt = 0;
        boolean inRange = false,yesOrNo = false;
        Scanner input = new Scanner(System.in);
        do{
            do{
                System.out.println("How many players?");
                playerAmt = Integer.parseInt(input.nextLine());
                if(playerAmt < MIN_PLAYERS){
                    System.out.println("Must be at least " + MIN_PLAYERS +  " players");
                    inRange = false;
                }else if(playerAmt > MAX_PLAYERS){
                    System.out.println("Must be " + MAX_PLAYERS + " players or fewer");
                        inRange = false;
                }else {
                    inRange = true;
                }

            }while(!inRange);

            char [] tokens = new char[playerAmt];
            char userChar = ' ';
            int tokenCounter = 0;
            boolean tokenAvailable = true;

            for(int i = 0; i < tokens.length; i++) {
                do {
                    System.out.println("Enter the character to represent player " + (i + 1));
                    userChar = (input.nextLine()).charAt(0);
                    userChar = Character.toUpperCase(userChar);

                    for (int j = 0; j < i; j++) {
                        if (tokens[j] != userChar)
                            tokenCounter++;
                    }

                    if (tokenCounter == i) {
                        tokens[i] = userChar;
                        tokenAvailable = true;
                    }
                    else {
                        System.out.println(userChar + " is already taken as a player token!");
                        tokenAvailable = false;
                    }
                    tokenCounter = 0;
                }while(!tokenAvailable);
            }

            int rows = 0;
            System.out.println("How many rows?");
            rows = Integer.parseInt(input.nextLine());
            while( !(rows >= MIN_SIZE && rows <= MAX_SIZE)){
                System.out.println("Rows must be between " + MIN_SIZE + " and  " + MAX_SIZE);
                System.out.println("How many rows?");
                rows = Integer.parseInt(input.nextLine());
            }

            int columns = 0;
            System.out.println("How many columns?");
            columns = Integer.parseInt(input.nextLine());
            while( !(columns >= MIN_SIZE && columns <= MAX_SIZE)){
                System.out.println("Columns must be between " + MIN_SIZE + " and  " + MAX_SIZE);
                System.out.println("How many columns?");
                columns = Integer.parseInt(input.nextLine());
            }

            int numToWin = 0;

            System.out.println("How many in a row to win?");
            numToWin = Integer.parseInt(input.nextLine());
            while(!(numToWin > 0 && numToWin <= columns && numToWin <= rows)){
                System.out.println("How many in a row to win?");
                numToWin = Integer.parseInt(input.nextLine());
            }

            char mem = 'M';
            char fast = 'F';
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            userChar = (input.nextLine()).charAt(0);
            userChar = Character.toUpperCase(userChar);
            while(!(userChar == mem || userChar == fast)){
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                userChar = (input.nextLine()).charAt(0);
                userChar = Character.toUpperCase(userChar);
            }
            IGameBoard gB;
            if(userChar == fast)
                gB = new GameBoard(rows, columns, numToWin);
            else
                gB = new GameBoardMem(rows, columns, numToWin);


            int rowPos, colPos, currPlayerCount = 0;
            char curr_player;

            boolean gameOver = false;
            String userStr;

            gameOver = false;
            curr_player = tokens[0];
            System.out.println(gB.toString());
            while(!(gameOver)) {
                System.out.println("Player " + curr_player + " Please enter your ROW");
                rowPos = Integer.parseInt(input.nextLine());

                System.out.println("Player " + curr_player + " Please enter your COLUMN");
                colPos = Integer.parseInt(input.nextLine());

                BoardPosition bP = new BoardPosition(rowPos, colPos);

                while (!gB.checkSpace(bP)) {
                    System.out.println("That space is unavailable, please pick again");
                    System.out.println("Player " + curr_player + " Please enter your ROW");
                    rowPos = Integer.parseInt(input.nextLine());

                    System.out.println("Player " + curr_player + " Please enter your COLUMN");
                    colPos = Integer.parseInt(input.nextLine());

                    bP = new BoardPosition(rowPos, colPos);
                }
                gB.placeMarker(bP, curr_player);
                System.out.println(gB.toString());
                //checks for a win or draw
                if (gB.checkForWinner(bP)) {
                    System.out.println("Player " + curr_player + " wins!");
                    gameOver = true;
                }else {
                    if (gB.checkForDraw()) {
                        System.out.println("It's a draw!");
                        gameOver = true;
                    } else {
                        currPlayerCount = ++currPlayerCount % tokens.length;
                        curr_player = tokens[currPlayerCount];
                    }
                }
            }
            do {
                System.out.println("Would you like to play again? Y/N");
                userStr = input.nextLine();
                userChar = userStr.charAt(0);
                if (Character.toUpperCase(userChar) == 'Y') {
                    yesOrNo = true;
                } else if (Character.toUpperCase(userChar) == 'N') {
                    yesOrNo = false;
                }
            }while(Character.toUpperCase(userChar) != 'N' && Character.toUpperCase(userChar) != 'Y');
        }while(yesOrNo);
    }
}
