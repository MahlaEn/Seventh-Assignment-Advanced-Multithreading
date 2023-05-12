package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public class BlueThread extends ColorThread {

    private static final String MESSAGE = "hi finished blacks, hi whites!";


    private static CountDownLatch whiteCnt;
    private static CountDownLatch blueCnt;

    public BlueThread(CountDownLatch blueCnt, CountDownLatch whiteCnt) {
        BlueThread.whiteCnt = whiteCnt;
        BlueThread.blueCnt = blueCnt;
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
        try {
            blueCnt.await();
            printMessage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            whiteCnt.countDown();
        }
        // TODO call printMessage
    }
}
