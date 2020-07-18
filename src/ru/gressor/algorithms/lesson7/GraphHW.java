package ru.gressor.algorithms.lesson7;

public class GraphHW {

    public static void main(String[] args) {
        Graph<Integer> graph = Graph.createConnectedIntegerGraph(10);
        System.out.println(graph);

        System.out.println("\nWide search has " + graph.traverseWide(1, 10) + " step(s).\n");
        System.out.println("\nDeep search has " + graph.traverseDeep(1, 10) + " step(s).\n");

        System.out.println("\nFull wide traverse in " + graph.traverseWide() + " step(s).\n");
        System.out.println("\nFull deep traverse in " + graph.traverseDeep() + " step(s).\n");
    }
}
