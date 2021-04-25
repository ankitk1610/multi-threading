package com.threads.example.semaphore;

import java.util.concurrent.Semaphore;

import javax.management.RuntimeErrorException;

public class SemaphoreDemo {
	public static void main(String[] args) throws InterruptedException {
		CorrectSemaphore.example();
	}
}

class CorrectSemaphore {

	final static Semaphore semaphore = new Semaphore(1);

	public static void example() throws InterruptedException {
		Thread badThread = new Thread(new Runnable() {

			public void run() {

				while (true) {

					try {
						semaphore.acquire();
						try {
							throw new RuntimeErrorException(null);
						} catch (Exception e) {
							return;
						} 
							finally {
							System.out.println("BadThread Releasing Semaphore..");
							semaphore.release();
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		badThread.start();

		Thread.sleep(1000);

		Thread goodThread = new Thread(new Runnable() {

			public void run() {
				System.out.println("Good Thread waiting for Semaphore...");
				try {

					semaphore.acquire();

					System.out.println("Good Thread has acquired Semaphore...");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		goodThread.start();

		badThread.join();
		goodThread.join();

		System.out.println("Done!");

	}
}