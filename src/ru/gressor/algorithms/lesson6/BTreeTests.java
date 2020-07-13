package ru.gressor.algorithms.lesson6;

public class BTreeTests {

    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>();

        bTree.insert(4);
        bTree.insert(8);
        bTree.insert(2);
        bTree.insert(3);
        bTree.insert(1);
        bTree.insert(6);
        bTree.insert(7);
        bTree.insert(5);
        bTree.insert(10);
        bTree.insert(9);
        bTree.insert(11);

        System.out.println(bTree.toStringTreeLike());
        System.out.println(bTree);
        System.out.println(bTree.toStringSymmetric());
        System.out.println(bTree.toStringReverse());

        System.out.println("find(10) = " + bTree.find(10));
        System.out.println("find(12) = " + bTree.find(12));

        System.out.println("delete(10) = " + bTree.delete(10));
        System.out.println("delete(12) = " + bTree.delete(12));
        System.out.println(bTree.toStringTreeLike());
        System.out.println(bTree);
    }
}
