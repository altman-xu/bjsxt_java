package com.bjsxt.thread.height.concurrent018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhihua
 * @date 12/08/2018 1:14 PM
 */
public class UseThreadPoolExecutor1 {

    public static void main(String[] args) {
        /**
         * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
         * 若大于corePoolSize，则会将任务加入队列，
         * 若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
         * 若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式。
         *
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1,              // coreSize
                2,          // MaxSize
                60,             // 60
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3)     // 指定一种队列（有界队列）
//                new LinkedBlockingDeque<Runnable>()
                , new MyRejected()
//                ,new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        MyTask myTask1 = new MyTask(1, "任务1");
        MyTask myTask2 = new MyTask(2, "任务2");
        MyTask myTask3 = new MyTask(3, "任务3");
        MyTask myTask4 = new MyTask(4, "任务4");
        MyTask myTask5 = new MyTask(5, "任务5");
        MyTask myTask6 = new MyTask(6, "任务6");

        pool.execute(myTask1);
        pool.execute(myTask2);
        pool.execute(myTask3);
        pool.execute(myTask4);
        pool.execute(myTask5);
        pool.execute(myTask6);

        pool.shutdown();

    }

}
