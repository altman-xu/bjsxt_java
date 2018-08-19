package com.bjsxt.base.conn009;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟Queue
 * @author xuzhihua
 * @date 12/08/2018 12:27 PM
 */
public class MyQueue {

    private final LinkedList<Object> list = new LinkedList<Object>();

    private final AtomicInteger count = new AtomicInteger(0);

    private final int maxSize;
    private final int minSize = 0;

    private final Object lock = new Object();

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void put(Object obj) {
        synchronized (lock) {
            while (count.get() == maxSize){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            list.add(obj);
            count.getAndIncrement();
            System.out.println(" element " + obj + " has been added");
            lock.notify();
        }
    }

    public Object take(){
        Object temp = null;
        synchronized (lock) {
            while (count.get() == minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            count.getAndDecrement();
            temp = list.removeFirst();
            System.out.println(" element " + temp + " has been consumed");
            lock.notify();
        }
        return temp;
    }

    public int size() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {

        final MyQueue myQueue = new MyQueue(5);
        myQueue.put("a");
        myQueue.put("b");
        myQueue.put("c");
        myQueue.put("d");
        myQueue.put("e");
        System.out.println("current num of element: " + myQueue.size());
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.put("h");
                myQueue.put("i");
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Object t1 = myQueue.take();
                    System.out.println("element been taken is " + t1);
                    Thread.sleep(1000);
                    Object t2 = myQueue.take();
                    System.out.println("element been taken is " + t2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        Thread.sleep(1000);
        t2.start();

    }

}
