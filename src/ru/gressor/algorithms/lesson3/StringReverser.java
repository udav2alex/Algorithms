package ru.gressor.algorithms.lesson3;

import org.jetbrains.annotations.NotNull;

public class StringReverser {

    public static void main(String[] args) {
        System.out.println(revertString("123456789"));
        System.out.println(revertString("12"));
        System.out.println(revertString("1"));
        System.out.println(revertString(""));
    }

    public static String revertString(@NotNull String string) {
        if (string.length() <= 1) return string;

        Stack stack = new Stack(string.length());

        for (int i = 0; i < string.length(); i++) {
            stack.push(string.charAt(i));
        }

        StringBuilder sb = new StringBuilder(string.length());
        for (int i = 0; i < string.length(); i++) {
            sb.append((char)stack.pop());
        }

        return sb.toString();
    }
}
