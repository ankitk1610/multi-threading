package com.threads.example.deadlock;

public class Demo {
	public static void main(String[] args) {

		Deadlock deadlock = new Deadlock();
		try {
			deadlock.runTests();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
	}
}
