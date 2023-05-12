package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public class BlueThread extends ColorThread {

    private static final String MESSAGE = "hi finished blacks, hi whites!";


    private CountDownLatch blueCnt;

    public BlueThread(CountDownLatch blueCnt) {
        this.blueCnt = blueCnt;
    }

    void printMessage() {
        super.printMessage(new Message(this.getClass().getName(), getMessage()));
    }

    @Override
    String getMessage() {
        return MESSAGE;
    }

    @Override
    public void run() {
        printMessage();
        blueCnt.countDown();
        // TODO call printMessage
    }
}
