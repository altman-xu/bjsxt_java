package com.bjsxt.thread.height.lock021;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xuzhihua
 * @date 12/08/2018 3:50 PM
 */
public class UseReentrantReadWriteLock {

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    public void read(){
        try {
            readLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write(){
        try {
            writeLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        final UseReentrantReadWriteLock urrw = new UseReentrantReadWriteLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t4");

//        情况1 读读 情况下，可以重入
//		t1.start(); // R
//		t2.start(); // R

//        情况2.1 读写 情况下，按照顺序
        t1.start(); // R
        t3.start(); // W

//        情况2.2 写读 情况下，按照顺序
//        t1.start(); // w
//        t3.start(); // R

//        情况3 写写 情况下， 按照顺序
//        t3.start(); // W
//        t4.start(); // W

    }
}
