package br.com.outletstore.inventory;

import java.util.ArrayList;
import java.util.List;

import br.com.outletstore.director.Catalog;
import br.com.outletstore.entity.Product;
import br.com.outletstore.exceptions.InventoryException;

public class ProductInventory {

	// The field storing the instance of singleton must be declared as static.
	private static ProductInventory productInventory;

	private static int notebookStock =10;

	private static int desktopStock = 10;

	private static int notebooksReserved = 0;

	private static int desktopsReserved = 0;

	private static List<Product> inventory = new ArrayList<>();

	// The static method that gives access to the singleton
	public static ProductInventory getInstance() {
		// Makes sure the instance of singleton
		// wasn't initialized by another thread yet
		if (productInventory == null) {
			productInventory = new ProductInventory(notebookStock, desktopStock, inventory, notebooksReserved, desktopsReserved);
		}
		return productInventory;
	}

	// The constructor of the singleton must always be declared as
	// private to make sure noone can make new instances of the singleton
	// by using the operator `new`
	// i.e, ProductIventory productInventory = new ProductInventory();
	private ProductInventory(int notebookStock, int desktopStock, List<Product> inventory, int notebooksReserved, int desktopsReserved) {
		ProductInventory.notebookStock = notebookStock;
		ProductInventory.desktopStock = desktopStock;
		ProductInventory.inventory = inventory;
		ProductInventory.notebooksReserved = notebooksReserved;
		ProductInventory.desktopsReserved = desktopsReserved;
	}
	
	public static void addCatalogToInventory(Catalog catalog) {
		for (Product product : catalog.getAllProducts()) {
			inventory.add(product);
		}
	}
	
	
	

	public List<Product> getInventory() {
		ProductInventory.getInstance();
		return ProductInventory.inventory;
	}
	
	public int getProductStock(int sku) throws InventoryException {
		int stock = 0;
		switch (sku) {
		case 1:
			stock = ProductInventory.notebookStock;
			break;
		case 2:
			stock = ProductInventory.desktopStock;
			break;
		default:
			throw new InventoryException("Product not found!");
		}
		return stock;
	}


	
	public int getStockReserved(int sku) throws InventoryException {
		int stock = 0;
		switch (sku) {
		case 1:
			stock = ProductInventory.notebooksReserved;

			break;
		case 2:
			stock = ProductInventory.desktopsReserved;
			break;
		default:
			throw new InventoryException("Product not found!");
		}
		return stock;
	}
	



	public void returnProductsToStock(int sku, int quantity) throws InventoryException {
		switch (sku) {
		case 1:
			ProductInventory.notebookStock += quantity;
			ProductInventory.notebooksReserved -= quantity;
			break;
		case 2:
			// Must check if the quantity being returned is equal to the quantity reserved
			ProductInventory.desktopStock += quantity;
			ProductInventory.desktopsReserved -= quantity;
			break;
		default:
			throw new InventoryException("Product not found!");
		}
	}

	public void removeProductFromStock(int sku, int quantity) throws InventoryException {
		switch (sku) {
		case 1:
			if (quantity > ProductInventory.notebookStock) {
				throw new InventoryException("Quantity to remove larger than stock");
			}

			ProductInventory.notebookStock -= quantity;
			blockProductFromStock(sku, quantity);
			break;
		case 2:
			if (quantity > ProductInventory.desktopStock) {
				throw new InventoryException("Quantity to remove larger than stock");
			}
			ProductInventory.desktopStock -= quantity;
			blockProductFromStock(sku, quantity);
			break;
		default:
			throw new InventoryException("Product not found!");
		}

	}

	public void blockProductFromStock(int sku, int quantity) throws InventoryException {
		switch (sku) {
		case 1:
			ProductInventory.notebooksReserved += quantity;
			break;
		case 2:
			ProductInventory.desktopsReserved += quantity;
			break;
		default:
			throw new InventoryException("Product not found!");
		}
	}
	
	public void sellBlockedStock(int sku, int quantity) throws InventoryException {
		switch (sku) {
		case 1:
			ProductInventory.notebooksReserved -= quantity;
			break;
		case 2:
			ProductInventory.desktopsReserved -= quantity;
			break;
		default:
			throw new InventoryException("Product not found!");
		}
	}



}
