package ru.gribnoff.l1.t3;

class Triangle implements Figure {
	private final int side1;
	private final int side2;
	private final int angle;

	public Triangle(int side1, int side2, int angle) {
		this.side1 = side1;
		this.side2 = side2;
		this.angle = angle;
	}

	@Override
	public void draw() {
		System.out.printf("Triangle with sides %d and %d and angle %d is drawn%n", side1, side2, angle);
	}
}
