package test;

import javax.management.relation.RoleUnresolved;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhihua
 * @date 2019/2/23 9:57 AM
 */
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> System.out.println("Hello World java8 Lambda")).start();

        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            // 加锁，拥有lock的monitor
            synchronized (lock) {
                // 当条件不满足，继续wait
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        // 释放了lock的锁
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 当条件满足，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            // 加锁，拥有lock的monitor
            synchronized (lock) {
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁
                // 直到当前线程释放了lock后，WaitThrad才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
