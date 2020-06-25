package ru.gressor.algorithms.lesson1;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class HomeWork1 {
    public static void main(String[] args) {
        long[] array = {1L, 2L, 3L, 4L, 5L};
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Minimum: " + min(array));
        System.out.println("Average: " + average(array));
        System.out.println();
        System.out.println("With recursion: 10 ^ 5 = " + powerRecursion(10, 5));
        System.out.println("Without recursion: 10 ^ 5 = " + power(10, 5));
        System.out.println("(more examples in HomeWork1PowerTests)");
    }

    // Сложность O(logN)
    // На каждом (почти) следующем шаге задача упрощается кратно (вдвое) + "правило" №5
    // Но есть дополнительные затраты по памяти на рекурсию
    public static long powerRecursion(long arg, long pow) {
        if (pow == 0) {
            return 1;
        }

        if (pow % 2 == 0) {
            long result = powerRecursion(arg, pow / 2);
            return result * result;
        } else {
            long result = powerRecursion(arg, (pow - 1) / 2);
            return result * result * arg;
        }
    }

    // Сложность O(logN)
    // На каждом (почти) следующем шаге задача упрощается кратно (вдвое) + "правило" №5
    // Алгоритм более сложный, но зато нет потерь памяти на рекурсию как в powerRecursion выше
    public static long power(long arg, long pow) {
        if (pow == 0) {
            return 1;
        }

        long myPow = pow;
        long myArg = arg;
        long result = 1;

        while (myPow > 0) {
            if ((myPow & 1) == 0) { // Оптимизируем определение четности, то же самое, что (myPow % 2 == 0)
                myArg = myArg * myArg;
                myPow >>= 1; // Оптимизируем деление на 2, то же самое, что (myPow /= 2)
            } else {
                result *= myArg;
                myPow -= 1;
            }
        }

        return result;
    }

    // Сложность O(N)
    // Число операций прямо пропорционально размеру массива
    // "Правила" №1 и №5
    public static long min(long @NotNull [] array) {
        if (array.length == 0) {
            throw new RuntimeException("The input array is empty!");
        }

        long result = array[0];

        for (long l : array) {
            if (result > l) {
                result = l;
            }
        }

        return result;
    }

    // Сложность O(N)
    // Число операций прямо пропорционально размеру массива
    // "Правила" №1 и №5
    public static double average(long @NotNull [] array) {
        if (array.length == 0) {
            throw new RuntimeException("The input array is empty!");
        }

        double sum = 0;

        for (long l : array) {
            sum += l;
        }

        return sum / array.length;
    }
}