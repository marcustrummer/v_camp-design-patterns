package br.com.outletstore.entity;

public class Product {
	
	public int sku;
	
	public double price;
	
	public double weight;

	public Product() {}
	
	
	
	//CONSTRUCTOR	
	public Product(int sku, double price, double weight) {
		super();
		this.sku = sku;
		this.price = price;
		this.weight = weight;
	}
	
	
	

	//GETTERS AND SETTERS	
	public int getSku() {
		return sku;
	}

	public double getPrice() {
		return price;
	}

	public double getWeight() {
		return weight;
	}


}
