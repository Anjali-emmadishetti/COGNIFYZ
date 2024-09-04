import java.util.Scanner;

public class TicTacToe {
    static char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char player = 'X';

        while (true) {
            printBoard();
            System.out.print("Player " + player + ", enter move (1-9): ");
            int move = sc.nextInt() - 1;
            if (board[move / 3][move % 3] == 'X' || board[move / 3][move % 3] == 'O') {
                System.out.println("Invalid move!");
                continue;
            }
            board[move / 3][move % 3] = player;
            if (checkWin(player)) {
                printBoard();
                System.out.println("Player " + player + " wins!");
                break;
            }
            if (++move == 9) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }
            player = (player == 'X') ? 'O' : 'X';
        }
        sc.close();
    }

    static void printBoard() {
        for (char[] row : board) {
            for (char cell : row) System.out.print(cell + " ");
            System.out.println();
        }
    }

    static boolean checkWin(char p) {
        for (int i = 0; i < 3; i++)
            if ((board[i][0] == p && board[i][1] == p && board[i][2] == p) ||
                (board[0][i] == p && board[1][i] == p && board[2][i] == p))
                return true;
        return (board[0][0] == p && board[1][1] == p && board[2][2] == p) ||
               (board[0][2] == p && board[1][1] == p && board[2][0] == p);
    }
}
