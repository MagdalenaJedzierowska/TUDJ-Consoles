package com.example.jdbcdemo.domain;

public class Console {

	private int id_console;
	private String name;
	private String brand;
	private int premiere;
	private double price;

	public Console(){
	}

	public Console(String name1, String brand1, int premiere1, double price1) {
		this.name = name1;
		this.brand = brand1;
		this.premiere = premiere1;
		this.price = price1;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPremiere() {
		return premiere;
	}

	public void setPremiere(int premiere) {
		this.premiere = premiere;
	}

	public int getId(){
		return id_console;
	}
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setId(int id_console){
		this.id_console=id_console;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}

	@Override
	public String toString() {
		return "Console [id_console=" + id_console + ", name=" + name + ", brand=" + brand + ", premiere=" + premiere
				+ ", price=" + price + "]";
	}

}
