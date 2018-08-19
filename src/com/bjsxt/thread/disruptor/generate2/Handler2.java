package com.bjsxt.thread.disruptor.generate2;

import com.bjsxt.thread.disruptor.generate1.Trade;
import com.lmax.disruptor.EventHandler;

/**
 * @author xuzhihua
 * @date 12/08/2018 4:49 PM
 */
public class Handler2 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handler2: set price");
        event.setPrice(17.0);
        Thread.sleep(1000);
    }
}
