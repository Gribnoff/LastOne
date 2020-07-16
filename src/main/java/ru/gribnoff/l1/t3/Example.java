package ru.gribnoff.l1.t3;

import java.util.ArrayList;
import java.util.List;

class Example {
	public static void main(String[] args) {
		List<Figure> figures = new ArrayList<>();
		figures.add(new Circle(10));
		figures.add(new Triangle(5, 2, 60));
		figures.add(new Square(15));

		figures.forEach(Figure::draw);
	}
}
