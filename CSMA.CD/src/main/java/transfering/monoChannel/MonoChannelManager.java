package transfering.monoChannel;

import java.util.Random;

public class MonoChannelManager implements ChannelManager, CollisionManager, DelayManager {

    private final int SLOT_TIME = 10;
    private final int SECONDS_ONE = 1000;

    @Override
    public boolean isThereCollision() {
        if (isSecondOdd()) {
            System.out.println("x");
            return true;
        }
        return false;
    }

    @Override
    public boolean isChannelBusy() {
        return isSecondOdd();
    }

    @Override
    public void waitForChannelIsFree() throws InterruptedException {
        if (isChannelBusy()) {
            System.out.println("Channel is busy");
            Thread.sleep(SECONDS_ONE);
        }
        System.out.println("Channel is free");
    }

    @Override
    public int calculateDelay(int numberOfTry) {
        return new Random().nextInt((int) Math.pow(2, Math.min(10, numberOfTry))) * SLOT_TIME;
    }

    @Override
    public void makeDelay(int delay) throws InterruptedException {
        Thread.sleep(delay);
    }

    private boolean isSecondOdd() {
        return (System.currentTimeMillis() % 2) == 0;
    }
}
