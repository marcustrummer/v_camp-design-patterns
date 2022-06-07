package br.com.outletstore.builder;

import br.com.outletstore.entity.iPad;

public class iPadBuilder implements IProductBuilder{

	
	
	
	//id of the product
	public int sku;
	
	public double price;
	
	public double weight;
	
	public String memory;
	
	public String version;
	
	private iPad iPad;


	public iPadBuilder setSku(int sku) {
		this.sku = sku;
		return this;
	}


	public iPadBuilder setPrice(double price) {
		this.price = price;
		return this;
	}


	public iPadBuilder setWeight(double weight) {
		this.weight = weight;
		return this;
	}
	


	public iPadBuilder setMemory(String memory) {
		this.memory = memory;
		return this;
	}
	
	public iPadBuilder setVersion(String version) {
		this.version = version;
		return this;
		
	}


	@Override
	public iPad build() {
		this.iPad = new iPad();
		iPad.sku = this.sku;
		iPad.price = this.price;
		iPad.weight = this.weight;
		iPad.memory = this.memory;
		iPad.version = this.version;
		return iPad;
	}
	
	public iPad getProduct() {
		return iPad;
	}

}
