package com.bjsxt.thread.disruptor.generate1;

import com.lmax.disruptor.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xuzhihua
 * @date 12/08/2018 4:33 PM
 */
public class Main1 {

    public static void main(String[] args) throws Exception {
        int BUFFER_SIZE = 1024;
        int THREAD_NUMBERS = Runtime.getRuntime().availableProcessors();

        /**
         * createSingleProducer 创建一个单生产者的RingBuffer
         * 第一个参数 EventFactory，从名字理解就是事件工厂，其实它的职责就是产生数据填充RingBuffer的区块
         * 第二个参数 RingBuffer 的大小，它必须是2的指数倍，目的是为了将求模运算转为& 提高运算效率
         * 第三个参数 RingBuffer的生产都在没有可用区块的时候(可能是消费者(或者说是事件处理器)太慢了)的等待策略
         */
        final RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(
                new EventFactory<Trade>() {
                    @Override
                    public Trade newInstance() {
                        return new Trade();
                    }
                },
                BUFFER_SIZE,
                new YieldingWaitStrategy()
        );

        // 创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);

        // 创建SequenceBarrier
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        // 创建消息处理器
        BatchEventProcessor<Trade> transProcessor = new BatchEventProcessor<Trade>(
                ringBuffer, sequenceBarrier, new TradeHandler()
        );

        // 这一步的目的就是把消费者的位置信息引用注入到生产者    如果只有一个消费者的情况可以省略
        ringBuffer.addGatingSequences(transProcessor.getSequence());

        // 把消息处理器提交到线程池
        executors.submit(transProcessor);

        // 如果存在多个消费者 那么重复执行上面3行代码，把TradeHandler换成其他消费者类

        Future<?> future = executors.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                long seq;
                for (int i = 0; i < 10; i++) {
                    // 占个坑 RingBuffer一个可用区块
                    seq = ringBuffer.next();
                    // 给这个区块放入数据
                    ringBuffer.get(seq).setPrice(Math.random()*9999);
                    // 发布这个区块的数据，使handler(consumer)可见
                    ringBuffer.publish(seq);
                }
                return null;
            }
        });

        // 等待生产者结束
        future.get();
        // 登上1秒，等消费都处理完成
        Thread.sleep(1000);
        // 通知事件(或者说消息)处理器，可以结束(并不是马上介绍!!!)
        transProcessor.halt();
        // 终止线程
        executors.shutdown();

    }



}
