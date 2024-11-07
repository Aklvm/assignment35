package com.motadata.assignment;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

public class MainAssignmentApplication {

	/**
 * The entry point of the application that simulates a producer-consumer scenario.
 * <p>
 * This method initializes a blocking queue, creates a producer and a consumer, 
 * starts both threads, and lets them run for a fixed amount of time (10 seconds). 
 * After 10 seconds, the producer and consumer threads are interrupted to simulate a shutdown.
 * Finally, it prints the number of successful and error records processed by the consumer.
 * </p>
 *
 * @param args Command-line arguments (not used in this program).
 */

	public static void main(String[] args) {
		BlockingQueue<Integer> queue= new LinkedBlockingQueue<>();
	    Producer producer= new Producer(queue);
		Consumer consumer= new Consumer(queue);

		producer.start();
		consumer.start();

		try {
			Thread.sleep(10000);
		
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
       producer.interrupt();
	   consumer.interrupt();

		System.out.println("Success Record processed "+consumer.getSucessCount());
		System.out.println("Error Record processed "+consumer.getErrorCount());
	}

}
