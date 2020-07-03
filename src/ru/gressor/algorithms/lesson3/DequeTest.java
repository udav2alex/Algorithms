package ru.gressor.algorithms.lesson3;

public class DequeTest {
    static Deque deque = new Deque(3);

    public static void main(String[] args) {
        deque.show();
        addLast(1);
        addFirst(13);
        removeFirst();
        removeLast();
        addLast(2);
        addLast(4);
        addFirst(15);
        addFirst(14);
        addFirst(16);
    }

    public static void addFirst(int i) {
        deque.addFirst(i);
        System.out.println("   deque.addFirst(" + i + ") >>> ");
        deque.show();
    }

    public static void removeFirst() {
        int i = deque.removeFirst();
        System.out.println("   deque.removeFirst() = " + i + " >>> ");
        deque.show();
    }

    public static void addLast(int i) {
        deque.addLast(i);
        System.out.println("   deque.addLast(" + i + ") >>> ");
        deque.show();
    }

    public static void removeLast() {
        int i = deque.removeLast();
        System.out.println("   deque.removeLast() = " + i + " >>> ");
        deque.show();
    }
}
