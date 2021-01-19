package com.array;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.Random;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-08 21:21
 **/
public class AgeStas {
    public static void main(String[] args) {
//        createFile("D:\\age.txt");
        readFile("D:\\age.txt");

    }

    /**
     * 利用数组统计数据
     * @param fileName
     */
    public static void readFile(String fileName) {
        LocalDateTime start = LocalDateTime.now();

        int total = 0;
        int[] data = new int[100];

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String str = null;
            while ((str = reader.readLine()) != null) {
                int age = Integer.valueOf(str);
                data[age]++;
                total++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("数据总量" + total);
        Arrays.stream(data).forEach(System.out::println);

        LocalDateTime end = LocalDateTime.now();

        Duration duration = Duration.between(start, end);
        System.out.println("耗时共" + duration.toMillis() + "ms");
    }

    /**
     * 生成测试文件
     * @param filePath
     */
    public static void createFile(String filePath) {
        Random random = new Random();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)))) {
            for (int i = 0; i < 10000000; i++) {
                int age = random.nextInt(100);
                bufferedWriter.write(age + "\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
