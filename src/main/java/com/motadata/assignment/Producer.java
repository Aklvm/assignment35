package com.motadata.assignment;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
    private BlockingQueue<Integer> queue;
    private Random random;

    /**
     * Constructor to initialize the producer with the queue.
     * 
     * @param queue The blocking queue where the producer will place generated
     *              integers.
     */

    Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
        random = new Random();

    }

    /**
     * The main logic for the producer thread that generates random integers and
     * puts them into the provided queue.
     * <p>
     * This method continuously generates random integers using
     * {@link Random#nextInt()} and places them into the
     * {@link BlockingQueue} using the {@link BlockingQueue#put(Object)} method. It
     * then sleeps for 100 milliseconds
     * between each operation, simulating work and allowing the consumer to process
     * items from the queue.
     * </p>
     * <p>
     * The method will continue running until the thread is interrupted. When the
     * thread is interrupted, it exits the loop
     * and terminates gracefully.
     * </p>
     * 
     * @see BlockingQueue
     * @see Random
     */

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            int temp = random.nextInt();
            try {
                queue.put(temp);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }
        }
    }
}
