package ru.gressor.algorithms.lesson6;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BTree<T extends Comparable<T>> {
    private Node root;
    private boolean containsNull = false;

    private class Node implements Comparable<T> {
        T data;
        Node left;
        Node right;

        public Node(T value) {
            this.data = value;
        }

        @Override
        public String toString() {
            return data.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }

        @Override
        public int compareTo(@NotNull T o) {
            return this.data.compareTo(o);
        }
    }

    public static BTree<Integer> createFilled(int level, boolean stopBeforeExcess) {
        BTree<Integer> tree = new BTree<>();

        int limit = stopBeforeExcess ? level + 1 : level;
        int value = 0;

        while (tree.getDepth() < limit) {
            value = (int)(Math.random() * 200 - 100);
            tree.insert(value);
        }

        if (stopBeforeExcess) {
            tree.delete(value);
        }

        return tree;
    }

    public boolean insert(T value) {
        if (value == null) {
            boolean result = !containsNull;
            containsNull = true;
            return result;
        }

        if (root == null) {
            root = new Node(value);
            return true;
        }

        Node current = root;
        Node parent = root;

        while (current != null) {
            parent = current;
            if (value.compareTo(current.data) < 0) {
                current = current.left;
            } else if (value.compareTo(current.data) > 0) {
                current = current.right;
            } else return false;
        }

        if (value.compareTo(parent.data) < 0) {
            parent.left = new Node(value);
        } else {
            parent.right = new Node(value);
        }

        return true;
    }

    public boolean find(T value) {
        if (value == null) return containsNull;
        if (root == null) return false;

        Node current = root;

        while (current != null) {
            if (value.compareTo(current.data) < 0) {
                current = current.left;
            } else if (value.compareTo(current.data) > 0) {
                current = current.right;
            } else return true;
        }

        return false;
    }

    public boolean delete(T value) {
        if (value == null) {
            boolean result = containsNull;
            containsNull = false;
            return result;
        }
        if (root == null) return false;

        Node current = root;
        Node parent = root;
        boolean isLeft = true;

        while (current != null) {
            if (value.compareTo(current.data) < 0) {
                parent = current;
                isLeft = true;
                current = current.left;
            } else if (value.compareTo(current.data) > 0) {
                parent = current;
                isLeft = false;
                current = current.right;
            } else {
                break;
            }
        }

        if (current == null) return false;

        if (current.left == null && current.right == null) {
            if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.left == null) {
            if (isLeft) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null) {
            if (isLeft) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else {
            Node replacement = selectAndDetachBestDescendant(current);
            replacement.left = current.left;
            replacement.right = current.right;
            if (isLeft) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
        }

        return true;
    }

    private Node selectAndDetachBestDescendant(@NotNull Node root) {
        Node parent = root;
        Node current = root.right;

        if (current.left == null) {
            parent.right = current.right;
            return current;
        }

        while (current.left != null) {
            parent = current;
            current = current.left;
        }
        parent.left = current.right;
        return current;
    }

    public int getDepth() {
        return getSubtreeDepth(root);
    }

    private int getSubtreeDepth(Node root) {
        if (root == null) return 0;

        return 1 + Math.max(
                getSubtreeDepth(root.left),
                getSubtreeDepth(root.right));
    }

    public boolean isBalanced() {
        return isSubtreesBalances(root);
    }

    private boolean isSubtreesBalances(Node root) {
        if (root == null) return true;

        int lL = getSubtreeDepth(root.left);
        int lR = getSubtreeDepth(root.right);

        return isSubtreesBalances(root.left) && isSubtreesBalances(root.right)
                && (lL - lR <= 1) && (lR - lL <= 1);
    }

    private void traverseDirectOrder(StringBuilder sb, Node root) {
        if (root == null) {
            sb.append("none");
            return;
        }
        sb.append(root.data.toString());
        if (root.left == null && root.right == null) return;

        sb.append(" (");
        traverseDirectOrder(sb, root.left);
        sb.append(", ");
        traverseDirectOrder(sb, root.right);
        sb.append(")");
    }

    private void traverseDirectOrderTreeLike(StringBuilder sb, Node root, int level) {
        if (root == null) return;

        for (int i = 0; i < level; i++) {
            sb.append("\t");
        }
        sb.append("- L").append(level).append(": ");
        sb.append(root.data.toString()).append("\n");

        level++;
        traverseDirectOrderTreeLike(sb, root.left, level);
        traverseDirectOrderTreeLike(sb, root.right, level);
    }

    private void traverseSymmetricOrder(StringBuilder sb, Node root) {
        if (root == null) return;
        traverseSymmetricOrder(sb, root.left);
        sb.append(root.data.toString()).append(" ");
        traverseSymmetricOrder(sb, root.right);
    }

    private void traverseReverseOrder(StringBuilder sb, Node root) {
        if (root == null) return;
        traverseReverseOrder(sb, root.right);
        traverseReverseOrder(sb, root.left);
        sb.append(root.data.toString()).append(" ");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (containsNull) sb.append("null");
        if (containsNull && root != null) sb.append(", ");
        traverseDirectOrder(sb, root);
        return sb.toString();
    }

    public String toStringTreeLike() {
        StringBuilder sb = new StringBuilder();
        if (containsNull) sb.append("- L0: null\n");
        traverseDirectOrderTreeLike(sb, root, 0);
        return sb.toString();
    }

    public String toStringSymmetric() {
        StringBuilder sb = new StringBuilder();
        if (containsNull) sb.append("null ");
        traverseSymmetricOrder(sb, root);
        return sb.toString();
    }

    public String toStringReverse() {
        StringBuilder sb = new StringBuilder();
        if (containsNull) sb.append("null ");
        traverseReverseOrder(sb, root);
        return sb.toString();
    }
}
