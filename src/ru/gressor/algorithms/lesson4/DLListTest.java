package ru.gressor.algorithms.lesson4;

public class DLListTest {
    public static void main(String[] args) {
        DLList<Integer> list = new DLList<>();
        view(list);

        list.addFirst(0);
        view(list);

        System.out.println("list.popFirst() = " + list.popFirst());
        view(list);

        list.addLast(1);
        view(list);

        System.out.println("list.popFirst() = " + list.popFirst());
        view(list);

        list.addLast(2);
        view(list);

        list.addFirst(3);
        view(list);

        System.out.println("list.popFirst() = " + list.popFirst());
        System.out.println("list.popFirst() = " + list.popFirst());
        view(list);

        list.addFirst(2);
        view(list);

        list.addLast(3);
        list.addLast(0);
        list.addLast(1);
        list.addLast(4);
        view(list);

        System.out.println("list.contains(2) = " + list.contains(2));
        System.out.println("list.contains(5) = " + list.contains(5));

        System.out.println("list.delete(5) = " + list.delete(5));
        System.out.println("list.delete(2) = " + list.delete(2));

        System.out.println("list.popLast() = " + list.popLast());
        System.out.println("list.popLast() = " + list.popLast());
        view(list);
    }

    private static void view(DLList<?> list) {
        System.out.println(list + ", size = " + list.getSize());
        System.out.println();
    }
}
