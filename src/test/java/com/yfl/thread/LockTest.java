package com.yfl.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class LockTest {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Runnable r1 = () -> {
            try {
                lock.lock();
                System.out.println("线程1 start");
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            System.out.println("线程1 end");
        };

        Runnable r2 = () -> {
            try {
                System.out.println("线程2 start");
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2 end");
        };

        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.execute(r1);
        pool.execute(r1);

    }
}
