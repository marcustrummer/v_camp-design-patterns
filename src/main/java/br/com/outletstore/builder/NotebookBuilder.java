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


	public NotebookBuilder setSku(int sku) {
		this.sku = sku;
		return this;
	}


	public NotebookBuilder setPrice(double price) {
		this.price = price;
		return this;
	}


	public NotebookBuilder setWeight(double weight) {
		this.weight = weight;
		return this;
	}
	

	public NotebookBuilder setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
		return this;
	}

	public NotebookBuilder setStockReserved(int stockReserved) {
		this.stockReserved = stockReserved;
		return this;
	}



	public NotebookBuilder setType(String type) {
		this.type = type;
		return this;
	}
	
	public NotebookBuilder setBrand(String brand) {
		this.brand = brand;
		return this;
		
	}


	@Override
	public Notebook build() {
		this.notebook = new Notebook();
		notebook.sku = this.sku;
		notebook.price = this.price;
		notebook.weight = this.weight;
		notebook.stockQuantity = this.stockQuantity;
		notebook.stockReserved = this.stockReserved;
		notebook.brand = this.brand;
		notebook.type = this.type;
		return notebook;
	}

	


	public Notebook getProduct() {
		return notebook;
	}





	
	
	
	
	
	

	
	
	
	
	
	
	
	

}
