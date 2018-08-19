package com.bjsxt.base.conn010;

/**
 * @author xuzhihua
 * @date 12/08/2018 12:40 PM
 */
public class ConnThreadLocal {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public void setThreadLocal(String value) {
        threadLocal.set(value);
    }
    public void getThreadLocal(){
        System.out.println(Thread.currentThread().getName() + ": " + this.threadLocal.get());
    }

    public static void main(String[] args) throws InterruptedException {
        final ConnThreadLocal connThreadLocal = new ConnThreadLocal();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                connThreadLocal.setThreadLocal("altman");
                connThreadLocal.getThreadLocal();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    connThreadLocal.setThreadLocal("xuzhihua");
                    connThreadLocal.getThreadLocal();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        t2.start();

    }
}
