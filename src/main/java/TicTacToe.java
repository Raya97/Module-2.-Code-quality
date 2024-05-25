import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    private static final char EMPTY = ' ';
    private static final char PLAYER_MARK = 'X';
    private static final char AI_MARK = 'O';
    private static final String CREATED_BY = "Created by Shreyas Saha. Thanks for playing!";

    private final char[] board;
    private boolean isBoardInitialized;
    private int winner;
    private final Scanner scanner;

    public TicTacToe() {
        this.board = initializeBoard();
        this.isBoardInitialized = false;
        this.winner = 0;
        this.scanner = new Scanner(System.in);
    }


    public void startGame() {
        displayWelcomeMessage(); // Display welcome message

        while (true) {
            displayBoard(); // Display the current state of the board

            if (!isBoardInitialized) {
                clearBoard(); // Clear the board if it is not initialized
                isBoardInitialized = true;
            }

            if (checkGameOver()) {
                displayGameOverMessage(); // Display game over message based on the winner
                break;
            }

            playerMove(); // Handle player's move

            if (isWinner(PLAYER_MARK)) {
                winner = 1; // Player wins
                continue;
            }

            if (!isBoardAvailable()) {
                winner = 3; // Draw
                continue;
            }

            aiMove(); // Handle AI's move

            if (isWinner(AI_MARK)) {
                winner = 2; // AI wins
            }
        }

        scanner.close(); // Close the scanner resource
    }


    private void displayWelcomeMessage() {
        System.out.println("Enter box number to select. Enjoy!\n");
    }


    private char[] initializeBoard() {
        return new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' }; // Initialize the board with numbers
    }


    private void displayBoard() {
        // Display the current state of the board
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }


    private void clearBoard() {
        // Clear the board by filling all positions with EMPTY
        Arrays.fill(board, EMPTY);
    }


    private boolean checkGameOver() {
        // Check if the game is over based on the winner status
        return winner == 1 || winner == 2 || winner == 3;
    }


    private void displayGameOverMessage() {
        // Display the appropriate game over message based on the winner status
        if (winner == 1) {
            System.out.println("You won the game!\n" + CREATED_BY);
        } else if (winner == 2) {
            System.out.println("You lost the game!\n" + CREATED_BY);
        } else if (winner == 3) {
            System.out.println("It's a draw!\n" + CREATED_BY);
        }
    }


    private void playerMove() {
        // Handle the player's move
        while (true) {
            System.out.print("Enter your move (1-9): ");
            int input = scanner.nextInt();
            if (input > 0 && input < 10) {
                if (board[input - 1] == PLAYER_MARK || board[input - 1] == AI_MARK) {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    board[input - 1] = PLAYER_MARK;
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }


    private boolean isWinner(char mark) {
        // Check if the given mark  is a winner
        return (board[0] == mark && board[1] == mark && board[2] == mark) ||
                (board[3] == mark && board[4] == mark && board[5] == mark) ||
                (board[6] == mark && board[7] == mark && board[8] == mark) ||
                (board[0] == mark && board[3] == mark && board[6] == mark) ||
                (board[1] == mark && board[4] == mark && board[7] == mark) ||
                (board[2] == mark && board[5] == mark && board[8] == mark) ||
                (board[0] == mark && board[4] == mark && board[8] == mark) ||
                (board[2] == mark && board[4] == mark && board[6] == mark);
    }


    private boolean isBoardAvailable() {
        // Check if there are any available positions on the board
        for (char c : board) {
            if (c != PLAYER_MARK && c != AI_MARK) {
                return true;
            }
        }
        return false;
    }


    private void aiMove() {
        // Handle the AI's move
        while (true) {
            int rand = (int) (Math.random() * 9);
            if (board[rand] != PLAYER_MARK && board[rand] != AI_MARK) {
                board[rand] = AI_MARK;
                break;
            }
        }
    }
}
