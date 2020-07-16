package ru.gribnoff.l1.t2;

class Lorry extends Car implements Moveable, Stoppable {
	public void move(){
		System.out.println("Car is moving");
	}

	public void stop(){
		System.out.println("Car is stop");
	}
}
