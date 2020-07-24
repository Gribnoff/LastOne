package ru.gribnoff.l3.t1;

class PingPong {
	public static void main(String[] args) {
		Object lock = new Object();
		PingPongThread ping = new PingPongThread("ping", lock);
		PingPongThread pong = new PingPongThread("pong", lock);
		ping.start();
		pong.start();
	}
}
