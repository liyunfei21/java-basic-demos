package thread;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadDemoTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20));

        Random a = new Random("a");
        threadPoolExecutor.execute(a);
        threadPoolExecutor.execute(new Random("b"));
        threadPoolExecutor.execute(new Random("c"));
        threadPoolExecutor.execute(new Random("d"));
        threadPoolExecutor.execute(new Random("e"));
        System.out.println(a.getLocal().get());
    }

    private static class Random implements Runnable {

        private ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return num;
            }
        };
        private final Integer num = ThreadLocalRandom.current().nextInt();
        private String name;

        public ThreadLocal<Integer> getLocal() {
            return local;
        }

        public Random(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(local.get());
        }
    }
}
