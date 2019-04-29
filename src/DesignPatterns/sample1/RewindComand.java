package DesignPatterns.sample1;

/**
 * @author xuzh001
 * @date 2019-04-29
 */
public class RewindComand implements Command {

    private AutioPlayer autioPlayer;

    public RewindComand(AutioPlayer autioPlayer) {
        this.autioPlayer = autioPlayer;
    }

    @Override
    public void execute() {
        autioPlayer.rewind();
    }
}
