package com.array;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-09 15:08
 **/
public interface MyList<T> {
    public void add(T t);

    public void remove(int i);

    public void remove(Object o);

    public T get(int i);

    public int size();

    public boolean isEmpty();
}
