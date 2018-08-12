package com.bjsxt.height.concurrent017;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhihua
 * @date 12/08/2018 1:05 PM
 */

class Temp extends Thread {
    public void run() {
        System.out.println("run");
    }
}

public class ScheduledJob {

    public static void main(String[] args) {
        Temp command = new Temp();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(command, 5, 1, TimeUnit.SECONDS);
    }

}
