package transfering.monoChannel;

public interface DelayManager {
    int calculateDelay(int numberOfTry);
    void makeDelay(int delay) throws InterruptedException;
}
