package com.stack;

import java.util.Scanner;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-11 15:11
 **/
public class BracketStack {

    public static boolean isOk(String s) {
        MyStack<Character> brackets = new ArrayStack<>(20);
        char[] c = s.toCharArray();

        for (char x : c) {
            switch (x) {
                case '{':
                case '[':
                case '(':
                    brackets.push(x);
                    break;
                case '}':
                    if (brackets.isEmpty()) {
                        return false;
                    }
                    if (brackets.pop().equals('{')) {
                        break;
                    } else {
                        return false;
                    }
                case ']':
                    if (brackets.isEmpty()) {
                        return false;
                    }
                    if (brackets.pop().equals('[')) {
                        break;
                    } else {
                        return false;
                    }
                case ')':
                    if (brackets.isEmpty()) {
                        return false;
                    }
                    if (brackets.pop().equals('(')) {
                        break;
                    } else {
                        return false;
                    }
                default:
                    break;
            }
        }
        return brackets.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        System.out.println("s的匹配结果:"+isOk(s));
    }
}
