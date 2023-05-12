package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public class WhiteThread extends ColorThread {

    private static final String MESSAGE = "hi finished blacks, hi finished blues!";

    private CountDownLatch whiteCnt;

    public WhiteThread(CountDownLatch whiteCnt) {
        this.whiteCnt = whiteCnt;
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
        whiteCnt.countDown();
        // TODO call printMessage
    }
}
