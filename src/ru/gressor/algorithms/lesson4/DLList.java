package ru.gressor.algorithms.lesson4;

import java.util.NoSuchElementException;
import java.util.Objects;

public class DLList<T> {
    private Node head;
    private Node tail;
    private int size;
    private Iterator iterator;

    public DLList() {
        head = null;
        tail = null;
        size = 0;
        iterator = new Iterator();
    }

    public Iterator iterator() {
        return new Iterator();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        iterator.reset();

        StringBuilder sb = new StringBuilder("[");
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append(", ");
        }
        sb.append(iterator.getCurrent());
        sb.append("]");

        return sb.toString();
    }

    public void addFirst(T value) {
        iterator.reset();
        iterator.insertBefore(value);
    }

    public void addLast(T value) {
        iterator.resetToTail();
        iterator.insertAfter(value);
    }

    public T popFirst() {
         iterator.reset();
        return iterator.deleteCurrent();
    }

    public T popLast() {
        iterator.resetToTail();
        return iterator.deleteCurrent();
    }

    public boolean contains(T value) {
        return iterator.find(value);
    }

    public boolean delete(T value) {
        boolean result = iterator.find(value);
        if (result) iterator.deleteCurrent();
        return result;
    }

    private class Node {
        T value;
        Node previous;
        Node next;

        public Node(T value) {
            this.value = value;
            previous = null;
            next = null;
        }

        @Override
        public String toString() {
            return value.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node another = (Node) o;
            return Objects.equals(value, another.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public class Iterator {
        private Node current;

        public Iterator() {
            current = head;
        }

        public void reset() {
            current = head;
        }

        public void resetToTail() {
            current = tail;
        }

        private void checkCurrent() {
            if (current == null) throw new NoSuchElementException("the list is empty");
        }

        public T getCurrent() {
            checkCurrent();
            return current.value;
        }

        public T next() {
            checkCurrent();
            if (current.next == null) throw new NoSuchElementException("the next element absent");

            T result = current.value;
            current = current.next;
            return result;
        }

        public T previous() {
            checkCurrent();
            if (current.previous == null) throw new NoSuchElementException("the previous element absent");

            T result = current.value;
            current = current.previous;
            return result;
        }

        public boolean hasNext() {
            if (current == null) return false;
            return current.next != null;
        }

        public boolean hasPrevious() {
            if (current == null) return false;
            return current.previous != null;
        }

        public boolean atEnd() {
            return current == tail;
        }

        public boolean atStart() {
            return current == head;
        }

        public void insertAfter(T value) {
            Node inserted = new Node(value);

            if (current == null) {
                head = inserted;
                tail = inserted;
                current = inserted;
            } else {
                if (current.next == null) {
                    tail = inserted;
                } else {
                    current.next.previous = inserted;
                    inserted.next = current.next;
                }
                inserted.previous = current;
                current.next = inserted;
            }

            size++;
        }

        public void insertBefore(T value) {
            Node inserted = new Node(value);

            if (current == null) {
                head = inserted;
                tail = inserted;
                current = inserted;
            } else {
                if (current.previous == null) {
                    head = inserted;
                } else {
                    current.previous.next = inserted;
                    inserted.previous = current.previous;
                }
                inserted.next = current;
                current.previous = inserted;
            }

            size++;
        }

        public void setCurrent(T value) {
            checkCurrent();
            current.value = value;
        }

        public T deleteCurrent() {
            checkCurrent();

            if (current.previous == null && current.next == null) {
                head = null;
                tail = null;

            } else if (current.previous == null) {
                head = current.next;
                head.previous = null;

            } else if (current.next == null) {
                tail = current.previous;
                tail.next = null;

            } else {
                current.previous.next = current.next;
                current.next.previous = current.previous;
            }

            size--;
            return current.value;
        }

        public boolean find(T value) {
            if (current == null) return false;

            Node hCurrent = head;
            Node tCurrent = tail;

            for (int i = 0; i < size / 2 + 1; i++) {
                if (hCurrent.value.equals(value)) {
                    current = hCurrent;
                    return true;
                }

                if (tCurrent.value.equals(value)) {
                    current = tCurrent;
                    return true;
                }
                hCurrent = hCurrent.next;
                tCurrent = tCurrent.previous;
            }

            return false;
        }
    }

    //reset();
    //next();
    //getCurrent();
    //hasNext();
    //atEnd();
    //insertAfter();
    //*insertBefore();
    //deleteCurrent();
}