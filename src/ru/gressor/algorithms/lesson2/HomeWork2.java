package ru.gressor.algorithms.lesson2;

public class HomeWork2 {
    public static void main(String[] args) {
        Array a0 = new Array(1, -2, -3, -4, 5);
        a0.sortCounting();
        System.out.println(a0);

        Array a1 = new Array();
        a1.insert(0, 10);
        a1.append(100);
        System.out.println(a1);
        System.out.println(a1.deleteValue(50));
        System.out.println(a1.deleteValue(100));
        System.out.println(a1);
        System.out.println(a1.deleteByIndex(0));
        System.out.println(a1);
        a1.deleteAll();
        System.out.println(a1);
    }
}
