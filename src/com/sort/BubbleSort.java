package com.sort;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-12 22:32
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int[] data = { 9, 8, 7, 0, 1, 3, 2 };
        int len = data.length;

        for (int i = 0; i < len - 1; i++) { //排序的次数
            boolean flag = false;
            for (int j = 0; j < len - 1 - i; j++) { //具体冒泡 n - 1 - i,6,5,4,3,2,1
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(data));
    }
}
