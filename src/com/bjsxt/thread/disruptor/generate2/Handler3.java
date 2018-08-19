package com.bjsxt.disruptor.generate2;

import com.bjsxt.disruptor.generate1.Trade;
import com.lmax.disruptor.EventHandler;

/**
 * @author xuzhihua
 * @date 12/08/2018 4:49 PM
 */
public class Handler3 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handler3: name: " + event.getName() + " ,price: " + event.getPrice() + "; instance: " + event.toString());
    }
}
