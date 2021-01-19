package com.tx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-13 18:59
 **/
public class MettingTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Metting> mettings = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            Metting m = new Metting(i + 1, start, end);
            mettings.add(m);
        }
        mettings.sort(null);
        int currTime = 0;
        for (Metting m : mettings) {
            if (m.startTime >= currTime) {
                System.out.println(m);
                currTime = m.endTime;
            }
        }
    }

}

class Metting implements Comparable<Metting> {

    int meNum;
    int startTime;
    int endTime;

    public Metting(int meNum, int startTime, int endTime) {
        this.meNum = meNum;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Metting{" +
                "meNum=" + meNum +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public int compareTo(Metting o) {
        if (this.endTime > o.endTime) {
            return 1;
        }
        return -1;
    }

}