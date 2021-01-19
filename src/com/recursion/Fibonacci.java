package com.recursion;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-12 15:04
 **/
public class Fibonacci {

    private static int[] data;

    //递归实现斐波那契数列 f(n) = f(n-1) + f(n-2),时间:O(2^n)，空间:O(2^n)
    public static int fab(int n) {
        if (n <= 2) {
            return 1;
        }
        return fab(n - 1) + fab(n - 2);
    }

    //用尾递归的方式计算斐波那契数列
    public static int fabTail(int n, int pre, int res) {
        if (n <= 2) {
            return res;
        }
        return fabTail(n - 1, res, res + pre);
    }

    //递归加缓存,时间:O(n)，空间:O(1)
    public static int fab2(int n) {
        if (n <= 2) {
            return 1;
        }
        if (data[n] != 0) {
            return data[n];
        }
        //每次计算到结果先记录到缓存数组，在递归过程中再次计算该值时直接从数组获取
        data[n] = fab2(n - 1) + fab2(n - 2);
        return data[n];
    }


    //不用递归实现斐波那契数列,用循环实现，时间:O(n)，空间:O(n)
    public static int noFab(int n) {
        if (n <= 2) {
            return 1;
        }
        int a = 1;
        int b = 1;
        int c = 0;
        for (int i = 3; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    //求N的阶乘,普通递归写法 5!=5*4*3*2*1=> f(n) = n * f(n)，时间:O(n)，空间:O(n)
    public static int fac(int n) {
        if (n == 1) {
            return 1;
        }
        return n * fac(n - 1);
    }

    //用尾递归计算N的阶乘
    public static int facTail(int n, int res) {
        if (n == 1) {
            return res;
        }
        return facTail(n - 1, n * res);
    }

    public static void main(String[] args) {
        //计算斐波那契
/*        data = new int[46];
        for (int i = 1; i <= 45; i++) {
            LocalDateTime start = LocalDateTime.now();
//            int n = fab(i);
            int n = fab2(i);
//            int n = noFab(i);
            LocalDateTime end = LocalDateTime.now();
            Duration duration = Duration.between(start, end);
            System.out.println(i + ":" + n + "，花费时间" + duration.toMillis() + "ms");
        }*/

        //计算阶乘
        for (int i = 1; i <= 10; i++) {
            LocalDateTime start = LocalDateTime.now();
            int n = fac(i);
            LocalDateTime end = LocalDateTime.now();
            Duration duration = Duration.between(start, end);
            System.out.println(i + ":" + n + "，花费时间" + duration.toMillis() + "ms");
        }
    }
}
