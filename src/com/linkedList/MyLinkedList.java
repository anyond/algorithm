package com.linkedList;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-09 15:31
 **/
public class MyLinkedList {
    ListNode head;

    public void insertHead(int data) {
        ListNode newNode = new ListNode(data);
        newNode.next = head.next;
        head = newNode;
    }

    public void insertNth(int data,int position){
        if (position == 0) {
            insertHead(data);
        } else {
            ListNode curr = head;
            for (int i = 1; i < position; i++) {
                curr = curr.next;
            }
            ListNode newNode = new ListNode(data);
            newNode.next = curr.next;
            curr.next = newNode;
        }
    }

    public void deleteHead(){
        head = head.next;
    }

    public void deleteNth(int position){
        if (position == 0) {
            deleteHead();
        } else {
            ListNode curr = head;
            for (int i = 1; i < position; i++) {
                curr = curr.next;
            }
            curr.next = curr.next.next;
        }
    }

    public ListNode find(int data) {
        ListNode curr = head;
        while (curr != null) {
            if (curr.value == data) {
                break;
            }
            curr = curr.next;
        }
        return curr;
    }

    public void print() {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}

class ListNode {
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}
