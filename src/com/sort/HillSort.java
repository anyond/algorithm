package com.sort;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-12 17:34
 **/
public class HillSort {
    public static void main(String[] args) {
        int[] data = { 9, 8, 7, 0, 1, 3, 2 };
        sort1(data);
        System.out.println(Arrays.toString(data));
        int[] data2 = { 9, 8, 7, 0, 1, 3, 2 };
        sort2(data2);
        System.out.println(Arrays.toString(data));

        int[] data3 = { 9, 8, 7, 0, 1, 3, 2 };
        sort3(data3);
        System.out.println(Arrays.toString(data));

    }

    public static void sort1(int[] data) {
        int len = data.length;

        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int j = i;
                while (j - gap >= 0 && data[j] < data[j - gap]) {
                    change(data, j, j - gap);
                    j -= gap;
                }
            }
        }
    }

    public static void sort2(int[] data) {
        int len = data.length;

        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int j = i;
                int temp = data[j];
                while (j - gap >= 0 && data[j] < data[j - gap]) {
                    data[j] = data[j - gap];
                    j -= gap;
                }
                data[j] = gap;
            }
        }
    }

    public static void sort3(int[] data) {
        int len = data.length;
        for (int add = len / 2; add > 0; add /= 2) {
            for (int i = 0; i < len; i++) {
                int j = i - add;
                int n = data[i];
                for (; j > 0; j-=add) {
                    if (data[i] < data[j]) {
                        data[j + add] = data[j];
                    } else {
                        break;
                    }
                }
                data[j + add] = n;
            }
        }
    }

    public static void change(int[] data, int a, int b) {
        data[a] = data[a] + data[b];
        data[b] = data[a] - data[b];
        data[a] = data[a] - data[b];
    }

}
