package com.threads.example.deadlock;

public class Deadlock {

	private int counter = 0;
	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private void incrementCounter() throws InterruptedException {
		synchronized (lock1) {
			System.out.println("Incrementer : Acquired lock1");
			counter++;

			Thread.sleep(100);
			
			synchronized (lock2) {
				System.out.println("Incrementer : Acquired lock2");
				counter++;
			}
		}
	}

	private void decrementCounter() throws InterruptedException {
		synchronized (lock2) {
			System.out.println("Decrementer : Acquired lock2");
			counter--;
			Thread.sleep(100);
			
			synchronized (lock1) {
				System.out.println("Decrementer : Acquired lock1");
				counter--;
			}
		}
	}

	Runnable incrementer = new Runnable() {

		public void run() {

			try {

				for (int i = 0; i < 100; i++) {
					incrementCounter();
					System.out.println("Incrementing step: " + i);
				}
			} catch (InterruptedException e) {
				System.out.println("Exception Ocurred in incrementer");
			}

		}
	};

	Runnable decrementer = new Runnable() {

		public void run() {
			try {
				for (int i = 0; i < 100; i++) {
					decrementCounter();
					System.out.println("Decrementing step: " + i);
				}
			} catch (Exception e) {
				System.out.println("Exception Ocurred in decrementer");
			}

		}
	};
	
	
	public void runTests() throws InterruptedException {
		
		Thread inc = new Thread(incrementer);
		Thread dec = new Thread(decrementer);
		
		inc.start();
		inc.sleep(100);
		
		dec.start();
		
		inc.join();
		dec.join();
		
		System.out.println("Counter: " + counter);
		
		
		
	}
}
