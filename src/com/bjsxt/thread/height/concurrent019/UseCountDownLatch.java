package com.bjsxt.thread.height.concurrent019;

import java.util.concurrent.CountDownLatch;

/**
 * @author xuzhihua
 * @date 12/08/2018 1:30 PM
 *
 * CountDownLatch
 */
public class UseCountDownLatch {

    public static void main(String[] args) {


        final CountDownLatch countDownLatch = new CountDownLatch(2);    // 此处参数为2 说明需要两次 countDown后，await的线程才能执行

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("进入线程t1" + "等待其他线程处理完成...");
                    countDownLatch.await();
                    System.out.println("t1线程继续执行...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t2线程进行初始化操作...");
                    Thread.sleep(2000);
                    System.out.println("t2线程初始化完毕，通知t1线程继续...");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t3线程进行初始化操作...");
                    Thread.sleep(4000);
                    System.out.println("t3线程初始化完毕，通知t1线程继续...");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }

}
