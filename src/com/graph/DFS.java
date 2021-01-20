package com.graph;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-19 20:49
 **/
public class DFS {

    int m;
    int n;
    Point end;
    int[][] data;
    boolean[][] mark;
    int minStep; // 用来记录最少步数
    ArrayList<Point> fastPath; // 用来记录最快路径

    public DFS(int m, int n, Point end, int[][] data) {
        this.m = m;
        this.n = n;
        this.end = end;
        this.data = data;
        this.mark = new boolean[m][n];
        this.minStep = Integer.MAX_VALUE; // 给最少步数初始化一个最大值
    }

    /**
    * @Description: 深度优先查找最短路径
    * @Param: [point 当前坐标, step 已走步数, path 已走路径]
    * @return: void
    * @Author: 穆野
    * @Date: 2021/1/19
    */
    public void find(Point point, int step, ArrayList<Point> path) {
        if (point.x < 0 || point.x >= m || point.y < 0 || point.y >= n) {
            System.out.println("起点错误！");
            return;
        }
        // 记录访问标记
        mark[point.x][point.y] = true;
        // 将当前节点存入访问路径
        path.add(point);
        if (point.x == end.x && point.y == end.y) {
            // 当访问到目标，并且步数小于记录的最小步数时，更新最小步数和访问路径
            if (step < minStep) {
                minStep = step;
                fastPath = (ArrayList<Point>) path.clone();
            }
        }

        int[][] next = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < next.length; i++) {
            int x = point.x + next[i][0];
            int y = point.y + next[i][1];
            if ((x < 0 || x >= m || y < 0 || y >= n)) {
                continue;
            }
            if (data[x][y] == 0 && !mark[x][y]) {
                find(new Point(x, y), ++step, path);
            }
        }
        // 这里进行回溯，需要将访问记录清除，否则其他路径无法再次访问此坐标，并且访问路径记录也会有问题
        mark[point.x][point.y] = false;
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        Point start = new Point(0, 0);
        Point end = new Point(2, 3);
        int[][] data = {{0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 0, 1, 0, 0}, {0, 0, 0, 0, 1}};
        ArrayList<Point> path = new ArrayList<>();

        DFS dfs = new DFS(m, n, end, data);
        dfs.find(start, 0,path);
        System.out.println(dfs.minStep);
        dfs.fastPath.stream().forEach(System.out::println);
    }
}
