package com.yfl.grammer;

public class StringTest {
    public static void main(String[] args) {
        String x = "hello";
        String y = "hello";
        String z = new String("hello");
        System.out.println(x == "hello"); // true
        System.out.println(x == y); // true
        System.out.println(x == z); // false
        System.out.println(x.equals(z)); // true
    }
}
