package com.bjsxt.disruptor.base;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author xuzhihua
 * @date 12/08/2018 4:03 PM
 *
 * Disruptor 3.0提供了lambda式的API。这样可以把一些复杂的操作放在Ring Buffer，
 * 所以在Disruptor3.0以后的版本最好使用Event Publisher或者Event Translator来发布事件
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 *
 */
public class LongEventProducerWithTranslator {

    // 一个translator可以看做一个事件初始化器，publicEvent方法会调用它
    // 填充event
    public static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
            (longEvent, l, byteBuffer) -> longEvent.setValue(byteBuffer.getLong(0));

    public void onData(ByteBuffer buffer) {
        ringBuffer.publishEvent(TRANSLATOR, buffer);
    }

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
}
