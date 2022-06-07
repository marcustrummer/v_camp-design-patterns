package br.com.outletstore.director;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.outletstore.builder.AlexaBuilder;
import br.com.outletstore.builder.DesktopBuilder;
import br.com.outletstore.builder.NotebookBuilder;
import br.com.outletstore.builder.iPadBuilder;
import br.com.outletstore.exceptions.InventoryException;
import br.com.outletstore.inventory.ProductInventory;

class CatalogTest {

	static ProductInventory inventory = ProductInventory.getInstance();
	static Catalog catalog = new Catalog();
	static NotebookBuilder builder = new NotebookBuilder();
	static DesktopBuilder builderB = new DesktopBuilder();
	static iPadBuilder builderC = new iPadBuilder();
	static AlexaBuilder builderD = new AlexaBuilder();
	

	@BeforeAll
	static void setup() throws InventoryException {
		// Action

		catalog.addProductToCatalog(1);
		builder.setBrand("Dell");
		builder.setType("G1511");
		builder.build();

		catalog.addProductToCatalog(2);
		builderB.setCpu("Pc Gamer");
		builderB.setMonitor("AORUS");
		builderB.build();
		
		catalog.addProductToCatalog(3);
		builderC.setMemory("8gb");
		builderC.setVersion("iPadMini");
		builderC.build();
		
		catalog.addProductToCatalog(4);
		builderD.setColor("Black");
		builderD.setVoice("Male");
		builderD.build();
		ProductInventory.addCatalogToInventory(catalog);
	}


	@Test
	public void notebookBuilderTest() {
		// Verify
		Assertions.assertEquals(1, catalog.getAllProducts().get(0).getSku());
		assertEquals(12000, catalog.getAllProducts().get(0).getPrice());
		assertEquals(4.5, catalog.getAllProducts().get(0).getWeight());
		assertEquals("Dell", builder.getProduct().getBrand());
		assertEquals("G1511", builder.getProduct().getType());
		
	}
	
	@Test
	public void desktopBuilderTest() {
		// Verify
		assertEquals(2, catalog.getAllProducts().get(1).getSku());
		assertEquals(6800, catalog.getAllProducts().get(1).getPrice());
		assertEquals(5.5, catalog.getAllProducts().get(1).getWeight());
		assertEquals("Pc Gamer", builderB.getProduct().getCpu());
		assertEquals("AORUS", builderB.getProduct().getMonitor());
		
		System.out.println(catalog.getAllProducts());
		
	}
	@Test
	public void iPadBuilderTest() {
		// Verify
		assertEquals(3, catalog.getAllProducts().get(2).getSku());
		assertEquals(2800, catalog.getAllProducts().get(2).getPrice());
		assertEquals(0.8, catalog.getAllProducts().get(2).getWeight());
		assertEquals("8gb", builderC.getProduct().getMemory());
		assertEquals("iPadMini", builderC.getProduct().getVersion());
		
		System.out.println(catalog.getAllProducts());
		
	}
	@Test
	public void alexaBuilderTest() {
		// Verify
		assertEquals(4, catalog.getAllProducts().get(3).getSku());
		assertEquals(300, catalog.getAllProducts().get(3).getPrice());
		assertEquals(0.3, catalog.getAllProducts().get(3).getWeight());
		assertEquals("Black", builderD.getProduct().getColor());
		assertEquals("Male", builderD.getProduct().getVoice());
		
		System.out.println(catalog.getAllProducts());
		
	}
	
	@Test
	public void checkIfAllProductsWereAdded() {
		// Verify
		assertEquals(4, catalog.getAllProducts().size());
	}
	
	@Test
	public void checkAddingProductToCatalog() {
		//Verify
		InventoryException exception = Assertions.assertThrows(InventoryException.class,
				() -> catalog.addProductToCatalog(0));
		Assertions.assertEquals("Product not found!", exception.getMessage());
	}
	
	
	
}
