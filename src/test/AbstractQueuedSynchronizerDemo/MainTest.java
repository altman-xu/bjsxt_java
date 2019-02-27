package test.AbstractQueuedSynchronizerDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author xuzhihua
 * @date 2019/2/24 10:30 AM
 * 测试类
 * 测试结果，线程名称成对输出，也就是在同一个时刻只有两个线程能够获取到锁
 */
public class MainTest {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Integer.MAX_VALUE);
        test();
    }

    public static void test() {
        final Lock lock = new TwinsLock();
        class Worker extends  Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
