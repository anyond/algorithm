package com.introduction;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-08 22:11
 **/
public class Test {

    /**
     * 判断一个数是否是2的N次方。比如2 4 8 16 是的6 10 不是的就看这个数是不是可以拆成N个2相乘。
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            if (check(i)) {
                System.out.println(i);
            }
        }
    }

    public static Boolean check(int i) {
        return (i & (i - 1)) == 0;
    }

}
