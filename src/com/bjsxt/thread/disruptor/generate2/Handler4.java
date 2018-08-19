package com.bjsxt.disruptor.generate2;

import com.bjsxt.disruptor.generate1.Trade;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @author xuzhihua
 * @date 12/08/2018 4:49 PM
 */
public class Handler4 implements EventHandler<Trade>, WorkHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(Trade event) throws Exception {
        System.out.println("handler4 : get name: " + event.getName());
        event.setName(event.getName() + " h4");
    }
}