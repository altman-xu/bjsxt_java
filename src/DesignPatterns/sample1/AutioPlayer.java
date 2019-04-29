package DesignPatterns.sample1;

/**
 * @author xuzh001
 * @date 2019-04-29
 * 接受者角色，由录音机类扮演
 */

public class AutioPlayer {

    public void play() {
        System.out.println("播放...");
    }

    public void rewind() {
        System.out.println("倒带...");
    }

    public void stop() {
        System.out.println("停止...");

    }
}
