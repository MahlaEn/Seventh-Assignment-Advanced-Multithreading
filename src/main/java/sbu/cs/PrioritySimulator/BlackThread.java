package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public class BlackThread extends ColorThread {

    private static final String MESSAGE = "hi blues, hi whites!";
    private CountDownLatch blackCnt;

    public BlackThread(CountDownLatch blackCnt) {
        this.blackCnt = blackCnt;
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
        blackCnt.countDown();
        // TODO call printMessage
    }
}
