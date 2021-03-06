package com.bjsxt.thread.height.concurrent018;

/**
 * @author xuzhihua
 * @date 12/08/2018 1:12 PM
 */
public class MyTask implements Runnable {

    private int taskId;
    private String taskName;

    public MyTask(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                '}';
    }

    @Override
    public void run() {
        try {
            System.out.println("run taskId = " + this.taskId);
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
