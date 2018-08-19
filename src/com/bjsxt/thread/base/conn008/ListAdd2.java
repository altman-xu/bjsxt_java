package com.bjsxt.thread.base.conn008;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhihua
 * @date 12/08/2018 11:15 AM
 */
public class ListAdd2 {

    private volatile static List list = new ArrayList();

    public void add(){
        list.add("bjsxt");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {

        final ListAdd2 listAdd2 = new ListAdd2();
        final Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        System.out.println("t1 start...");
                        for (int i = 0; i < 10; i++) {
                            listAdd2.add();
                            System.out.println("current thread: " + Thread.currentThread().getName() + " add one new element...");
                            Thread.sleep(500);
                            if (listAdd2.size() == 5){
                                System.out.println("sent notice...");
                                lock.notify();
                            }
                        }
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("t2 start...");
                    if (listAdd2.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("current thread: " + Thread.currentThread().getName() + " receive notice that stop thread...");
                    throw new RuntimeException();
                }
            }
        }, "t2");

        t2.start();
        t1.start();

    }

}
