package br.com.outletstore.builder;

import br.com.outletstore.entity.Notebook;

public class NotebookBuilder implements IProductBuilder{
	
	//id of the product
	public int sku;
	
	public double price;
	
	public double weight;
	
	public int stockQuantity;
	
	public int stockReserved;
	
	public String brand;
	
	public String type;
	
	private Notebook notebook;

	@Override
	public IProductBuilder setSku(int sku) {
		this.sku = sku;
		return this;
	}

	@Override
	public IProductBuilder setPrice(double price) {
		this.price = price;
		return this;
	}

	@Override
	public IProductBuilder setWeight(double weight) {
		this.weight = weight;
		return this;
	}


	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public void setStockReserved(int stockReserved) {
		this.stockReserved = stockReserved;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public Notebook build() {
		Notebook notebook = new Notebook();
		notebook.sku = this.sku;
		notebook.price = this.price;
		notebook.weight = this.weight;
		notebook.stockQuantity = this.stockQuantity;
		notebook.stockReserved = this.stockReserved;
		notebook.type = this.type;
		notebook.brand = this.brand;
		
		return notebook;
	}

	@Override
	public Notebook getProduct() {
		return notebook;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	

}
