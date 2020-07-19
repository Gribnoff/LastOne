package ru.gribnoff.l1.t2;

abstract class Car {
	protected Engine engine;
	protected String color;
	protected String name;


	public void start() {
		System.out.println("Car starting");
	}

	public void open() {
		System.out.println("Car is open");
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
