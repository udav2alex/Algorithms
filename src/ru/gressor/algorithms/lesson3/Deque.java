package ru.gressor.algorithms.lesson3;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Deque {
    private int capacity;
    private int[] storage;
    private int head;
    private int tail;
    private int items;

    public Deque(int capacity) {
        this.capacity = capacity;
        storage = new int[capacity];
        head = 0;
        tail = -1;
        items = 0;
    }

    public boolean isEmpty() {
        return items == 0;
    }

    public boolean isFull() {
        return items == capacity;
    }

    public int size() {
        return items;
    }

    private void expand() {
        if (isFull()) {
            capacity *= 2;
            int[] newS = new int[capacity];
            if (tail >= head) {
                System.arraycopy(storage, 0, newS, 0, storage.length);
            } else {
                System.arraycopy(storage, 0, newS, 0, tail + 1);
                System.arraycopy(storage, head,
                        newS, capacity - (storage.length - head),
                        storage.length - head);
            }
            head = capacity - (storage.length - head);
            storage = newS;
        }
    }

    void show() {
        System.out.printf("head: %d, tail: %d, size: %d\n", head, tail, items);
        System.out.println(Arrays.toString(storage));
    }

    public void addFirst(int value) {
        expand();
        head = (head - 1 + capacity) % capacity;
        storage[head] = value;
        items++;
    }

    public void addLast(int value) {
        expand();
        tail = (tail + 1 + capacity) % capacity;
        storage[tail] = value;
        items++;
    }

    private void checkEmpty() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
    }

    public int removeFirst() {
        checkEmpty();
        int temp = storage[head++];
        head %= capacity;
        items--;
        return temp;
    }

    public int removeLast() {
        checkEmpty();
        int temp = storage[tail--];
        tail = (tail + capacity) % capacity;
        items--;
        return temp;
    }

    public int peekFirst() {
        checkEmpty();
        return storage[head];
    }

    public int peekLast() {
        checkEmpty();
        return storage[tail];
    }
}