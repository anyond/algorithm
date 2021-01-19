package com.stack;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-11 10:38
 **/
public class ArrayStack<T> implements MyStack<T> {

    private T[] a;
    private int n;

    public ArrayStack(int cap) {
        this.a = (T[]) new Object[cap];
    }

    private void judgeSize() {
        if (n >= a.length) {
            resize(2 * a.length);
        } else if (n < a.length / 2) {
            resize(a.length / 2);
        }
    }

    private void resize(int size) {
        T[] temp = (T[]) new Object[size];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public void push(T t) {
        judgeSize();
        a[n++] = t;
    }

    @Override
    public T pop() {
        if (!isEmpty()){
            T t = a[--n];
            a[n] = null;
            return t;
        }
        return null;
    }

    @Override
    public T peek() {
        if (!isEmpty()){
            T t = a[n-1];
            return t;
        }
        return null;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n==0;
    }
}
