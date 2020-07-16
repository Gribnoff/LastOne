package ru.gribnoff.l1.t2;

abstract class Car {
	private Engine engine;
	private String color;
	private String name;


	protected void start() {
		System.out.println("Car starting");
	}

	protected void open() {
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
