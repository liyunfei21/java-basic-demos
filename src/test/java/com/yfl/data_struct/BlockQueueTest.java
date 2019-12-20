package com.yfl.data_struct;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockQueueTest {

    private static BlockingQueue get1(){
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        return queue;
    }

    public static BlockingQueue get2(){
        BlockingQueue queue = new ArrayBlockingQueue(10);
        return queue;
    }

    public static void main(String[] args) {
        BlockingQueue q1 = get1();
        int size = q1.size();
        for(int i = 0 ; i < size ; i++){
            System.out.println(q1.poll());
        }
    }
}
