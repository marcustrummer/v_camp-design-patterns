package br.com.outletstore.builder;

import br.com.outletstore.entity.Product;


//The builder interface specifies methods used to create the different parts of product objects.
public interface IProductBuilder {
	
	IProductBuilder setSku(int sku);
	IProductBuilder setPrice(double price);
	IProductBuilder setWeight(double weight);
	

	Product build();
	
	Product getProduct();

}
