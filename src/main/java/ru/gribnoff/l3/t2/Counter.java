package ru.gribnoff.l3.t2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter {

	private static final AtomicInteger count = new AtomicInteger();
	private static final Lock lock = new ReentrantLock();

	static AtomicInteger getCount() {
		return count;
	}

	private Counter() {}

	static void countUp() {
		lock.lock();
		System.out.println("+1 = " + count.incrementAndGet());
		lock.unlock();
	}

	static void countDown() {
		lock.lock();
		System.out.println("-1 = " + count.decrementAndGet());
		lock.unlock();
	}
}
