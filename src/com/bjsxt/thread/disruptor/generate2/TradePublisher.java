package com.bjsxt.thread.disruptor.generate2;

import com.bjsxt.thread.disruptor.generate1.Trade;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author xuzhihua
 * @date 12/08/2018 4:49 PM
 */
public class TradePublisher implements Runnable {

    Disruptor<Trade> disruptor;
    private CountDownLatch latch;
    private static int LOOP = 10;   // 交易次数 模拟百万次交易的发生

    public TradePublisher(CountDownLatch latch, Disruptor<Trade> disruptor) {
        this.latch = latch;
        this.disruptor = disruptor;
    }

    @Override
    public void run() {
        TradeEventTranslator tradeEventTranslator = new TradeEventTranslator();
        for (int i = 0; i < LOOP; i++) {
            disruptor.publishEvent(tradeEventTranslator);
        }
        latch.countDown();
    }

    class TradeEventTranslator implements EventTranslator<Trade>{

        private Random random = new Random();

        @Override
        public void translateTo(Trade event, long sequence) {
            this.generateTrade(event);
        }

        private Trade generateTrade(Trade trade) {
            trade.setPrice(random.nextDouble()*9999);
            return trade;
        }
    }

}
