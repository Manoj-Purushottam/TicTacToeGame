import java.util.Scanner;

public class TicTacToe{

    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static Scanner sc = new Scanner(System.in);

    // Initialize board
    private static void clearBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '.';
            }
        }
    }

    // Display board
    private static void showBoard() {
        System.out.println("\n    0   1   2");
        System.out.println("  -------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n  -------------");
        }
    }

    // Validate move
    private static boolean validMove(int r, int c) {
        return r >= 0 && r < SIZE &&
               c >= 0 && c < SIZE &&
               board[r][c] == '.';
    }

    // Winning check
    private static boolean checkWin(char mark) {

        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark)
                return true;

            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark)
                return true;
        }

        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark)
            return true;

        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark)
            return true;

        return false;
    }

    // Draw check
    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == '.')
                    return false;
        return true;
    }

    // Player move
    private static void makeMove(String playerName, char symbol) {
        int r, c;
        while (true) {
            System.out.print(playerName + " (" + symbol + ") enter row and column: ");
            r = sc.nextInt();
            c = sc.nextInt();

            if (validMove(r, c)) {
                board[r][c] = symbol;
                break;
            } else {
                System.out.println("❌ Invalid move. Try again.");
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("🎮 TIC TAC TOE GAME");

        System.out.print("Enter Player 1 name (X): ");
        String playerX = sc.nextLine();

        System.out.print("Enter Player 2 name (O): ");
        String playerO = sc.nextLine();

        boolean playAgain;

        do {
            clearBoard();
            char currentSymbol = 'X';
            boolean gameFinished = false;

            showBoard();

            while (!gameFinished) {

                if (currentSymbol == 'X') {
                    makeMove(playerX, 'X');
                } else {
                    makeMove(playerO, 'O');
                }

                showBoard();

                if (checkWin(currentSymbol)) {
                    String winner = (currentSymbol == 'X') ? playerX : playerO;
                    System.out.println("🏆 Congratulations " + winner + "! You won!");
                    gameFinished = true;
                } else if (isBoardFull()) {
                    System.out.println("🤝 Match Draw!");
                    gameFinished = true;
                } else {
                    currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = sc.next().equalsIgnoreCase("yes");
            sc.nextLine(); // clear buffer

        } while (playAgain);

        System.out.println("👋 Thanks for playing!");
        sc.close();
    }
}
