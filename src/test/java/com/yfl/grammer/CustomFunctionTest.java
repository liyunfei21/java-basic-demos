package com.yfl.grammer;

import java.util.Arrays;
import java.util.List;

public class CustomFunctionTest {

    @FunctionalInterface
    private interface  MultiFunction<R,T1>{

        List<R> handle(T1 ...t1s);
    }

    public static void main(String[] args) {

        MultiFunction<String, String> function = strings -> get(strings[0], strings[1],strings[2]);
        List<String> list = function.handle("a", "b", "c");
        System.out.println(list);
    }

    private static List<String> get(String a, String b, String c){
        return Arrays.asList(a,b,c);
    }
}
