package sbu.cs.PrioritySimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Runner {

    public static List<Message> messages = new ArrayList<>();

    /**
     * Simulate a priority system between Black, white, and blue threads.
     * Given a number of each thread type, your program must first execute every Black thread, then
     execute every Blue thread, and finally execute every white thread. (Black > Blue > White)
     * Complete each thread type class by adding the specified code in the respective run() methods.

     * The order of execution only matters when comparing two different thread types. This means two
     black threads are not prioritised over each other (can swap their execution order) but between a
     Black thread and a Blue thread, the Black thread must fully execute before the Blue thread starts.

     * You are NOT ALLOWED to use the Thread.Sleep() method to solve this problem, as it does not
     guarantee the desired thread execution order.
     * You can utilize other classes and methods available in the java.util.concurrent package. It is
     recommended that you use the "CountDownLatch" class to solve the problem.

     * You are allowed to change the existing code in any of the given classes or add your own new code
     to any of them.
     * Your code must pass all of the test cases provided in the test folder.

     * @param blackCount    number of black threads
     * @param blueCount     number of blue threads
     * @param whiteCount    number of white threads
     */
    private static final CountDownLatch latch1=new CountDownLatch(1);
    private static final CountDownLatch latch2 = new CountDownLatch(1);
    private static final CountDownLatch latch3 = new CountDownLatch(1);
    public void run(int blackCount, int blueCount, int whiteCount) throws InterruptedException {
        List<ColorThread> colorThreads = new ArrayList<>();

        // TODO
        for (int i = 0; i < blackCount; i++) {
            BlackThread blackThread = new BlackThread(latch1,latch2);
            colorThreads.add(blackThread);
            blackThread.start();
        }
        latch1.countDown();
        // TODO
        for (int i = 0; i < blueCount; i++) {
            BlueThread blueThread = new BlueThread(latch2,latch3);
            colorThreads.add(blueThread);
            blueThread.start();
        }
        latch2.countDown();
        // TODO

        for (int i = 0; i < whiteCount; i++) {
            WhiteThread whiteThread = new WhiteThread();
            colorThreads.add(whiteThread);
            whiteThread.start();
        }
        latch3.countDown();
        for (ColorThread thread : colorThreads) {
            thread.join();
        }

        // TODO
    }

    synchronized public static void addToList(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public static void main(String[] args) throws InterruptedException {
        int blackCnt,blueCnt,whiteCnt;
        Scanner in = new Scanner(System.in);
        blackCnt=in.nextInt();
        blueCnt=in.nextInt();
        whiteCnt=in.nextInt();
        Runner Main=new Runner();
        Main.run(blackCnt,blueCnt,whiteCnt);
        // Use the main function to test the code yourself
    }
}
