package com.motadata.assignment;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.testng.Assert;

import org.testng.annotations.Test;

/**
 * Unit tests for the Producer-Consumer application, ensuring that the consumer
 * and producer
 * threads interact as expected. These tests verify that:
 * <ul>
 * <li>The producer and consumer threads are active and processing messages
 * correctly.</li>
 * <li>The consumer processes messages and classifies them as success or error
 * records based on their values.</li>
 * </ul>
 * These tests simulate the producer-consumer behavior using a
 * {@link BlockingQueue}.
 */

class AssignmentApplicationTests {
	Producer producer;
	Consumer consumer;
	BlockingQueue<Integer> queue;

	/**
	 * Test to verify that both the producer and consumer threads are active and
	 * processing
	 * messages correctly. This test starts both threads, waits for a period of time
	 * to allow them
	 * to operate, and then checks whether both success and error records have been
	 * processed by the consumer.
	 * 
	 * <p>
	 * This test interrupts both the producer and consumer threads after waiting for
	 * 3 seconds
	 * to simulate their active processing.
	 * </p>
	 *
	 * @throws InterruptedException If the thread is interrupted during sleep or
	 *                              joining.
	 */

	@Test
	public void testConsumerProducerAreActive() throws InterruptedException {

		queue = new LinkedBlockingQueue<>();
		producer = new Producer(queue);
		consumer = new Consumer(queue);

		producer.start();
		consumer.start();

		Thread.sleep(3000);
		producer.interrupt();
		consumer.interrupt();

		producer.join();
		consumer.join();

		Assert.assertTrue(consumer.getErrorCount() > 0);
		Assert.assertTrue(consumer.getSucessCount() > 0);

	}

	/**
	 * Test to simulate the processing of a predefined set of messages. In this
	 * test, 10 messages are added
	 * to the queue, and the consumer processes them. The success count is expected
	 * to be 5 (even numbers) and
	 * the error count is expected to be 5 (odd numbers).
	 * 
	 * <p>
	 * This test adds 10 integers to the queue and then starts the consumer thread
	 * to process them.
	 * </p>
	 * After a short period, the consumer is interrupted, and the final success and
	 * error counts are asserted.
	 *
	 * @throws InterruptedException If the thread is interrupted during sleep or
	 *                              joining.
	 */

	@Test
	public void testProcessingMessage() throws InterruptedException {
		queue = new LinkedBlockingQueue<>();
		producer = new Producer(queue);
		consumer = new Consumer(queue);
		for (int i = 1; i <= 10; i++) {
			queue.put(i);
		}
		consumer.start();

		Thread.sleep(1000);

		consumer.interrupt();
		Assert.assertTrue(consumer.getErrorCount() == 5);
		Assert.assertTrue(consumer.getSucessCount() == 5);

	}

}
