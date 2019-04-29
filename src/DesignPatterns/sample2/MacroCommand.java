package DesignPatterns.sample2;

import DesignPatterns.sample1.Command;

/**
 * @author xuzh001
 * @date 2019-04-29
 * 宏命令接口
 */
public interface MacroCommand extends Command {
    /**
     * 宏命令集的管理方法
     * 可以添加一个成员变量
     */
    public void add(Command command);

    /**
     * 宏命令集的管理办法
     * 可以移除一个成语变量
     */
    public void remove(Command command);
}
