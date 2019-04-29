package DesignPatterns.sample1;

/**
 * @author xuzh001
 * @date 2019-04-29
 * 客户端类，由朱莉小女孩扮演
 */
public class MainTest_Client_Julia {

    public static void main(String[] args) {
        // 创建接受者对象
        AutioPlayer autioPlayer = new AutioPlayer();
        // 创建命令对象
        Command playCommand = new PlayCommand(autioPlayer);
        Command rewindCommand = new RewindComand(autioPlayer);
        Command stopCommand = new StopCommand(autioPlayer);

        // 创建请求者对象
        Keypad keypad = new Keypad();
        keypad.setPlayCommand(playCommand);
        keypad.setRewindCommand(rewindCommand);
        keypad.setStopCommand(stopCommand);

        // 测试
        keypad.play();
        keypad.rewind();
        keypad.stop();
        keypad.play();
        keypad.stop();
    }

}
