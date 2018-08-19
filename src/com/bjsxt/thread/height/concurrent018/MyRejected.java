package com.bjsxt.thread.height.concurrent018;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xuzhihua
 * @date 12/08/2018 1:11 PM
 */
public class MyRejected implements RejectedExecutionHandler {

    public MyRejected() {

    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自定义处理..");
        System.out.println("当前被拒绝任务为: " + r.toString());
    }
}
