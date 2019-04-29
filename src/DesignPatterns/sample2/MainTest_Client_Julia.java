package DesignPatterns.sample2;

import DesignPatterns.sample1.*;

/**
 * @author xuzh001
 * @date 2019-04-29
 */
public class MainTest_Client_Julia {
    public static void main(String[] args) {
        // 创建接受者对象
        AutioPlayer autioPlayer = new AutioPlayer();
        // 创建命令对象
        Command playCommand = new PlayCommand(autioPlayer);
        Command rewindCommand = new RewindComand(autioPlayer);
        Command stopCommand = new StopCommand(autioPlayer);

        // 创建宏命令对象
        MacroCommand macroCommand = new MacroAutioCommand();
        // 记录操作命令
        macroCommand.add(playCommand);
        macroCommand.add(rewindCommand);
        macroCommand.add(stopCommand);
        macroCommand.add(playCommand);
        macroCommand.add(stopCommand);
        // 执行宏命令
        macroCommand.execute();
    }
}
