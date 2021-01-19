package com.sort;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-12 16:24
 **/
public class MergeSort {

    public static void main(String[] args) {
        int data[] = { 9, 5, 6, 8, 0, 3, 7, 1 };
        mergeSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));

    }

    //将所有数据拆分成单独数据，然后将排队归并回来
    public static void mergeSort(int[] data, int left, int right) {// left和right是数组的两端
        if (left < right) {// 相等了就表示只有一个数了 不用再拆了
            int mid = (left + right) / 2;
            mergeSort(data, left, mid);
            mergeSort(data, mid + 1, right);
            // 分完了 接下来就要进行合并，也就是我们递归里面归的过程
            merge(data, left, mid, right);
        }
    }

    //归并数据
    public static void merge(int[] data, int left, int mid, int right) {
        int[] temp = new int[data.length];//借助一个临时数组用来保存合并的数据

        int point1 = left;//表示的是左边的第一个数的位置
        int point2 = mid + 1;//表示的是右边的第一个数的位置
        int loc = left;//表示的是我们当前已经到了哪个位置了
        while (point1 <= mid && point2 <= right) {
            if (data[point1] <= data[point2]) {
                temp[loc++] = data[point1++];
            } else {
                temp[loc++] = data[point2++];
            }
        }

        //上面的循环完成之后，前端或者后端数组可能有余留的数据，下面两个循环只会执行一个
        while (point1 <= mid) {
            temp[loc++] = data[point1++];
        }
        while (point2 <= right) {
            temp[loc++] = data[point2++];
        }
        for (int i = left; i <= right; i++) {
            data[i] = temp[i];
        }
    }
}