package ru.geekbrains.home_task_Level1_3;
// import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '_';

    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int fieldWin;
    private static char[][] field;
    private static int warninX = 0;
    private static int warninY = 0;



    private static final Scanner SCANNER = new Scanner(System.in);
    //  private static final Random RANDOM = new Random();

    private static void initField() {
        fieldSizeY = 5;
        fieldSizeX = 5;
        fieldWin = 4;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.println("------");
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
    }

    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >=0 && y < fieldSizeY;
    }

    private static boolean isEmptyCell(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты хода X и Y (от 1 до 3) через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValidCell(x, y) || !isEmptyCell(x, y));
        field[y][x] = DOT_HUMAN;
        warninX = x; warninY = y;
    }


    private static void aiTurn() {
        int x=warninX;
        int y=warninY;
        y=y-1;
        for (int iy =0; iy < 3; iy++) {

            for (int i = 0; i < 3; i++) {
                if (isValidCell(x, y) && isEmptyCell(x, y)) {
                    field[y][x] = DOT_AI;
                    return;
                }
                x = x + 1;
            }
            x = warninX -1;
            y = y + 1;
        }
  }

    private static boolean isFieldFull() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    private static boolean checkWin(char c) {
        // Проверка по строкам
        // x y задают адрес ячеки n сдвиг (0 или 1) winCount - счетчик совпадений
        // Может
        int n, winCount = 0;
        for (int y =0; y < fieldSizeY; y++) {
            for (n = 0; n < 2; n++) {
                for ( int x = 0; x < fieldWin; x++) {
                    if (field[y][x + n] == c) {winCount++;}
                }
                if (winCount == fieldWin) {return true;}
                winCount = 0;
            }
            winCount = 0;
        }
        // Проверка по столбцам

        winCount = 0;
        for (int x =0; x < fieldSizeX; x++) {
            for (n = 0; n < 2; n++) {
                for ( int y = 0; y < fieldWin; y++) {
                    if (field[y+n][x] == c) {winCount++;}
                }
                if (winCount == fieldWin) {return true;}
                winCount = 0;
            }
            winCount = 0;
        }

        // Проверка по диагоналям

          // Пр главной

        winCount = 0;
        for (int ny = 0; ny < 2; ny++) {
            for (n = 0; n < 2; n++) {
                for (int y = 0; y < fieldWin; y++) {
                    if (field[y+ny][y + n] == c) {winCount++;}
                }
                if (winCount == fieldWin) {return true;}
                winCount = 0;
            }
            winCount = 0;
        }

          // По второстеренной

        winCount = 0;
        for (int ny = 0; ny < 2; ny++) {
            for (n = 0; n < 2; n++) {
                for (int y = 0; y < fieldWin; y++) {
                    if (field[fieldSizeY - 1 - y - ny][y+n] == c) {winCount++;}
                }
                if (winCount == fieldWin) {return true;}
                winCount = 0;
            }
            winCount = 0;
        }

        return false;
    }


    public static void main(String[] args) {
        //        while (true) {

        playOneRound();
//            System.out.println("Play again?");
//            if (SCANNER.next().equals("no"))
//                break;
//        }



    }
    private static void playOneRound() {
        initField();
        printField();
        while (true) {
            humanTurn();
            printField();
            if (checkWin(DOT_HUMAN)) {
                System.out.println("Выиграл игрок!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(DOT_AI)) {
                System.out.println("Выиграл компьютер!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }

}
