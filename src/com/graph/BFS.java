package com.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-19 20:49
 **/
public class BFS {

    int m; // 邻接矩阵宽度
    int n; // 邻接矩阵高度
    Point end; // 需要找到的点
    int[][] data; // 邻接矩阵
    boolean[][] mark; // 用来标记是否访问过

    public BFS(int m, int n, Point end, int[][] data) {
        this.m = m;
        this.n = n;
        this.end = end;
        this.data = data;
        this.mark = new boolean[m][n];
    }

    public void find(Point start) {
        // 超出地图坐标，直接返回
        if (start.x < 0 || start.x >= m || start.y < 0 || start.y >= n) {
            System.out.println("起点错误！");
            return;
        }
        // 标记是否访问过，如果访问过不再重复访问
        mark[start.x][start.y] = true;

        // 声明一个阻塞队列，用来存放接下来需要访问的坐标
        Queue<Point> queue = new ArrayBlockingQueue<>(m * n);
        // 首先将起点放入队列
        queue.add(start);

        // 为了方便操作，这里定义一个二维数组，当前坐标与数组中的坐标相加即是上下左右
        int[][] next = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // 只要队列中有数据，就说明还没遍历完
        while (!queue.isEmpty()) {
            // 从队列中取出第一个节点，然后判断是否是需要找的坐标
            Point point = queue.poll();
            mark[point.x][point.y] = true;
            if (point.x == end.x && point.y == end.y) {
                System.out.println("找到对象！");
                return;
            }
            // 分别将上下左右四个坐标放入队列
            for (int i = 0; i < next.length; i++) {
                int x = point.x + next[i][0];
                int y = point.y + next[i][1];
                // 超出范围直接返回
                if ((x < 0 || x >= m || y < 0 || y >= n)) {
                    continue;
                }
                // 如果访问过或者有障碍物则不访问
                if (data[x][y] == 0 && !mark[x][y]) {
                    queue.add(new Point(x, y));
                }
            }
        }
        System.out.println("没有找到对象！");
    }

    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        Point start = new Point(0, 0);
        Point end = new Point(2, 3);
        int[][] data = {{0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 0, 1, 0, 0}, {0, 0, 0, 0, 1}};

        BFS bfs = new BFS(m, n, end, data);
        bfs.find(start);
    }
}

// 用来表示坐标
class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}