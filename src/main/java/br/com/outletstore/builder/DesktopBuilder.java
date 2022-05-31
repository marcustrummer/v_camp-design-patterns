package br.com.outletstore.builder;

import br.com.outletstore.entity.Desktop;

public class DesktopBuilder implements IProductBuilder{

	
	public int sku;
	
	public double price;
	
	public double weight;
	
	
	public String cpu;
	
	public String monitor;
	
	private Desktop desktop;
	
	

	public DesktopBuilder setSku(int sku) {
		this.sku = sku;
		return this;
	}


	public DesktopBuilder setPrice(double price) {
		this.price = price;
		return this;
	}


	public DesktopBuilder setWeight(double weight) {
		this.weight = weight;
		return this;
	}


	public DesktopBuilder setType(String cpu) {
		this.cpu = cpu;
		return this;
	}
	
	public DesktopBuilder setBrand(String monitor) {
		this.monitor = monitor;
		return this;
	}
	
	
	




	@Override
	public Desktop build() {
		this.desktop = new Desktop();
		desktop.sku = this.sku;
		desktop.price = this.price;
		desktop.weight = this.weight;
		desktop.cpu = this.cpu;
		desktop.monitor = this.monitor;
		return desktop;
	}


	public Desktop getProduct() {
		return desktop;
	}

}
