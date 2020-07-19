package ru.gribnoff.l1.t3;

class Circle implements Figure {
	private final int radius;

	public Circle(int radius) {
		this.radius = radius;
	}

	@Override
	public void draw() {
		System.out.printf("Circle with radius %d is drawn%n", radius);
	}
}
