package ru.gribnoff.l3.t2;

import java.util.ArrayList;
import java.util.List;

public class CounterExample {
	static Counter counter = new Counter();

	public static void main(String[] args) {
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			threads.add(new CountUpRunnable());
			threads.add(new CountDownRunnable());
		}

		threads.forEach(runnable -> {
			runnable.start();
			try {
				runnable.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(counter.getCount());
	}

	private static class CountUpRunnable extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				counter.countUp();
			}
		}
	}

	private static class CountDownRunnable extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				counter.countDown();
			}
		}
	}
}
