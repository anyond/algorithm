package com.queue;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-11 22:01
 **/
public class ListNodeQueue {
    private ListNode head;
    private ListNode tail;

    public void push(int n) {
        if (isEmpty()) {
            head = new ListNode(n);
            tail = head;
        } else {
            tail.next = new ListNode(n);
            tail = tail.next;
        }
    }

    public int pop() {
        if (head == null) {
            return -1;
        } else {
            int n = head.value;
            head = head.next;
            return n;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public static void main(String[] args) {
        ListNodeQueue queue = new ListNodeQueue();
        for (int i = 0; i < 5; i++) {
            queue.push(i);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.pop());
        }
    }

}

class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}
