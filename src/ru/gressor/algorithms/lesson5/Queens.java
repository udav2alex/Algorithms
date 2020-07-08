package ru.gressor.algorithms.lesson5;

import java.util.Date;

public class Queens {
    public static final int DIM = 8;

    private static class Desk {
        int[][] desk;
        int dim;

        public Desk() {
            desk = new int[DIM][DIM];
            dim = DIM;
        }

        public Desk(int dimension) {
            dim = dimension;
            desk = new int[dim][dim];
        }
    }

    public static void main(String[] args) {

        for (int i = 1; i < 30; i++) {
            Date dateStart = new Date();
            runTest(i);
            Date dateFinish = new Date();
            System.out.println("Продолжительность вычислений: "
                    + (dateFinish.getTime() - dateStart.getTime()) + " милисек.");
            System.out.println();
        }
    }

    public static void runTest(int dimension) {
        Desk d = new Desk(dimension);
        System.out.println(makeSolution(d, 0, 0) ? "Решение найдено :)))" : "Решение не найдено ;(");
        printDesk(d);
    }

    private static boolean safe(Desk d, int x, int y) {
        for (int i = 0; i < d.dim; i++) {
            if (       (i != x && d.desk[i][y] > 0)
                    || (i != x && y - x + i >= 0 && y - x + i < d.dim && d.desk[i][y - x + i] > 0)
                    || (i != x && y + x - i >= 0 && y + x - i < d.dim && d.desk[i][y + x - i] > 0)
                    || (i != y && d.desk[x][i] > 0)) {
//                System.out.println(false);
                return false;
            }
        }
//        System.out.println("safe!");
        return true;
    }

    private static boolean makeSolution(Desk d, int x, int turn) {
        turn++;
        if (x >= d.dim) return true;

        for (int y = 0; y < d.dim; y++) {
            d.desk[x][y] = turn;
//            printDesk(d);
            if (safe(d, x, y) && makeSolution(d, x + 1, turn)) {
                return true;
            } else {
                d.desk[x][y] = 0;
            }
        }
        return false;
    }

    private static void printDesk(Desk d) {
        for (int x = 0; x < d.dim; x++) {
            for (int y = 0; y < d.dim; y++) {
                System.out.printf("%3d", d.desk[x][y]);
            }
            System.out.println();
        }
    }
}
