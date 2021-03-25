package com.yfl.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(8, 16, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("com.yfl.thread-situation-porter-%d").build());

    public static void main(String[] args) {
        Future<?> submit = poolExecutor.submit(() -> System.out.println(Thread.currentThread().getName()));
        System.out.println(submit.isDone());
        poolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        poolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        poolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        poolExecutor.shutdown();
        System.out.println(submit.isDone());
    }
}
