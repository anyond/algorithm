package com.sort;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-12 22:19
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] data = {4, 5, 6, 3, 2, 1};
        int len = data.length;

        for (int i = 0; i < len - 1; i++) {
            int minloc = i;
            for (int j = i + 1; j < len; j++) {
                if (data[minloc] > data[j]) {
                    minloc = j;
                }
            }
            if (i != minloc) {
                data[i] = data[i] + data[minloc];
                data[minloc] = data[i] - data[minloc];
                data[i] = data[i] - data[minloc];
            }
            System.out.println(Arrays.toString(data));
        }

        System.out.println(Arrays.toString(data));

    }
}
