package ru.gressor.algorithms.lesson3;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Queue {
    private int capacity;
    private int[] queue;
    private int head;
    private int tail;
    private int items;

    public Queue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
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

    void show() {
        System.out.printf("head: %d, tail: %d, size: %d\n", head, tail, items);
        System.out.println(Arrays.toString(queue));
    }

    public void insert(int value) {
        if (isFull()) {
            capacity *= 2;
            int[] newQ = new int[capacity];
            if (tail >= head) {
                System.arraycopy(queue, 0, newQ, 0, queue.length);
            } else {
                System.arraycopy(queue, 0, newQ, 0, tail + 1);
                System.arraycopy(queue, head,
                        newQ, capacity - (queue.length - head),
                        queue.length - head);
            }
            head = capacity - (queue.length - head);
            queue = newQ;
        }
        if (tail == capacity - 1)
            tail = -1;
        queue[++tail] = value;
        items++;
    }

    public int remove() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        int temp = queue[head++];
        head %= capacity; // if (head == capacity) head = 0;
        items--;
        return temp;
    }

    public int peek() {
        return queue[head];
    }
}