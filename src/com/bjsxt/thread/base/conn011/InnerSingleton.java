package com.bjsxt.thread.base.conn011;

/**
 * @author xuzhihua
 * @date 12/08/2018 12:46 PM
 *
 * 内部静态类 -> 单例模式
 */
public class InnerSingleton {

    private static class Singleton {
        private static Singleton single = new Singleton();
    }
    public static Singleton getInstance(){
        return Singleton.single;
    }

}
