package br.com.outletstore.inventory;

import java.util.ArrayList;
import java.util.List;


import br.com.outletstore.director.Catalog;
import br.com.outletstore.entity.Product;

public class ProductInventory {
	

	// The field storing the instance of singleton must be declared as static.
	private static ProductInventory productInventory;
	

	private static int notebookStock = 10;
	
	private static int desktopStock = 10;
	
	private static int notebooksReserved = 0;
	
	private static int desktopsReserved = 0;
	
	
	
	private static List<Product> inventory = new ArrayList<>();

	
	// The static method that gives access to the singleton
	public static ProductInventory getInstance() {
		// Makes sure the instance of singleton 
		// wasn't initialized by another thread yet
        if (productInventory == null) {
        	productInventory = new ProductInventory(notebookStock, desktopStock, inventory);
        }
        return productInventory;
    }
		
	// The constructor of the singleton must always be declared as
	// private to make sure noone can make new instances of the singleton
	// by using the operator `new` 
	// i.e, ProductIventory productInventory = new ProductInventory(); 
	private ProductInventory(int notebookStock, int desktopStock, List<Product> inventory) {
		ProductInventory.notebookStock = notebookStock;
		ProductInventory.desktopStock = desktopStock;
		ProductInventory.inventory = inventory;
	}
	
	
	
	public List<Product> getInventory() {
		ProductInventory.getInstance();
		return ProductInventory.inventory;
	}
	
	
	public static void addCatalogToInventory(Catalog catalog) {
		for(Product product: catalog.getAllProducts()) {
			inventory.add(product);
		}
	}
	
	public int getProductStock(int sku) {
		int stock = 0;
		switch(sku) {
		case 1: stock = ProductInventory.notebookStock;
		        System.out.println("There are currently " + stock + " notebooks available in stock.");
		        break;        
		case 2: stock = ProductInventory.desktopStock;
		        System.out.println("There are currently " + stock + " desktops available in stock.");
		        break;  
		default: 
			System.out.println("404 - Product Not Found");
		}
		return stock;
	}
	
	public void returnProductToStock(int sku, int quantity) {
		switch(sku) {
		case 1:
			// Must check if the quantity being returned is equal to the quantity reserved
			
			ProductInventory.notebookStock += quantity;
			
			System.out.println("Notebook returned.\n" 
			                 + ProductInventory.notebookStock + " notebooks available in stock.");
			break;
		case 2:
			// Must check if the quantity being returned is equal to the quantity reserved
			ProductInventory.desktopStock += quantity;
			break;
		default:
			System.out.println("404 - Product Not Found");
		}
	}
	
	public void removeProductFromStock(int sku, int quantity) {
		switch(sku) {
		case 1:
			if(quantity > ProductInventory.notebookStock) {
				System.out.println("The amount of notebooks you are trying to remove is larger than stock.\n" 
		                 + ProductInventory.notebookStock + " products available in stock.");
				break;
			}
			ProductInventory.notebookStock -= quantity;
			blockProductFromStock(sku, quantity);
			System.out.println("Notebook removed from stock.\n" 
	                 + ProductInventory.notebookStock + " notebooks available in stock.");
			break;
		case 2: 
			ProductInventory.desktopStock -= quantity;
			blockProductFromStock(sku, quantity);
			System.out.println("Desktop(s) removed.\n" 
	                 + ProductInventory.notebookStock + " desktops available in stock.");
			break;
		default:
			System.out.println("404 - Product not found");
		}

	}
	
	public void blockProductFromStock(int sku, int quantity) {
		switch(sku) {
		case 1:
			ProductInventory.notebooksReserved += quantity;
			System.out.println(ProductInventory.notebooksReserved + " notebooks reserved");
			break;
		case 2: 
			ProductInventory.desktopsReserved += quantity;
			System.out.println(ProductInventory.notebooksReserved + " desktops reserved");
		default:
			System.out.println("404 - Product not found");
		}
	}
	
	
	public int getStockReserved(int sku) {
		int stock = 0;
		switch(sku) {
		case 1: stock = ProductInventory.notebooksReserved;
		        System.out.println("There are currently " + stock + " notebooks reserved");
		        break;        
		case 2: stock = ProductInventory.desktopsReserved;
		        System.out.println("There are currently " + stock + " desktops reserved");
		        break;  
		default: 
			System.out.println("404 - Product Not Found");
		}
		return stock;
	}
	
	
	
}
