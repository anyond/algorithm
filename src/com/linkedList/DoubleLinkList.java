package com.linkedList;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-09 15:51
 **/
public class DoubleLinkList {
    DNode head;
    DNode tail;

    public void insertHead(int data) {
        DNode node = new DNode(data);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.pre = node;
            head = node;
        }
    }

    public void deleteHead(){
        if (head != null) {
            if (head.next != null) {
                head.next.pre = null;
            }
            head = head.next;
        }
    }

    public void deleteKey(int data){
        DNode curr = head;
        while (curr.value != data) {
            if (curr.next == null) {
                return;
            }
            curr = curr.next;
        }
        if (curr == head) {
            deleteHead();
        } else {
            curr.pre.next = curr.next;
            if (curr == tail) {
                tail = curr.pre;
                curr.pre = null;
            }
            curr.next.pre = curr.pre;
        }

    }
}

class DNode{
    int value;		//值
    DNode next;		//下一个的指针
    DNode pre;		//指向的是前一个指针

    DNode(int value){
        this.value = value;
        this.next = null;
        this.pre = null;
    }
}