package ru.gressor.algorithms.lesson5;

public class Main {

    private static void iterPrint(int i) {
        while (i >= 0) {
            System.out.print(i + " ");
            i--;
        }
        System.out.print(i + "h ");
    }

    private static void recPrint(int i) {
        if (i >= 0) {
            System.out.print(i + " ");
            recPrint(--i);
        }
        System.out.print(i + "h ");
    }

    private static int power(int a, int b) {
        int result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
    }

    private static int poweRec(int a, int b) {
        return (b == 0) ? 1 : poweRec(a, b - 1) * a;
    }

    public static void main(String[] args) {
        iterPrint(5);
        System.out.println();
        recPrint(5);
        System.out.println();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%6d", routes(i, j));
            }
            System.out.println();
        }

        int[][] desk = new int[8][8];
        knightMove(desk, 1, 0, 1);
        printDesk(desk);
        System.out.println(op);
    }

    // F(x, 0) = 1
    // F(0, y) = 1
    // F(x, y) = F(x - 1, y) + F(x, y - 1)

    private static int routes(int x, int y) {
        if (x == 0 || y == 0) {
            return 1;
        } else {
            return routes(x - 1, y) + routes(x, y - 1);
        }
    }

    private static int[][] kMoves = {
            {-2, 1}, {-1, 2}, {1, 2}, {2, 1},
            {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };

    private static boolean isPossible(int[][] desk, int x, int y) {
        return x >= 0 && x < desk.length &&
                y >= 0 && y < desk[0].length &&
                desk[x][y] == 0;
    }

    private static int op = 0;
    private static boolean knightMove(int[][] desk, int currX, int currY, int move) {
        desk[currX][currY] = move;
        if (move > desk.length * desk[0].length - 1) return true;

        int nextX, nextY;
        for (int i = 0; i < 7; i++) {
            op++;
            nextX = currX + kMoves[i][1];
            nextY = currY + kMoves[i][0];
            if (isPossible(desk, nextX, nextY) && knightMove(desk, nextX, nextY, move + 1)) {
                return true;
            }
        }
        desk[currX][currY] = 0;
        return false;
    }

    private static void printDesk(int[][] desk) {
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk[0].length; j++) {
                System.out.printf("%3d", desk[i][j]);
            }
            System.out.println();
        }
    }

    // + King routes
    // + chess Knight
    // h Hanoi
    // h 8 Queens
}
