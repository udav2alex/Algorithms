package ru.gressor.algorithms.lesson3;

public class PriorityQueueTest {
    static PriorityQueue priorityQueue = new PriorityQueue(3);

    public static void main(String[] args) {
        priorityQueue.show();
        insert(1);
        insert(2);
        insert(1);
        remove();
        insert(2);
        remove();
        insert(2);
        insert(3);
        insert(1);
        remove();
        remove();
        remove();
        remove();
        remove();
        remove();
    }

    public static void insert(int i) {
        priorityQueue.insert(i);
        System.out.println("   queue.insert(" + i + ") >>> ");
        priorityQueue.show();
    }

    public static void remove() {
        int i = priorityQueue.remove();
        System.out.println("   queue.remove() = " + i + " >>> ");
        priorityQueue.show();
    }
}
