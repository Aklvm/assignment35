package com.motadata.assignment;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer extends Thread {
    private BlockingQueue<Integer> queue;
    private AtomicInteger sucessCount;
    private AtomicInteger errorCount;

    /**
     * Constructor to initialize the consumer with the queue.
     * 
     * @param queue The blocking queue where the producer will place generated
     *              integers.
     */

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
        sucessCount = new AtomicInteger(0);
        errorCount = new AtomicInteger(0);

    }

    /**
     * The main logic of the consumer thread that takes messages from the queue and
     * processes them.
     * <p>
     * The thread runs in a loop where it continuously takes messages from the
     * {@link BlockingQueue} using
     * {@link BlockingQueue#take()}, which blocks until an item is available. Once a
     * message is taken from the queue,
     * the {@link #processMessage(int)} method is called to process the message. The
     * loop will continue running until
     * the thread is interrupted.
     * </p>
     * <p>
     * If the thread is interrupted while it is waiting for a message in the queue
     * (during the {@link BlockingQueue#take()}
     * call), an {@link InterruptedException} is thrown. In such cases, the
     * interrupt status is restored and the loop exits.
     * </p>
     *
     * @see BlockingQueue
     * @see #processMessage(int)
     */
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                int temp = queue.take();
                processMessage(temp);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        }
    }

    /**
     * Processes a single message (represented by an integer) and updates the
     * success or error count.
     * <p>
     * This method classifies the message as a success if the integer is even and an
     * error if the integer is odd.
     * It updates the respective counters using
     * {@link AtomicInteger#incrementAndGet()} to ensure thread-safe
     * updates to the success and error counts. Additionally, the method prints out
     * the current count for each category.
     * </p>
     * 
     * @param temp The message (integer) to be processed.
     *             If the integer is even, it is considered a success; if it is odd,
     *             it is considered an error.
     */

    private void processMessage(int temp) {
        if (temp % 2 == 0) {
            System.out.println("Success record processed till now " + sucessCount.incrementAndGet());
        } else {
            System.out.println("Error  record processed till now " + errorCount.incrementAndGet());
        }
    }

    public int getSucessCount() {
        return this.sucessCount.get();
    }

    public int getErrorCount() {
        return this.errorCount.get();
    }

}
