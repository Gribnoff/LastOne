package ru.gribnoff.l3.t1;

class PingPongThread extends Thread {
	private static volatile int count = 10;
	private final String s;
	private final Object lock;

	public PingPongThread(String s, Object lock) {
		this.s = s;
		this.lock = lock;
	}

	@Override
	public void run() {
		synchronized (lock) {
			while (count-- > 0) {
				System.out.println(s);
				lock.notify();
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.notify();
			}
		}
	}
}
