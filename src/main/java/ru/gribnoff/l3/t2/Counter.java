package ru.gribnoff.l3.t2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter {

	private int count;
	private final Lock lock = new ReentrantLock();

	int getCount() {
		return count;
	}

	private Counter() {}

	void countUp() {
		lock.lock();
		count++;
		lock.unlock();
	}

	void countDown() {
		lock.lock();
		count--;
		lock.unlock();
	}
}
