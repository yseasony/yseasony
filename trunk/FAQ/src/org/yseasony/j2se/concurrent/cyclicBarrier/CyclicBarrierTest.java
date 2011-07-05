package org.yseasony.j2se.concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	static ExecutorService exe = Executors.newFixedThreadPool(50);
	
	class End implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("End............");
		}
		
	}
	
	class Bow implements Runnable {
		CyclicBarrier barr;

		public Bow(CyclicBarrier barr) {
			this.barr = barr;
		}

		public void run() {
			System.out.println("The bow is coming");
			System.out.println("kick a down");
			try {
				barr.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//System.out.println("do other thing");
		}
	}
	
	public static void testCyclicBarrier() throws InterruptedException,
			BrokenBarrierException {
		CyclicBarrierTest t = new CyclicBarrierTest();
		CyclicBarrier barr = new CyclicBarrier(6,t.new End());
		for (int i = 0; i < 5; i++) {
			exe.execute(t.new Bow(barr));
		}
		barr.await();
	}

	public static void main(String[] args) {
		while(true){
			try {
				testCyclicBarrier();
			} catch (InterruptedException e) {
			} catch (BrokenBarrierException e) {
			}
		}
		
	}

}
