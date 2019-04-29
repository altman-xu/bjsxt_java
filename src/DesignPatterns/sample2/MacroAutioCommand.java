package DesignPatterns.sample2;

import DesignPatterns.sample1.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzh001
 * @date 2019-04-29
 */
public class MacroAutioCommand implements MacroCommand {

    private List<Command> commandList = new ArrayList<>();

    @Override
    public void execute() {
        System.out.println("--- 自动回放保存的命令 ---");
        for (Command command:commandList){
            command.execute();
        }
        System.out.println("--- 宏命令集回放完成 ---");
    }
    @Override
    public void add(Command command) {
        commandList.add(command);
    }
    @Override
    public void remove(Command command) {
        commandList.remove(command);
    }
}
