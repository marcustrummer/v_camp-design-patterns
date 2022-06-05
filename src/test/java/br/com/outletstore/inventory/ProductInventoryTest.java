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
	void checkThatAllProductsStartWith10() throws InventoryException {
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
		InventoryException exception = Assertions.assertThrows(InventoryException.class,
				() -> inventory.getProductStock(0));
		Assertions.assertEquals("Product not found!", exception.getMessage());
		exception = Assertions.assertThrows(InventoryException.class, () -> inventory.blockProductFromStock(0, 0));
		Assertions.assertEquals("Product not found!", exception.getMessage());
		exception = Assertions.assertThrows(InventoryException.class, () -> inventory.getProductStock(0));
		Assertions.assertEquals("Product not found!", exception.getMessage());
		exception = Assertions.assertThrows(InventoryException.class, () -> inventory.getStockReserved(0));
		Assertions.assertEquals("Product not found!", exception.getMessage());
		exception = Assertions.assertThrows(InventoryException.class, () -> inventory.returnProductsToStock(0, 0));
		Assertions.assertEquals("Product not found!", exception.getMessage());
		exception = Assertions.assertThrows(InventoryException.class, () -> inventory.sellBlockedStock(0, 0));
		Assertions.assertEquals("Product not found!", exception.getMessage());
		exception = Assertions.assertThrows(InventoryException.class, () -> inventory.removeProductFromStock(0, 0));
		Assertions.assertEquals("Product not found!", exception.getMessage());
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
		InventoryException exception = Assertions.assertThrows(InventoryException.class,
				() -> inventory.removeProductFromStock(1, 11));
		Assertions.assertEquals("Quantity to remove larger than stock", exception.getMessage());

		exception = Assertions.assertThrows(InventoryException.class, () -> inventory.removeProductFromStock(2, 11));
		Assertions.assertEquals("Quantity to remove larger than stock", exception.getMessage());
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
		// Verification

		inventory.returnProductsToStock(1, 5);
		assertEquals(inventory.getProductStock(1) + inventory.getStockReserved(1), inventory.getProductStock(1));

		inventory.returnProductsToStock(2, 5);
		assertEquals(inventory.getProductStock(2) + inventory.getStockReserved(2), inventory.getProductStock(2));
	}

	@Test
	@Order(6)
	void checkSellingStock() throws InventoryException {
		// Verification

		inventory.removeProductFromStock(1, 5);
		inventory.removeProductFromStock(2, 5);

		inventory.sellBlockedStock(1, 5);
		inventory.sellBlockedStock(2, 5);
		assertEquals(0, inventory.getStockReserved(1));
		assertEquals(0, inventory.getStockReserved(2));
		assertEquals(inventory.getProductStock(2) + inventory.getStockReserved(2), inventory.getProductStock(2));
	}

}
