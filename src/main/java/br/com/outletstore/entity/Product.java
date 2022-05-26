package br.com.outletstore.entity;

public class Product {
	
	public int sku;
	
	public double price;
	
	public double weight;

	public int stockQuantity;
	
	public int stockReserved;

	
	public Product() {}
	
	
	
	//CONSTRUCTOR	
	public Product(int sku, double price, double weight, int stockQuantity, int stockReserved) {
		super();
		this.sku = sku;
		this.price = price;
		this.weight = weight;
		this.stockQuantity = stockQuantity;
		this.stockReserved = stockReserved;
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

	public int getStockQuantity() {
		return stockQuantity;
	}

	public int getStockReserved() {
		return stockReserved;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public void setStockReserved(int stockReserved) {
		this.stockReserved = stockReserved;
	}


	


	
	

}
