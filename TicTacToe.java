
import java.util.Scanner;


public class TicTacToe {

    enum Status{
        WIN,
        DRAW,
        CONTINUE;
    }

    private static final int BOARDSIZE = 3; // sets BOARDSIZE to 3
    private static char[][] board = new char[BOARDSIZE][BOARDSIZE]; // creates 3x3 array
    private boolean firstPlayer = true;
    private boolean gameOver = false;
    

    public void play()
    {
        int col = -1; // initia null values for col and row
        int row = -1;

        while (gameOver != true)
        {
            printBoard(); // function to print the current state of the board
            if(firstPlayer)
            {

            Scanner scanner = new Scanner(System.in); // scanner to get the input from the terminal/user
            System.out.println("Player X's turn.");
            System.out.println("Player X, enter row: ");
            row = scanner.nextInt();
            row--;
            System.out.println("Player X, enter column: ");
            col = scanner.nextInt();
            col--; 
            //scanner.close(); does not work if I add it to the program
    
            }
            else
            {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Player O's turn.");
            System.out.println("Player O, enter row: ");
            row = scanner.nextInt();
            row--;
            System.out.println("Player O, enter column: ");
            col = scanner.nextInt();
            col--;
            //scanner.close();

            }

            if(validMove(row, col) == true) // if move is valid, set values for player 1 or 2
            {
                if(firstPlayer)
                {
                    printSymbol(row, col, 'X'); // sets symbol to table for first player X
                }
                else if(!firstPlayer)
                {
                    printSymbol(row, col, 'O'); // sets symbol in table for 2nd player O
                }
            }
                else // when error is made, the turn goes back to the current player
             {
                firstPlayer = !firstPlayer;
             }
            

            if(gameStatus() == Status.WIN) // compares current game status
            {
                if(firstPlayer) // if first plauer wins, print the status with X saying X won 
                {
                    printBoard(); // prints the board
                    printStatus('X'); // X won
                    gameOver = !gameOver; // sets gameover value to true
                    
                }
                else
                {
                    printBoard(); 
                    printStatus('O'); // O won
                    gameOver = !gameOver; // setsgameOver value to ture
                }
            }
            if(gameStatus() == Status.DRAW) // when the game ends in a draw
            {
                printBoard();
                printStatus('T');
                gameOver = !gameOver;
            }
            firstPlayer = !firstPlayer; // switches players
         
        }
        
       
    }
    public TicTacToe() //constructor (initializes board with empty character spaces
    {

        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                board[i][j] = ' '; // Example initialization
            }
        }
        
    }

    //Prints out the board with the current values
    public static void printBoard()
    {
        
        System.out.println("-------------");
        System.out.println("|   |   |   |");
        System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("|   |   |   |");
        System.out.println("-------------");
        System.out.println("|   |   |   |");
        System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("|   |   |   |");
        System.out.println("-------------");
        System.out.println("|   |   |   |");
        System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
        System.out.println("|   |   |   |");
        System.out.println("-------------");
    }

    // sets the current value given into the board
    public static void printSymbol( int row, int col, char val)
    {
        board[row][col]= val;
    }

	// The different outcomes for winners and draw based off char value given
    public static void printStatus(char player)
    {
        if(player == 'T')
        {
            System.out.println("Game is a Draw!");
        }
        else if(player == 'X')
        {
            System.out.println("Player X wins!");
        }
        else
        {
            System.out.println("Player O wins!");
        }
    }

    public boolean validMove(int row, int col) // determines if move is valid
    {

        if ( row < 0 || row >= BOARDSIZE || col < 0 || col >= BOARDSIZE ) // move is in bounds of table
        {
            System.out.println("Invalid Move!");
            return false;
        }

        if(board[row][col] == 'X' || board[row][col] == 'O') // makes sure that the spot selected is empty
        {
            System.out.println("Space already occupied, choose another!");
            return false;
        } 
        return true;
    }

    public Status gameStatus() // determines the current game status (all  8 possible win scenarios and tie scenario)
    {
        //Win for Rows
        if( board[0][0] != ' ' && board[0][0] == board[0][1] && board[0][1] == board[0][2])
        {
            return Status.WIN;
        }
        if( board[1][0] != ' ' && board[1][0] == board[1][1] && board[1][1] == board[1][2])
        {
            return Status.WIN;
        }
        if( board[2][0] != ' ' && board[2][0] == board[2][1] && board[2][1] == board[2][2])
        {
            return Status.WIN;
        }
        //Win for Columns
        if( board[0][0] != ' ' && board[0][0] == board[1][0] && board[1][0] == board[2][0])
        {
            return Status.WIN;
        }
        if( board[0][1] != ' ' && board[0][1] == board[1][1] && board[1][1] == board[2][1])
        {
            return Status.WIN;
        }
        if( board[0][2] != ' ' && board[0][2] == board[1][2] && board[1][2] == board[2][2])
        {
            return Status.WIN;
        }
        //Win for Diagonal
        if( board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
        {
            return Status.WIN;
        }
        if( board[2][0] != ' ' && board[2][0] == board[1][1] && board[1][1] == board[0][2])
        {
            return Status.WIN;
        }

        // Checks for Draw condition, any blank space still in the array means it is still going
        for (int i = 0; i < BOARDSIZE; i++) { //goes through 2d array to find any blank spaces
            for (int j = 0; j < BOARDSIZE; j++) {
                if (board[i][j] == ' ') {
                    return Status.CONTINUE;
                }
            }
		} 
        return Status.DRAW; // if no blank spaces found, then board is full = draw
    }

    public static void main(String[] args)
    {
        TicTacToe game = new TicTacToe(); // creates a new instance of TicTacToe
        game.play(); //runs the game
    }

}
