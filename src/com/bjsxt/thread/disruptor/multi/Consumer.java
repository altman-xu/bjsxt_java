package com.bjsxt.disruptor.multi;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuzhihua
 * @date 12/08/2018 5:08 PM
 */
public class Consumer implements WorkHandler<Order> {

    private String consumerId;

    private static AtomicInteger count = new AtomicInteger(0);

    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }


    @Override
    public void onEvent(Order event) throws Exception {
        System.out.println("当前消费者: " + this.consumerId + ",消费信息: " + event.getId());
        count.incrementAndGet();
    }

    public int getCount(){
        return count.get();
    }
}

