package com.tx;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-13 20:22
 **/
public class Dp {
    public static void main(String[] args) {
        int[] value = {60, 100, 120};
        int[] weight = {10, 20, 40};

        int n = 3;
        int w = 50;
        int[][] dp = new int[n + 1][w + 1];

        for (int i = 1; i <= n; i++) {
            for (int cw = 10; cw <= w; cw += 10) {
                if (cw >= weight[i - 1]) {
                    dp[i][cw] = Math.max(value[i - 1] + dp[i - 1][cw - weight[i - 1]], dp[i - 1][cw]);
                } else {
                    dp[i][cw] = dp[i - 1][cw];
                }
                System.out.print(dp[i][cw] + ",");
            }
            System.out.println();
        }

        System.out.println(dp[n][w]);

    }
}
