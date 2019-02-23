package test.ThreadPoolDemo;

/**
 * @author xuzhihua
 * @date 2019/2/23 11:58 AM
 * 简单的线程池接口定义
 *
 * 客户端可以通过execute(Job)将Job提交入线程池执行，而客户端自身不用等待Job的执行完成
 * 这里的工作者线程代表一个重复执行Job的线程
 * 而每个客户端提交的Job豆浆进入到一个工作队列中等待工作者线程的处理
 */
public interface ThreadPool<Job extends Runnable> {
    // 执行一个job，这个job需要实现Runnable
    void execute(Job job);
    // 关闭线程池
    void shutDown();
    // 增加工作组线程
    void addWorker(int num);
    // 减少工作组线程
    void removeWorker(int num);
    // 得到正在等待执行的任务数量
    int getJobSize();
}
