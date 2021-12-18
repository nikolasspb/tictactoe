package tictactoe;

import java.util.Scanner;

public class Main {
    private static String[][] ar = new String[3][3];
    private static int[] userInput = new int[2];

    public static void main(String[] args) {
        boolean repeatMove = true;
        // write your code here
        startGame();
        int userCharacter = 0;
        while (repeatMove) {
            checkUserInput();
            userCharacter++;
            adToArr(userCharacter);
            printArr();
            repeatMove = checkTheWiner();
        }
    }

    private static void startGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ar[i][j] = " ";
            }
        }
        printArr();
    }

    private static boolean checkUserInput() {
        boolean inCorrect = true;
        while (inCorrect) {
            System.out.print("Enter the coordinates: ");
            Scanner sc = new Scanner(System.in);
            String[] fromUser = sc.nextLine().split(" ");
            int kolInput = 0;
            for (int a = 0; a < 2; a++) {
                if (Character.isDigit(fromUser[a].charAt(0))) {
                    int inp = Integer.parseInt(fromUser[a]);
                    if (3 < inp || inp < 1) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        break;
                    } else {
                        userInput[a] = inp;
                    }
                    kolInput++;
                } else {
                    System.out.println("You should enter numbers!");
                    break;
                }
            }
            if (kolInput == 2) {
                inCorrect = false;
                if ("X".equals(ar[userInput[0] - 1][userInput[1] - 1]) || "O".equals(ar[userInput[0] - 1][userInput[1] - 1])) {
                    System.out.println("This cell is occupied! Choose another one!");
                    inCorrect = true;
                }
            }
        }
        return true;
    }

    private static void adToArr(int userCharacter) {
        if (userCharacter % 2 == 0) {
            ar[userInput[0] - 1][userInput[1] - 1] = "O";
        } else {
            ar[userInput[0] - 1][userInput[1] - 1] = "X";
        }
    }

    private static void printArr() {
        System.out.print("---------\n");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + ar[i][j]);
            }
            System.out.print(" |\n");
        }
        System.out.print("---------\n");
    }

    private static boolean checkTheWiner() {
        int kolX = 0;
        int kolO = 0;
        int diagX = 0;
        int diagO = 0;
        int diag_X = 0;
        int diag_O = 0;
        boolean winX = false;
        boolean winO = false;
        for (int i = 0; i < 3; i++) {
            int strX = 0;
            int strO = 0;
            int colX = 0;
            int colO = 0;
            for (int j = 0; j < 3; j++) {
                if ("X".equals(ar[j][i])) {
                    colX++;
                }
                if ("O".equals(ar[j][i])) {
                    colO++;
                }
                if ("X".equals(ar[i][j])) {
                    kolX++;
                    strX++;
                    if (i == j) {
                        diagX++;
                    }
                    if ((i + j) == 2) {
                        diag_X++;
                    }
                }
                if ("O".equals(ar[i][j])) {
                    kolO++;
                    strO++;
                    if (i == j) {
                        diagO++;
                    }
                    if ((i + j) == 2) {
                        diag_O++;
                    }
                }
            }
            if (strX == 3 || diagX == 3 || diag_X == 3 || colX == 3) {
                winX = true;
            }
            if (strO == 3 || diagO == 3 || diag_O == 3 || colO == 3) {
                winO = true;
            }
        }
        if (Math.abs(kolX - kolO) > 1 || (winX && winO)) {
            System.out.println("Impossible");
            return false;
        } else {
            if (winX) {
                System.out.println("X wins");
                return false;
            } else if (winO) {
                System.out.println("O wins");
                return false;
            } else if (!winX && !winO && (kolX + kolO) == 9) {
                System.out.println("Draw");
                return false;
            }
        }
        return true;
    }
}
