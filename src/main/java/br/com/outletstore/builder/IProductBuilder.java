package br.com.outletstore.builder;

import br.com.outletstore.entity.Product;

public interface IProductBuilder {
	
	IProductBuilder setSku(int sku);
	IProductBuilder setPrice(double price);
	IProductBuilder setWeight(double weight);
	
	Product build();
	
	Product getProduct();

}
