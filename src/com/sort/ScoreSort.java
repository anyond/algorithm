package com.sort;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-13 18:03
 **/
public class ScoreSort {
    public static void main(String[] args) {
        String filePath = "D:\\score.txt";
        createFile(filePath, 2000000);
        sort(filePath);
    }

    public static void sort(String filePath) {
        LocalDateTime start = LocalDateTime.now();
        int total = 0;

        int[] arr = new int[90001];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String str = null;
            int score = 0;
            while ((str = reader.readLine()) != null) {
                score = (int) (Double.valueOf(str) * 100);
                arr[score]++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sortPath = "D:\\sortScore.txt";
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sortPath)))) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i]; j++) {
                    Double score = Double.valueOf(i / 100.0);
                    writer.write(score + "\r\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("数据总量" + total);
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("耗时共" + duration.toMillis() + "ms");
    }

    public static void createFile(String filePath,int rows) {
        Random random = new Random();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)))) {
            for (int i = 0; i < rows; i++) {
                double score = Double.valueOf(random.nextInt(90000) / 100.0);
                writer.write(score + "\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
