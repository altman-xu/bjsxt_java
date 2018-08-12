package com.bjsxt.disruptor.base;


import com.lmax.disruptor.EventFactory;

/**
 * @author xuzhihua
 * @date 12/08/2018 3:57 PM
 */

// 需要让disruptor为我们创建事件，我们同事还声明了一个eventFactory来实例化event对象
public class LongEventFactory implements EventFactory {

    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
