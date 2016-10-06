package com.yc.spring.day03;

public class Phone {
	private String brand;
	private String color;
	private int price;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Phone [brand=" + brand + ", color=" + color + ", price="
				+ price + "]";
	}

	public Phone() {
		System.out.println("phone实例化成功");
	}

}
