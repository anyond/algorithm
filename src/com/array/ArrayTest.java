package com.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-08 22:48
 **/
public class ArrayTest {

    int size;
    int index;
    int[] data;

    /**
    * @Description: 构造方法
    * @Param: [size]
    * @return: 
    * @Author: 穆野
    * @Date: 2021/1/8
    */
    public ArrayTest(int size) {
        this.size = size;
        data = new int[size];
        index = 0;
    }

    /**
     * @Description: 插入
     * @Param: [args]
     * @return: void
     * @Author: 穆野
     * @Date: 2021/1/8
     */
    public void insert(int loc, int n) {
        if (index++ < size) {
            for (int i = index; i > loc; i--) {
                data[i] = data[i - 1];
            }
            data[loc] = n;
        }
    }
    
    /**
    * @Description: 删除
    * @Param: [args]
    * @return: void
    * @Author: 穆野
    * @Date: 2021/1/8
    */
    public void delete(int loc,int n) {
        for (int i = loc; i < index; i++) {
            if (i != size - 1) {
                data[i] = data[i + 1];
            } else {
                data[i] = 0;
            }
        }
        index--;
    }

    /**
     * @Description: 查找
     * @Param: [args]
     * @return: void
     * @Author: 穆野
     * @Date: 2021/1/8
     */
    public int get(int loc) {
        return data[loc];
    }

    /**
    * @Description: 修改
    * @Param: [args]
    * @return: void
    * @Author: 穆野
    * @Date: 2021/1/8
    */
    public void update(int loc,int n) {
        data[loc] = n;
    }

    /**
     * @Description: 打印
     * @Param: [args]
     * @return: void
     * @Author: 穆野
     * @Date: 2021/1/8
     */
    public void print() {
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) {

        String str1 = "abc";
        String str2 = "abc";
        str1 = "bcd";
        String str3 = str1;



        System.out.println(str3 == str1);

    }


}
