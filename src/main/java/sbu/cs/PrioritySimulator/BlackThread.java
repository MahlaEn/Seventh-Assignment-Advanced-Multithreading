package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public class BlackThread extends ColorThread {

    private static final String MESSAGE = "hi blues, hi whites!";
    private static CountDownLatch blackCnt;
    private static CountDownLatch blueCnt;

    public BlackThread(CountDownLatch blackCnt,CountDownLatch blueCnt) {
        BlackThread.blackCnt = blackCnt;
        BlackThread.blueCnt = blueCnt;
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
            blackCnt.await();
            printMessage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            blueCnt.countDown();
        }
        // TODO call printMessage
    }
}
