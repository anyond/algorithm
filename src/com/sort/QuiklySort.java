package com.sort;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-12 22:38
 **/
public class QuiklySort {
    public static void main(String[] args) {
        int[] data = {4, 5, 6, 3, 2, 1};
        qsort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
        
    }

    public static void qsort(int[] data, int left, int right) {
        int base = data[left];
        int ll = left;
        int rr = right;

        while (ll < rr) {
            while (ll < rr && data[rr] >= base) {
                rr--;
            }
            if (ll < rr) {
                swap(data, ll, rr);
                ll++;
            }
            while (ll < rr && data[ll] <= base) {
                ll++;
            }
            if (ll < rr) {
                swap(data, ll, rr);
                rr--;
            }
        }
        if (left < ll - 1) {
            qsort(data, left, ll - 1);
        }
        if (right > ll + 1) {
            qsort(data, ll + 1, right);
        }
    }

    public static void swap(int[] data, int x, int y) {
        data[x] = data[x] + data[y];
        data[y] = data[x] - data[y];
        data[x] = data[x] - data[y];
    }
}
