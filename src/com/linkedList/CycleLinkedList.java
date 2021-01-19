package com.linkedList;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-09 16:19
 **/
public class CycleLinkedList {
    ListNode head;
    int size;

    public void add(int data) {
        ListNode node = new ListNode(data);
        if (head == null) {
            head = node;
        } else {
            ListNode curr = head;
            for (int i = 1; i < size; i++) {
                curr = curr.next;
            }
            curr.next = node;
        }
        node.next = head;
        this.size++;
    }

    public void delete(int i) {

        if (i >= 0 && i < size) {
            ListNode curr = head;
            if (i == 0) {
                i = this.size;
            }
            for (int j = 1; j < i; j++) {
                curr = curr.next;
            }
            System.out.println("delete : " + curr.next.value);
            curr.next = curr.next.next;
            head = curr.next;
            this.size--;
        }
    }

    public ListNode findLast(int m) {
        if (size <= 1) {
            return head;
        }
        if (m > this.size) {
            delete(m % this.size - 1);
        } else {
            delete(m - 1);
        }

        return findLast(m);
    }


//    public ListNode CycleLinkedList(int[] arr) {
//        if (arr != null || arr.length > 0) {
//            this.size = arr.length;
//            this.head = new ListNode(arr[0]);
//            ListNode curr = head;
//            for (int i = 1; i < arr.length; i++) {
//                curr.next = new ListNode(arr[i]);
//                curr = curr.next;
//            }
//            curr.next = this.head;
//        }
//        return head;
//    }

//    public static void sout(ListNode node) {
//        while (node != null) {
//            System.out.println(node.value);
//            node = node.next;
//        }
//    }

    public static void main(String[] args) {
        CycleLinkedList list = new CycleLinkedList();
        for (int i = 1; i <= 6; i++) {
            list.add(i);
        }

        ListNode node = list.findLast(5);
        System.out.println(node.value);




    }
}


