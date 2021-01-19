package com.queue;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-11 21:38
 **/
public class ArrayQueue {
    private int[] data;
    private int head;
    private int tail;

    public ArrayQueue(int cap) {
        this.data = new int[cap];
    }

    public void push(int n) {
        if (tail == data.length) {
            //移动队列
            if (!moveQueue()) {
                //扩容
                resize(data.length * 2);
            }
        }
        data[tail++] = n;
    }

    public void resize(int size) {
        int[] temp = new int[size];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    public boolean moveQueue() {
        if (head == 0) {
            return false;
        } else {
            for (int i = head; i < tail; i++) {
                data[i - head] = data[i];
            }
            tail -= head;
            head = 0;
            return true;
        }
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return data[head++];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(10);
        for (int i = 0; i < 20; i++) {
            queue.push(i);
        }

        for (int i = 0; i < 20; i++) {
            System.out.println(queue.pop());
        }
    }
}
