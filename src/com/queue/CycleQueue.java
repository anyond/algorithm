package com.queue;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-11 21:38
 **/
public class CycleQueue {
    private int[] data;
    private int head;
    private int tail;

    public CycleQueue(int cap) {
        this.data = new int[cap];
    }

    public void push(int n) {
        //如果数组长度是8，tail=7,head=0时tail+1=head不成立，故用(tail + 1) % data.length == head判断
        if ((tail + 1) % data.length == head) {
            //扩容
            resize(data.length * 2);
        }
        data[tail] = n;
        tail = (tail + 1) % data.length;
    }

    public void resize(int size) {
        int[] temp = new int[size];
        int len = data.length;
        for (int i = head; i < len; i++) {
            temp[i%len] = data[i%len];
        }
        data = temp;
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        int m = data[head];
        head = (head + 1) % data.length;
        return m;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public static void main(String[] args) {
        CycleQueue queue = new CycleQueue(10);
        for (int i = 0; i < 20; i++) {
            queue.push(i);
        }

        for (int i = 0; i < 20; i++) {
            System.out.println(queue.pop());
        }
    }
}
