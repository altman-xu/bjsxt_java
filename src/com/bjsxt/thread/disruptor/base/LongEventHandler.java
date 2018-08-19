package com.bjsxt.disruptor.base;

import com.lmax.disruptor.EventHandler;

/**
 * @author xuzhihua
 * @date 12/08/2018 4:00 PM
 */

// 我们还需要一个时间消费者，也就是时间处理器，这个事件处理器简单地把事件中存储的数据打印终端:
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue());
    }
}
