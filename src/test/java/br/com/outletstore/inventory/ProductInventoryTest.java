package br.com.outletstore.inventory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.outletstore.builder.DesktopBuilder;
import br.com.outletstore.builder.NotebookBuilder;
import br.com.outletstore.director.Catalog;
import br.com.outletstore.exceptions.InventoryException;

@TestMethodOrder(OrderAnnotation.class)
class ProductInventoryTest {



	ProductInventory inventory = ProductInventory.getInstance();
	Catalog catalog = new Catalog();
	NotebookBuilder builder = new NotebookBuilder();
	DesktopBuilder builderB = new DesktopBuilder();

	@Test
	@Order(1) 
	void checkThatAllProductsStartWith10() {
		// Action
		catalog.addProductToCatalog(1);
		builder.setBrand("Dell");
		builder.setType("G1511");
		builder.build();

		catalog.addProductToCatalog(2);
		builderB.setCpu("Pc Gamer");
		builderB.setMonitor("AORUS");
		builderB.build();
		ProductInventory.addCatalogToInventory(catalog);

		// Verification
		assertEquals(10, inventory.getProductStock(1));
		assertEquals(10, inventory.getProductStock(2));
		
	}

	@Test
	@Order(2) 
	void checksProductsReserved() throws InventoryException {
		// Action
		inventory.removeProductFromStock(1, 5);
		inventory.removeProductFromStock(2, 5);
		
		
		// Verification
		assertEquals(5, inventory.getProductStock(1));
		assertEquals(5, inventory.getProductStock(2));
		// StockReserved
		assertEquals(5, inventory.getStockReserved(1));
		assertEquals(5, inventory.getStockReserved(2));
	}

	@Test
	@Order(3) 
	void checksProductsReservedCantBeLargerThanStock() throws InventoryException {
		// Verification
		try {
			inventory.removeProductFromStock(1, 11);
			Assertions.fail();
		} catch (Exception e) {
			assertEquals("Quantity to remove larger than stock", e.getMessage());
		}

		try {
			inventory.removeProductFromStock(2, 11);
			Assertions.fail();
		} catch (Exception e) {
			assertEquals("Quantity to remove larger than stock", e.getMessage());
		}
  }
	
	@Test
	@Order(4) 
	void checkAddingCatalogToInventory() {
		catalog.getAllProducts();
		assertEquals(2, inventory.getInventory().size());
	}
	
	@Test
	@Order(5) 
	void checkReturningMoreThanStockReserved() throws InventoryException {
		//Verification

			inventory.returnProductsToStock(1, 5);
			assertEquals(inventory.getProductStock(1) + inventory.getStockReserved(1) , inventory.getProductStock(1));

			inventory.returnProductsToStock(2, 5);
			assertEquals(inventory.getProductStock(2) + inventory.getStockReserved(2) , inventory.getProductStock(2));
	}
	
	
}
