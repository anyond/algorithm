package com.heap;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-18 18:34
 **/
public class MyHeap {
    // 用数组实现堆
    int[] data;
    // 堆内总数据量
    int size;

    public MyHeap(int[] data) {
        this.data = data;
        this.size = data.length;
        // 构造函数内调用创建大顶堆方法
        createBigHeap();
    }

    // 创建大顶堆
    public void createBigHeap() {
        int len = data.length;
        // 经过计算len / 2 - 1 为第一个非叶子节点，从最后一个非叶子几点开始做堆化，一直到顶点结束
        for (int i = len / 2 - 1; i >= 0; i--) {
            maxHeap(i, len);
        }
    }

    // 单次堆化
    public void maxHeap(int start, int end) {
        int parent = start;//当前节点
        int son = 2 * parent + 1;//当前节点的左孩子节点
        //循环
        while (son < end) {
            int temp = son;
            if (son + 1 < end) {// 如果有右节点
                // 取左右节点的中的较大值
                temp = data[son] > data[son + 1] ? son : son + 1;
            }
            // 比较当前节点和其子节点大小，如果子节点较大则交换
            if (data[parent] > data[temp]) {
                return;
            } else {
                data[parent] = data[parent] + data[temp];
                data[temp] = data[parent] - data[temp];
                data[parent] = data[parent] - data[temp];
                parent = temp;
                son = temp * 2 + 1;
            }
        }
    }

    // 插入数据
    public void insert(int num) {
        // 获取新增节点的下标
        int index = size++;
        data[index] = num;

        // 获取新增节点父节点开始需要重新做堆化
        int parent = getParent(index);
        int son = index;

        // 循环直至顶点堆化完成
        while (parent >= 0) {
            if (data[parent] > data[son]) {
                break;
            }
            maxHeap(parent, size);
            son = parent;
            parent = getParent(parent);
        }
    }

    // 获取父节点坐标
    public int getParent(int index) {
        if (index % 2 == 1) {
            return (index - 1) / 2;
        } else { // 当index是偶数，为右节点
            return (index - 2) / 2;
        }
    }

    // 删除数据
    public void remove(int num) {
        // 循环数组，获取需要删除值的下标
        int i = 0;
        for (; i < size; i++) {
            if (num == data[i]) {
                break;
            }
        }

        // 如果需要删除的值是最后一个值，则直接删除
        if (i == size - 1) {
            data[i] = 0;
            return;
        }

        // 将需要删除的值和最后一个值交换
        data[size-1] = data[size-1] + data[i];
        data[i] = data[size-1] - data[i];
        data[size-1] = data[size-1] - data[i];

        //删除最后一个节点
        data[size - 1] = 0;
        size--;

        // 从i再做一次堆化
        maxHeap(i,size);
    }

    // 删除堆顶数据
    public void removeTop() {
        remove(data[0]);
    }

    // 打印堆
    public void show() {
        int len = data.length;
        int depth = (int) Math.ceil(Math.log(len) / Math.log(2));

        int arrayHeight = depth * 2 - 1;
        int arrayWidth = (2 << (depth - 2)) * 3 + 1;

        String[][] res = new String[arrayHeight][arrayWidth];

        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        writeArray(0,0,arrayWidth / 2, depth, res);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arrayHeight; i++) {
            sb.setLength(0);
            for (int j = 0; j < arrayWidth; j++) {
                sb.append(res[i][j]);
                if (res[i][j].length() > 1) {
                    j += res[i][j].length() > 4 ? 2 : res[i][j].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    public void writeArray(int dataIndex, int rowIndex, int columnIndex, int depth, String[][] res) {
        if (size == 0) {
            return;
        }
        res[rowIndex][columnIndex] = String.valueOf(data[dataIndex]);
        int currLevel = rowIndex / 2 + 1;
        int gap = depth - currLevel;

        int leftIndex = dataIndex * 2 + 1;
        int rightIndex = dataIndex * 2 + 2;

        if (leftIndex < size) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(leftIndex, rowIndex + 2, columnIndex - gap * 2, depth, res);
        }

        if (rightIndex < size) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(rightIndex, rowIndex + 2, columnIndex + gap * 2, depth, res);
        }
    }

    // 堆排序
    public void heapSort() {
        // 将堆顶和最后一个值交换，然后重新堆化到倒数第二个点，这样，最大值就在最有一位，倒数第二大的值就在堆顶，
        // 重复此操作就得到了一个有序的堆，直接打印数组即是排序后的结果。
        for (int i = size - 1; i > 0; i--) {
            // 交互元素
            data[0] = data[0] + data[i];
            data[i] = data[0] - data[i];
            data[0] = data[0] - data[i];

            maxHeap(0, i);
        }
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) {

        /*int[] data = {6, 4, 8, 10, 7, 3};
        MyHeap myHeap = new MyHeap(data);
        myHeap.show();

        System.out.println("测试堆排序");
        myHeap.heapSort();

        myHeap.createBigHeap();

        System.out.println("测试删除普通节点");
        myHeap.remove(7);
        myHeap.show();
        System.out.println("测试插入节点");
        myHeap.insert(5);
        myHeap.show();

        System.out.println("测试删除顶点");
        myHeap.removeTop();
        myHeap.show();*/

        System.out.println("测试删TOP10");
/*        int[] data = new int[10];
        MyHeap myHeap = new MyHeap(data);
        myHeap.insert();*/


    }
}
