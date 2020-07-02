package ru.gressor.algorithms.lesson3;

public class QueueTest {
    static Queue queue = new Queue(3);

    public static void main(String[] args) {
        queue.show();
        insert(1);
        insert(2);
        insert(3);
        remove();
        insert(4);
        remove();
        insert(5);
        insert(6);
        insert(7);
        remove();
        remove();
    }

    public static void insert(int i) {
        queue.insert(i);
        System.out.println("   queue.insert(" + i + ") >>> ");
        queue.show();
    }

    public static void remove() {
        int i = queue.remove();
        System.out.println("   queue.remove() = " + i + " >>> ");
        queue.show();
    }
}
