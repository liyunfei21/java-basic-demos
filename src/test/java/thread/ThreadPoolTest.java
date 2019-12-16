package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(8, 16, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("thread-situation-porter-%d").build());

    public static void main(String[] args) {
        poolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
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
    }
}
