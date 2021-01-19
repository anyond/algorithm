package com.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-11 16:48
 **/
public class calculator {
    public static double calculate(String[] str) {
        MyStack<Double> nums = new ArrayStack<>(20);
        MyStack<String> symbols = new ArrayStack<>(20);

        for (String x : str) {
            if (isAddorSub(x)) {
                if (symbols.isEmpty()) {
                    symbols.push(x);
                } else {
                    while (!symbols.isEmpty()) {
                        doCalculate(nums, symbols);
                    }
                    symbols.push(x);
                }
            } else if (isMultiorDiv(x)) {
                if (symbols.isEmpty()) {
                    symbols.push(x);
                } else {
                    while (isMultiorDiv(symbols.peek())) {
                        doCalculate(nums, symbols);
                    }
                    symbols.push(x);
                }
            } else {
                nums.push(Double.valueOf(x));
            }
        }
        while (!symbols.isEmpty()) {
            doCalculate(nums, symbols);
        }
        return nums.pop();
    }

    public static void doCalculate(MyStack<Double> nums,MyStack<String> symbols) {
        String symbol = symbols.pop();
        double m = nums.pop();
        double n = nums.pop();
        nums.push((double) simpleCalculate(n, m, symbol));
    }

    public static double simpleCalculate(double x, double y, String sympol) {
        if (sympol.equals("+"))
            return x + y;
        if (sympol.equals("-"))
            return x - y;
        if (sympol.equals("*"))
            return x * y;
        if (sympol.equals("/"))
            return x / y;
        return 0;
    }

    public static boolean isAddorSub(String c) {
        if (c.equals("+")  || c.equals("-")) {
            return true;
        }
        return false;
    }

    public static boolean isMultiorDiv(String c) {
        if (c.equals("*")  || c.equals("/")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] str = {"1", "+", "10", "*", "2", "/", "5", "-", "4"};
        System.out.println(calculate(str));
    }
}
