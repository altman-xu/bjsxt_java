package com.bjsxt.height.concurrent018;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuzhihua
 * @date 12/08/2018 1:21 PM
 */
public class UseThreadPoolExecutor2 implements Runnable {

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            int temp = count.incrementAndGet();
            System.out.println("任务 " + temp);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        BlockingQueue<Runnable> queue =
//                new LinkedBlockingDeque<Runnable>();
                new ArrayBlockingQueue<Runnable>(10);
        ExecutorService executor = new ThreadPoolExecutor(
                5,          // core
                10,     // max
                120l,       // 2min
                TimeUnit.SECONDS,
                queue
        );
        for (int i = 0; i < 20; i++) {
            executor.execute(new UseThreadPoolExecutor2());
        }
        Thread.sleep(1000);
        System.out.println("queue size: " + queue.size());
        Thread.sleep(2000);
    }
}
