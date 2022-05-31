package br.com.outletstore.client;

import br.com.outletstore.cart.Cart;
import br.com.outletstore.director.Catalog;
import br.com.outletstore.inventory.ProductInventory;

public class Client {
	
	
	public static void main(String[] args) {
		
		Catalog catalog = new Catalog();
		
		ProductInventory inventory = ProductInventory.getInstance();
		
		

		

		
		//Adding Products to Catalog
		catalog.addProductToCatalog(1);
		catalog.addProductToCatalog(2);
	    System.out.println(catalog.getAllProducts());
		
		//Filling the stock
		ProductInventory.addCatalogToInventory(catalog);
		
		
		//Checking 
		//inventory.getProductStock(1);
		

		//inventory.removeProductFromStock(1, 20);

		//System.out.println(inventory.getInventory());	
		
		
		//Instantiating Carts for simple testing
		
		Cart cart1 = new Cart();
		
		System.out.println("\nADDING PRODUCTS TO CART 1...");
		cart1.addProductToCart(1, 6);
		//cart1.addProductToCart(2, 4);
		System.out.println("\nCART 1");
		System.out.println("Products: " + cart1.getCart());
		

		
		cart1.removeProductFromCart(1, 7);
		
		
		
//		System.out.println(cart1.getShipping());
//		System.out.println("Total Price: " + cart1.getTotalPriceOfTheCart());
//		System.out.println("Total Weight: " + cart1.getTotalWeightOfTheCart());
		
	}

}
