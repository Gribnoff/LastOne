package ru.gribnoff.l1.t2;

class LightWeightCar extends Car implements Moveable{
	@Override
	public void move() {
		System.out.println("Car is moving");
	}
}
