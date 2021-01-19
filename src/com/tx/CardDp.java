package com.tx;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-13 21:47
 **/
public class CardDp {
    public static void main(String[] args) {
        int[] value = {1, 2, 3, 4, 5, 9};
        int n = 6;
        int w = 18;
        int[][] dp = new int[n + 1][w + 1];

        for (int i = 1; i <= 6; i++) {
            for (int cw = 1; cw <= w; cw++) {
                if (cw >= value[i - 1]) {
                    dp[i][cw] = Math.max(value[i - 1] + dp[i - 1][cw - value[i - 1]], dp[i - 1][cw]);
                } else {
                    dp[i][cw] = dp[i - 1][cw];
                }
            }
        }
        System.out.println(dp[n][w]);
        find(dp, n, w, value);

    }

    //递归反向寻找数据
    public static void find(int[][] dp, int i, int cw, int[] value) {
        if (i > 0 && cw > 0) {
            if (dp[i][cw] != dp[i - 1][cw]) {
                System.out.println(value[i - 1]);
                cw -= value[i - 1];
            }
            i--;
            find(dp, i, cw, value);
        }
    }
}
