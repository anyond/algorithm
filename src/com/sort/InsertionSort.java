package com.sort;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-12 16:07
 **/
public class InsertionSort {
    public static void main(String[] args) {
        int[] data = { 9, 8, 7, 0, 1, 3, 2 };
        int len = data.length;

        for (int i = 1; i < len; i++) {
            int n = data[i];
            int j = i - 1;
            for (;j >= 0; j--) {
                //对于小于n的数据，需要后移
                if (n < data[j]) {
                    data[j + 1] = data[j];
                } else {
                    //因为前面的数据都是有序的，查询到比n的小的数据后，就无需向前判断了
                    break;
                }
            }
            data[j + 1] = n;
        }

        System.out.println(Arrays.toString(data));
    }
}
