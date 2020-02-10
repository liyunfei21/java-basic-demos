package com.yfl.libs;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumTest {
    /*
     * 对于生成随机数，推荐使用ThreadLocalRandom.current()产生，效率更高
     * 参考Effective Java
     *
     * */
    public static void main(String[] args) {
//        thread();
//        ThreadLocalRandom current = ThreadLocalRandom.current();
//        for (int i = 0; i < 100; i++) {
//            double v = current.nextDouble(0.01, 1.0);
//            System.out.println(v);
//
//            BigDecimal b = new BigDecimal(v);
//            v = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//            if (v == 0) {
//            }
//            System.out.println(v);

//        }
        System.out.println(new Date(System.nanoTime()).toLocaleString());
        System.out.println(new Date(System.currentTimeMillis()).toLocaleString());
    }

    private static void thread() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        for (int i = 0; 10 > i; i++) {
            Object num = current.nextFloat();
            System.out.println(num);
        }
    }

}
