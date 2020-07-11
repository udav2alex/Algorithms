package ru.gressor.algorithms.lesson5;

import java.util.Date;

public class QueensFlat {
    public static final int DIM = 8;

    private static class Desk {
        int[] desk;
        boolean[] occupied;
        int dim;

        public Desk() {
            this(DIM);
        }

        public Desk(int dimension) {
            dim = dimension;
            desk = new int[dim];
            occupied = new boolean[dim];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < dim; i++) {
                int queen = desk[i];
                for (int j = 0; j < dim; j++) {
                    if (j == queen) {
                        sb.append(String.format("%3d", i+1));
                    } else {
                        sb.append("  -");
                    }
                }
                sb.append("\n");
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 35; i++) {
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
        System.out.println(makeSolution(d, 0) ? "Решение найдено :)))" : "Решение не найдено ;(");
        System.out.println(d);
    }

    private static boolean safe(Desk d, int x, int y) {
        for (int i = 0; i < x; i++) {
            if ((d.desk[i] == y - x + i) || (d.desk[i] == y + x - i)) {
//                    (i != x && d.desk[i][y] > 0)
//                    || (i != x && y - x + i >= 0 && y - x + i < d.dim && d.desk[i][y - x + i] > 0)
//                    || (i != x && y + x - i >= 0 && y + x - i < d.dim && d.desk[i][y + x - i] > 0)
//                    || (i != y && d.desk[x][i] > 0)
//            ) {
//                System.out.println(false);
                return false;
            }
        }
//        System.out.println("safe!");
        return true;
    }

    private static boolean makeSolution(Desk d, int x) {
        if (x >= d.dim) return true;

        for (int y = 0; y < d.dim; y++) {
            if (d.occupied[y]) continue;

            d.desk[x] = y;
            d.occupied[y] = true;

            if (safe(d, x, y) && makeSolution(d, x + 1)) {
                return true;
            } else {
                d.occupied[y] = false;
            }
        }
        return false;
    }
}
