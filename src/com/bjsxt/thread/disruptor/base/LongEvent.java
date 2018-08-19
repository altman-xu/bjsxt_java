package com.bjsxt.thread.disruptor.base;

/**
 * @author xuzhihua
 * @date 12/08/2018 3:56 PM
 *
 * disruptor 框架
 * http://ifeve.com/disruptor-getting-started/
 */
public class LongEvent {
    private long value;
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
