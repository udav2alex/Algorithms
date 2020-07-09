package ru.gressor.algorithms.lesson5;

public class Hanoi {
    private static int count;

    public static void main(String[] args) {
        testHanoi(12);
    }

    public static void testHanoi(int height) {
        count = 0;
        Towers towers = new Towers(height);
        System.out.println("Высота " + height);
        System.out.println(towers);
        moveIt(towers, 0, 2, towers.height);
        System.out.println();
        System.out.println();
        System.out.println(towers);
        System.out.println("Всего перемещений " + count);
        System.out.println();
    }

    private static class Towers{
        static final int HEIGHT = 8;
        int height;
        int[][] t;
        int[] tops = new int[3];

        public Towers(int height) {
            this.height = height;
            t = new int[3][height];
            tops[0] = height;

            for (int i = 1; i <= height; i++) {
                t[0][height - i] = i;
            }
        }

        public Towers() {
            this(HEIGHT);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int i = height - 1; i >= 0; i--) {
                for (int j = 0; j < 3; j++) {
                    sb.append(t[j][i] == 0 ? "      |" : String.format("%7d", t[j][i]));
                }
                sb.append("\n");
            }

            sb.append("=======================\n");
            for (int j = 0; j < 3; j++) {
                sb.append(String.format("%7d", tops[j]));
            }
            sb.append("\n");

            return sb.toString();
        }
    }

    private static int getSupport(int from, int to) {
        return ((from == 0 && to == 1) || (from == 1 && to == 0)) ? 2
                : ((from == 0 && to == 2) || (from == 2 && to == 0)) ? 1 : 0;
    }

    public static void moveIt(Towers towers, int from, int to, int qtty) {
        if (qtty == 1) {
            move(towers, from, to);
            return;
        }

        int support = getSupport(from, to);
        moveIt(towers, from, support, qtty - 1);
        move(towers, from, to);
        moveIt(towers, support, to, qtty - 1);
    }

    private static void move(Towers towers, int from, int to) {
        count++;
        int toMove = towers.t[from][towers.tops[from] - 1];

        towers.t[from][towers.tops[from] - 1] = 0;
        towers.tops[from]--;
        towers.tops[to]++;
        towers.t[to][towers.tops[to] - 1] = toMove;

        System.out.print("[t" + from + "{" + toMove + "}->t" + to + "] ");
    }
}
