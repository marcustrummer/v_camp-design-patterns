package br.com.outletstore.director;

import java.util.ArrayList;
import java.util.List;

import br.com.outletstore.entity.Product;

public class Catalog {
	
	public List<Product> catalog = new ArrayList<>();
	
	public void addProductToCatalog(int typeOfProduct) {
		Director director = new Director();
		switch(typeOfProduct) {		
			case 1:  catalog.add(director.notebookBuilder()); break;
			default: System.out.println("Type of product does not exists");
		}
	}
	
	public List<Product> getAllProducts(){
		return catalog;
	}
	

	
	
	

}
