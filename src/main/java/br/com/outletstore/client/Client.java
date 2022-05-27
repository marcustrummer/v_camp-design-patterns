package br.com.outletstore.client;

import br.com.outletstore.director.Catalog;
import br.com.outletstore.inventory.ProductInventory;

public class Client {
	
	
	public static void main(String[] args) {
		
		Catalog catalog = new Catalog();
		
		ProductInventory inventory = ProductInventory.getInstance();
		
		

		

		
		//Adding Products to Catalog
		catalog.addProductToCatalog(1);
		catalog.addProductToCatalog(2);

		//System.out.println(catalog.getAllProducts());
		
		//Filling the stock
		ProductInventory.addCatalogToInventory(catalog);
		
		
		//Checking 
		inventory.getProductStock(1);
		

		inventory.removeProductFromStock(1, 20);

		System.out.println(inventory.getInventory());		
	}

}
