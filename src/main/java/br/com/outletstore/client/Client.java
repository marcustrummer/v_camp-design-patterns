package br.com.outletstore.client;

import br.com.outletstore.cart.Cart;
import br.com.outletstore.director.Catalog;
import br.com.outletstore.exceptions.InventoryException;
import br.com.outletstore.inventory.ProductInventory;

public class Client {
	
	
	public static void main(String[] args) throws InventoryException {
		
		Catalog catalog = new Catalog();
		
		ProductInventory inventory = ProductInventory.getInstance();

		//Adding Products to Catalog
		catalog.addProductToCatalog(1);

		ProductInventory.addCatalogToInventory(catalog);
		//Filling the stock
		
		Cart cart1 = new Cart();
		
		

		cart1.addProductToCart(1,11);
		


		
		

		
	}

}
