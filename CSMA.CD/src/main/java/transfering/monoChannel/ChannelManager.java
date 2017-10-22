package transfering.monoChannel;

public interface ChannelManager {
    boolean isChannelBusy();

    void waitForChannelIsFree() throws InterruptedException;
}
