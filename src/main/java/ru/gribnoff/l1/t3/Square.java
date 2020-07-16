package ru.gribnoff.l1.t3;

class Square implements Figure {
	private final int side;

	public Square(int side) {
		this.side = side;
	}

	@Override
	public void draw() {
		System.out.printf("Square with side %d is drawn%n", side);
	}
}
