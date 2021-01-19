package com.stack;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-11 10:36
 **/
public interface MyStack<T> {
    void push(T t);

    T pop();

    T peek();

    int size();

    boolean isEmpty();
}
