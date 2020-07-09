package ru.gressor.algorithms.lesson5;

import java.util.Date;

public class QueensBoosted {
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

        public Desk(Desk d) {
            this.dim = d.dim;
            desk = new int[dim][dim];
            for (int i = 0; i < dim; i++) {
                System.arraycopy(d.desk[i], 0, this.desk[i], 0, dim);
            }
        }
    }

    public static void main(String[] args) {
//        Desk desk1 = new Desk(3);
//        Desk desk2 = new Desk(desk1);
//        printDesk(desk2);

//        runTest(3);

        for (int i = 1; i < 9; i++) {
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

    private static void markWrong(Desk d, int x, int y) {
        for (int i = 0; i < d.dim; i++) {
            d.desk[i][y] = -1;
            d.desk[x][i] = -1;
            if (y - x + i >= 0 && y - x + i < d.dim)
                d.desk[i][y - x + i] = -1;
            if (y + x - i >= 0 && y + x - i < d.dim)
                d.desk[i][y + x - i] = -1;
        }
    }

    private static boolean makeSolution(Desk d, int x, int turn) {
        turn++;
        if (x >= d.dim) return true;

        for (int y = 0; y < d.dim; y++) {
            if (d.desk[x][y] == 0) {
                Desk backup = new Desk(d);
                markWrong(backup, x, y);
                backup.desk[x][y] = turn;

                if (makeSolution(backup, x + 1, turn)) {
                    d.desk[x][y] = turn;
                    return true;
                }
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
