package ru.gressor.algorithms.lesson7;

import java.security.SecureRandom;
import java.util.*;

public class Graph<T> {
    private static final int MAX_SIZE = 10;
    private final ArrayList<Vertex> vertices;
    private final int[][] links;


    private class Vertex {
        T value;
        boolean isVisited;

        public Vertex(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    public Graph(int size) {
        this.vertices = new ArrayList<>(size);
        links = new int[size][size];
    }

    public Graph() {
        this(MAX_SIZE);
    }

    public static Graph<Integer> createConnectedIntegerGraph(int verticesQtty) {
        Graph<Integer> graph = new Graph<>(verticesQtty);

        for (int i = 1; i <= verticesQtty; i++) {
            graph.addVertex(i);
        }

        SecureRandom random = new SecureRandom();
        for (int i = 0; i <= verticesQtty; i++) {
            graph.addLink(random.nextInt(verticesQtty), random.nextInt(verticesQtty));
        }

        while (!graph.isGraphConnected()) {
            graph.addLink(random.nextInt(verticesQtty), random.nextInt(verticesQtty));
        }

        return graph;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Vertices: ");
        for (int i = 0; i < vertices.size(); i++) {
            if (i != 0) sb.append(", ");
            sb.append(vertices.get(i).toString());
        }

        sb.append("\n");
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(vertices.get(i).toString()).append("\t: ")
                    .append(Arrays.toString(links[i])).append("\n");
        }

        return sb.toString();
    }

    public boolean isGraphConnected() {
        traverseDeep(null, null, false);
        for (Vertex vertex : vertices) {
            if (!vertex.isVisited) return false;
        }
        return true;
    }

    public boolean addVertex(T value) {
        if (value == null) return false;

        if (findVertex(value) == -1) {
            vertices.add(new Vertex(value));
            return true;
        } else {
            return false;
        }
    }

    public boolean addLink(T first, T second) {
        int i1 = findVertex(first);
        int i2 = findVertex(second);

        return addLink(i1, i2);
    }

    private boolean addLink(int first, int second) {
        if (first < 0 || second < 0 || first == second) return false;

        links[first][second] = 1;
        links[second][first] = 1;
        return true;
    }

    private int findVertex(T value) {
        if (value == null) return -1;

        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).value.equals(value)) return i;
        }

        return -1;
    }

    public boolean contain(T value) {
        return findVertex(value) != -1;
    }

    public int traverseWide() {
        return traverseWide(null, null);
    }

    public int traverseWide(T first, T second) {
        int start = 0;
        int finish = -1;
        int steps = 0;

        if (first != null) {
            if (first.equals(second)) return 0;

            start = findVertex(first);
            if (start == -1) return -1;
        }

        if (second != null) {
            finish = findVertex(second);
            if (finish == -1) return -1;
        }

        reset();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        vertices.get(start).isVisited = true;
        showVertex(vertices.get(start));
        steps++;

        while (queue.size() > 0) {
            int current = queue.poll();
            System.out.print(" -> (" + vertices.get(current) + ")↓");

            int toOffer;
            while ((toOffer = getNextUnvisitedNeighbour(current)) >= 0) {
                queue.offer(toOffer);
                vertices.get(toOffer).isVisited = true;
                showVertex(vertices.get(toOffer));
                steps++;

                if(toOffer == finish) {
//                    System.out.println("Target reached in " + steps + " steps!");
                    return steps;
                }
            }
        }

        if (finish >= 0) System.out.println("Target unreachable!!!");
        return steps;
    }

    public int traverseDeep() {
        return traverseDeep(null, null, true);
    }

    public int traverseDeep(T first, T target) {
        return traverseDeep(first, target, true);
    }

    private int traverseDeep(T first, T target, boolean showPath) {
        int start = 0;
        int finish = -1;
        int steps = 0;

        if (first != null) {
            if (first.equals(target)) return 0;

            start = findVertex(first);
            if (start == -1) return -1;
        }

        if (target != null) {
            finish = findVertex(target);
            if (finish == -1) return -1;
        }

        reset();
        Deque<Integer> stack = new LinkedList<>();

        stack.push(start);
        vertices.get(start).isVisited = true;
        showVertex(vertices.get(start), showPath);
        steps++;

        while (stack.size() > 0) {
            int nextStep = getNextUnvisitedNeighbour(stack.peek());
            if (nextStep >= 0) {
                stack.push(nextStep);
                vertices.get(nextStep).isVisited = true;
                showVertex(vertices.get(nextStep), showPath);
                steps++;

                if (nextStep == finish) {
//                    System.out.println("Target reached in " + steps + " steps!");
                    return steps;
                }
            } else {
                int current = stack.pop();
                if (showPath) System.out.print(" -> (" + vertices.get(current) + ")↓");
            }
        }

        if (finish >= 0) System.out.println("Target unreachable!!!");
        return steps;
    }

    private void reset() {
        for (Vertex vertex : vertices) {
            vertex.isVisited = false;
        }
    }

    private void showVertex(Vertex vertex, boolean showPath) {
        if (showPath) System.out.print(" -> " + vertex);
    }

    private void showVertex(Vertex vertex) {
        System.out.print(" -> " + vertex);
    }

    private int getNextUnvisitedNeighbour(int index) {
        for (int i = 0; i < vertices.size(); i++) {
            if (links[index][i] > 0 && !vertices.get(i).isVisited) return i;
        }
        return -1;
    }
}
