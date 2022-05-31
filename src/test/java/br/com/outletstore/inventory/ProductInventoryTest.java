package br.com.outletstore.inventory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.rules.ErrorCollector;

import br.com.outletstore.builder.DesktopBuilder;
import br.com.outletstore.builder.NotebookBuilder;
import br.com.outletstore.director.Catalog;
import br.com.outletstore.exceptions.InventoryException;

@TestMethodOrder(OrderAnnotation.class)
class ProductInventoryTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();

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
		builderB.setBrand("Pc Gamer");
		builderB.setType("AORUS");
		builderB.build();
		ProductInventory.addCatalogToInventory(catalog);

		// Verification
		assertThat(inventory.getProductStock(1), is(equalTo(10)));
		assertThat(inventory.getProductStock(2), is(equalTo(10)));
	}

	@Test
	@Order(2) 
	void checksProductsReserved() throws InventoryException {
		// Action
		inventory.removeProductFromStock(1, 5);
		inventory.removeProductFromStock(2, 5);
		
		
		// Verification
		assertThat(inventory.getProductStock(1), is(equalTo(5)));
		assertThat(inventory.getProductStock(2), is(equalTo(5)));
		// StockReserved
		assertThat(inventory.getStockReserved(1), is(equalTo(5)));
		assertThat(inventory.getStockReserved(2), is(equalTo(5)));
	}

	@Test
	@Order(3) 
	void checksProductsReservedCantBeLargerThanStock() throws InventoryException {
		// Verification
		try {
			inventory.blockProductFromStock(1, 11);
			Assert.fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Quantity to remove larger than stock"));
		}

		try {
			inventory.blockProductFromStock(2, 11);
			Assert.fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Quantity to remove larger than stock"));
		}
  }
	
	@Test
	@Order(4) 
	void checkAddingCatalogToInventory() {
		catalog.getAllProducts();
		assertThat(inventory.getInventory().size(), is(equalTo(2)));
	}
	
	@Test
	@Order(5) 
	void checkReturningMoreThanStockReserved() throws InventoryException {
		//Verification

			inventory.returnProductsToStock(1);
			assertThat(inventory.getProductStock(1),
					is(equalTo(inventory.getProductStock(1) + inventory.getStockReserved(1))));

			inventory.returnProductsToStock(2);
			assertThat(inventory.getProductStock(2),
					is(equalTo(inventory.getProductStock(2) + inventory.getStockReserved(2))));
	}
	
	
}
