package ru.gressor.algorithms.lesson6;

public class BTreeHW {
    public static final int TARGET_LEVEL = 6;

    public static void main(String[] args) {
        /*
    1. Создать и запустить программу для построения двоичного дерева.
       В цикле построить двадцать деревьев с глубиной в 6 уровней.
       Данные, которыми необходимо заполнить узлы деревьев, представляются в виде чисел типа int.
       Число, которое попадает в узел, должно генерироваться случайным образом в диапазоне от -100 до 100.

    2. Проанализировать, какой процент созданных деревьев являются несбалансированными.
       */

        int counterBalanced = 0;
        int counterNonBalanced = 0;

        for (int i = 0; i < 20; i++) {
            BTree<Integer> tree = BTree.createFilled(TARGET_LEVEL, true);
//            System.out.println(tree.toStringTreeLike());
            boolean isBalanced = tree.isBalanced();
            System.out.println("tree.isBalanced() = " + isBalanced);
            System.out.println();

            if (isBalanced) {
                counterBalanced++;
            } else {
                counterNonBalanced++;
            }
        }

        System.out.println();
        System.out.printf("Всего сбалансированных деревьев %2.2f%%\n",
                (float)counterBalanced * 100/(counterBalanced + counterNonBalanced));
        System.out.printf("Всего несбалансированных деревьев %2.2f%%\n",
                (float)counterNonBalanced * 100/(counterBalanced + counterNonBalanced));
    }
}
