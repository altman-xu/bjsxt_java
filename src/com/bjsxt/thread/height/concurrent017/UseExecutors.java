package com.bjsxt.height.concurrent017;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuzhihua
 * @date 12/08/2018 1:08 PM
 */
public class UseExecutors {

    public static void main(String[] args) {
        ExecutorService pool1 = Executors.newScheduledThreadPool(10);
        ExecutorService pool2 = Executors.newCachedThreadPool();
        ExecutorService pool3 = Executors.newFixedThreadPool(10);
        ExecutorService pool4 = Executors.newSingleThreadExecutor();


    }
}
