import java.util.Arrays;
import java.util.Scanner;

public class App {

    private static final char EMPTY = ' ';
    private static final char PLAYER_MARK = 'X';
    private static final char AI_MARK = 'O';
    private static final String CREATED_BY = "Created by Shreyas Saha. Thanks for playing!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] board = initializeBoard();
        boolean isBoardInitialized = false; // Прапорець для перевірки, чи ініціалізовано дошку
        int winner = 0; // Змінна для збереження статусу переможця

        displayWelcomeMessage(); // Вивести привітальне повідомлення

        while (true) {
            displayBoard(board); // Вивести поточний стан дошки

            if (!isBoardInitialized) {
                clearBoard(board); // Очистити дошку, якщо вона не ініціалізована
                isBoardInitialized = true;
            }

            if (checkGameOver(winner)) {
                displayGameOverMessage(winner); // Вивести повідомлення про завершення гри залежно від переможця
                break;
            }

            playerMove(scanner, board); // Обробити хід гравця

            if (isWinner(board, PLAYER_MARK)) {
                winner = 1; // Гравець виграв
                continue;
            }

            if (!isBoardAvailable(board)) {
                winner = 3; // Нічия
                continue;
            }

            aiMove(board); // Обробити хід комп'ютера

            if (isWinner(board, AI_MARK)) {
                winner = 2; // Комп'ютер виграв
            }
        }

        scanner.close(); // Закрити ресурс сканера
    }

    private static void displayWelcomeMessage() {
        System.out.println("Введіть номер коробки для вибору. Насолоджуйтесь!\n");
    }

    private static char[] initializeBoard() {
        return new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' }; // Ініціалізувати дошку номерами
    }

    private static void displayBoard(char[] board) {
        // Вивести поточний стан дошки
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private static void clearBoard(char[] board) {
        // Очистити дошку, заповнивши всі позиції символом EMPTY
        Arrays.fill(board, EMPTY);
    }

    private static boolean checkGameOver(int winner) {
        // Перевірити, чи гра завершена залежно від статусу переможця
        return winner == 1 || winner == 2 || winner == 3;
    }

    private static void displayGameOverMessage(int winner) {
        // Вивести відповідне повідомлення про завершення гри залежно від статусу переможця
        if (winner == 1) {
            System.out.println("Ви виграли гру!\n" + CREATED_BY);
        } else if (winner == 2) {
            System.out.println("Ви програли гру!\n" + CREATED_BY);
        } else if (winner == 3) {
            System.out.println("Нічия!\n" + CREATED_BY);
        }
    }

    private static void playerMove(Scanner scanner, char[] board) {
        // Обробити хід гравця
        while (true) {
            System.out.print("Введіть свій хід (1-9): ");
            int input = scanner.nextInt();
            if (input > 0 && input < 10) {
                if (board[input - 1] == PLAYER_MARK || board[input - 1] == AI_MARK) {
                    System.out.println("Ця коробка вже зайнята. Введіть іншу.");
                } else {
                    board[input - 1] = PLAYER_MARK;
                    break;
                }
            } else {
                System.out.println("Неправильне введення. Введіть знову.");
            }
        }
    }

    private static boolean isWinner(char[] board, char mark) {
        // Перевірити, чи є даний маркер переможцем
        return (board[0] == mark && board[1] == mark && board[2] == mark) ||
                (board[3] == mark && board[4] == mark && board[5] == mark) ||
                (board[6] == mark && board[7] == mark && board[8] == mark) ||
                (board[0] == mark && board[3] == mark && board[6] == mark) ||
                (board[1] == mark && board[4] == mark && board[7] == mark) ||
                (board[2] == mark && board[5] == mark && board[8] == mark) ||
                (board[0] == mark && board[4] == mark && board[8] == mark) ||
                (board[2] == mark && board[4] == mark && board[6] == mark);
    }

    private static boolean isBoardAvailable(char[] board) {
        // Перевірити, чи є на дошці доступні позиції
        for (char c : board) {
            if (c != PLAYER_MARK && c != AI_MARK) {
                return true;
            }
        }
        return false;
    }

    private static void aiMove(char[] board) {
        // Обробити хід комп'ютера
        while (true) {
            int rand = (int) (Math.random() * 9);
            if (board[rand] != PLAYER_MARK && board[rand] != AI_MARK) {
                board[rand] = AI_MARK;
                break;
            }
        }
    }
}
