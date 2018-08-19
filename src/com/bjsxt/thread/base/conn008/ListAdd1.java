package com.bjsxt.thread.base.conn008;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhihua
 * @date 12/08/2018 11:12 AM
 */
public class ListAdd1 {
    private volatile static List list = new ArrayList();

    public void add(){
        list.add("bjsxt");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd1 listAdd1 = new ListAdd1();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    listAdd1.add();
                    System.out.println("current thread: " + Thread.currentThread().getName() + " add one new element..");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (listAdd1.size() == 5){
                        System.out.println("current thread receive notice: " + Thread.currentThread().getName() + " list size = 5, thread stop...");
                        throw new RuntimeException();
                    }
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
