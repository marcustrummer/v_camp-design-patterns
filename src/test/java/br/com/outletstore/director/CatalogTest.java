package br.com.outletstore.director;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.outletstore.builder.DesktopBuilder;
import br.com.outletstore.builder.NotebookBuilder;
import br.com.outletstore.inventory.ProductInventory;

class CatalogTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	static ProductInventory inventory = ProductInventory.getInstance();
	static Catalog catalog = new Catalog();
	static NotebookBuilder builder = new NotebookBuilder();
	static DesktopBuilder builderB = new DesktopBuilder();

	@BeforeAll
	static void setup() {
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
	}


	@Test
	public void notebookBuilderTest() {
		// Action

		
		// Verification
		Assertions.assertEquals(1, catalog.getAllProducts().get(0).getSku());
		assertEquals(12000, catalog.getAllProducts().get(0).getPrice());
		assertEquals(4500, catalog.getAllProducts().get(0).getWeight());
		assertEquals("Dell", builder.getProduct().getBrand());
		assertEquals("G1511", builder.getProduct().getType());
	}
	
	@Test
	public void desktopBuilderTest() {
		// Action

		// Verification
		assertEquals(2, catalog.getAllProducts().get(1).getSku());
		assertEquals(6800, catalog.getAllProducts().get(1).getPrice());
		assertEquals(5500, catalog.getAllProducts().get(1).getWeight());
		assertEquals("Pc Gamer", builderB.getProduct().getCpu());
		assertEquals("AORUS", builderB.getProduct().getMonitor());
		
		System.out.println(catalog.getAllProducts());
		
	}
	
	@Test
	public void checkIfAllProductsWereAdded() {
		// Verification
		assertEquals(2, catalog.getAllProducts().size());
	}
	
	
}
