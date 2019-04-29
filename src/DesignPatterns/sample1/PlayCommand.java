package DesignPatterns.sample1;


/**
 * @author xuzh001
 * @date 2019-04-29
 * 具体命令角色
 */
public class PlayCommand implements Command{

    private AutioPlayer audioPlayer;

    public PlayCommand(AutioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.play();
    }
}

