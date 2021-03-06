package com.yfl.thread;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class CountDownTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        Runnable r1 = () -> {
            try {
                System.out.println("线程1 start");
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            System.out.println("线程1 end");
        };

//        Runnable r2 = () -> {
//            try {
//                System.out.println("线程2 start");
//                sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            latch.countDown();
//            System.out.println("线程2 end");
//        };

        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(r1);
//        pool.submit(r2);

        long start = System.currentTimeMillis();
        System.out.println("await...." + start);
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("over....." + end);

        System.out.println((end - start) / 1000);
        System.out.println(pool.isShutdown());
        pool.shutdown();
        System.out.println(pool.isShutdown());
    }
}
