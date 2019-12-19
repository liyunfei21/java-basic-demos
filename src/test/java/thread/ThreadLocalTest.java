package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    /*
     线程本地变量
     */
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20));

        Random a = new Random("a");
        threadPoolExecutor.execute(a);
        threadPoolExecutor.execute(new Random("b"));
        threadPoolExecutor.execute(new Random("c"));
        threadPoolExecutor.execute(new Random("d"));
        threadPoolExecutor.execute(new Random("e"));
        threadPoolExecutor.shutdown();
    }

    private static class Random implements Runnable {
        public static ThreadLocal<String> local = new ThreadLocal<String>();

        private String name;

        public Random(String name) {

            this.name = name;
        }

        @Override
        public void run() {
            local.set(name);
            System.out.println(local.get());
        }
    }
}
