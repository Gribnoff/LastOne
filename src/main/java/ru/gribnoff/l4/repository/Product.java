package ru.gribnoff.l4.repository;

@DbTable(name = "products")
public class Product {
	@DbId
	private Long id;

	@DbField
	private String title;

	@DbField
	private double price;

	public Product() {
	}

	public Product(Long id, String title, double price) {
		this.id = id;
		this.title = title;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", title='" + title + '\'' +
				", price=" + price +
				'}';
	}
}
