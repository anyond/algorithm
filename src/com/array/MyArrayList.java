package com.array;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-09 15:10
 **/
public class MyArrayList<T> implements MyList<T>{

    private int size;
    private static final int DEFAULT_SIZE = 10;
    private int index;
    private Object[] data;

    public MyArrayList() {
        this.data = new Object[this.DEFAULT_SIZE];
        this.size = this.DEFAULT_SIZE;
    }

    @Override
    public void add(T t) {
        if (index == size) {
            this.size = this.size * 2 + 1;
            Object[] newData = new Object[this.size];
            for (int i = 0; i <= data.length; i++) {
                newData[i] = newData[i];
            }
            data = newData;
        }
        data[index++] = t;
    }

    @Override
    public void remove(int i) {
        if (i >= 0 && i < index) {
            for (int j = i + 1; j < index; j++) {
                data[j - 1] = data[j];
            }
            this.index--;
        }
    }

    @Override
    public void remove(Object o) {
        for (int i = 0; i < index; i++) {
            if (o.equals(data[i])) {
                remove(i);
                break;
            }
        }
    }

    @Override
    public T get(int i) {
        if (i >= 0 && i < index) {
            return (T) data[i];
        }
        return null;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }
}
