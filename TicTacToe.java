
import java.util.Scanner;


public class TicTacToe {

    enum Status{
        WIN,
        DRAW,
        CONTINUE;
    }

    private static final int BOARDSIZE = 3;
    private static char[][] board = new char[BOARDSIZE][BOARDSIZE];
    private boolean firstPlayer = true;
    private boolean gameOver = false;
    

    public void play()
    {
        int col = -1;
        int row = -1;

        while (gameOver != true)
        {
            printBoard();
            if(firstPlayer)
            {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Player X's turn.");
            System.out.println("Player X, enter row: ");
            row = scanner.nextInt();
            row--;
            System.out.println("Player X, enter column: ");
            col = scanner.nextInt();
            col--; 
            //scanner.close();
    
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

            if(validMove(row, col) == true)
            {
                if(firstPlayer)
                {
                    printSymbol(row, col, 'X');
                }
                else if(!firstPlayer)
                {
                    printSymbol(row, col, 'O');
                }
            }
            //fix here!!
                else
             {
                firstPlayer = !firstPlayer;
             }
            

            if(gameStatus() == Status.WIN)
            {
                if(firstPlayer)
                {
                    printBoard();
                    printStatus('X');
                    gameOver = !gameOver;
                    
                }
                else
                {
                    printBoard();
                    printStatus('O');
                    gameOver = !gameOver;
                }
            }
            if(gameStatus() == Status.DRAW)
            {
                printBoard();
                printStatus('T');
                gameOver = !gameOver;
            }
            firstPlayer = !firstPlayer;
         
        }
        
       
    }
    public TicTacToe() //constructor
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

    public boolean validMove(int row, int col)
    {

        if ( row < 0 || row >= BOARDSIZE || col < 0 || col >= BOARDSIZE )
        {
            System.out.println("Invalid Move!");
            return false;
        }

        if(board[row][col] == 'X' || board[row][col] == 'O')
        {
            System.out.println("Space already occupied, choose another!");
            return false;
        } 
        return true;
    }

    public Status gameStatus()
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

        // Checks for Draw condition
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                if (board[i][j] == ' ') {
                    return Status.CONTINUE;
                }
            }
		} 
        return Status.DRAW;
    }

    public static void main(String[] args)
    {
        TicTacToe run = new TicTacToe();
        run.play();
    }

}
