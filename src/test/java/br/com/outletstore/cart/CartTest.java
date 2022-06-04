package br.com.outletstore.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.outletstore.builder.DesktopBuilder;
import br.com.outletstore.builder.NotebookBuilder;
import br.com.outletstore.director.Catalog;
import br.com.outletstore.exceptions.InventoryException;
import br.com.outletstore.inventory.ProductInventory;

@TestMethodOrder(OrderAnnotation.class)
public class CartTest {
	
	Cart cart1 = new Cart();
	
	Cart cart2 = new Cart();

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
	@Order(1) 
	public void addProductToCartTest() {
		//Verification
		InventoryException exception = Assertions.assertThrows(InventoryException.class, ()-> cart1.addProductToCart(1, 11));
		Assertions.assertEquals("Quantity to remove larger than stock", exception.getMessage());
	}
	
	@Test
	@Order(2) 
	public void removeProductToCartTest() throws InventoryException {
		//Action
		cart1.addProductToCart(1, 7);
		
		//cart2.addProductToCart(1, 1);
		
		cart1.addProductToCart(2, 3);
		//Verify that you cannot return more products than reserved;
		InventoryException exception = Assertions.assertThrows(InventoryException.class, ()-> cart1.removeProductFromCart(1, 8));
		Assertions.assertEquals("Quantity to remove larger than stock", exception.getMessage());
		
		cart1.removeProductFromCart(1, 7);
		cart1.removeProductFromCart(2, 3);
		InventoryException exception2 = Assertions.assertThrows(InventoryException.class, ()-> cart1.removeProductFromCart(2, 4));
		Assertions.assertEquals("Quantity to remove larger than stock", exception2.getMessage());
	}
	
	
	@Test
	@Order(3) 
	void removeProductFromCart() throws InventoryException {
		
		//Actions
		cart1.addProductToCart(1, 7);  // CART : 7
		cart1.removeProductFromCart(1, 5);  // CART: 2
		
		//Product 2
		cart1.addProductToCart(2, 8);   // CART : 10   
		cart1.removeProductFromCart(2, 4);   // CART 6

		
		
		
		//Verify Product 1
		assertEquals(2, inventory.getStockReserved(1));
		assertEquals(8, inventory.getProductStock(1));
		assertEquals(6, cart1.getCart().size());  
		
		//Verify Product 2
		
		assertEquals(4, inventory.getStockReserved(2));
		assertEquals(6, inventory.getProductStock(2));  
		
		cart1.removeProductFromCart(1, 2);
		cart1.removeProductFromCart(2, 4);
		
	}
	
	@Test
	@Order(4) 
	void testCartPrice() throws InventoryException {
		//Action
		cart1.addProductToCart(1, 1);
		cart1.addProductToCart(2, 1);
		//Verify
		assertEquals(18800, cart1.getCartPrice());
		
	}
	
	
	
	@Test
	@Order(5) 
	void testCartWeight() throws InventoryException {
		//Verify
		assertEquals(10, cart1.getCartWeight());
	}
	
	
	
	
	
	
}
