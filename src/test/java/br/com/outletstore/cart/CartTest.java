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
	
	static Cart cart1 = new Cart();
	
	static Cart cart2 = new Cart();
	
	static CartList listOfCarts = CartList.getInstance();

	static ProductInventory inventory = ProductInventory.getInstance();
	static Catalog catalog = new Catalog();
	static NotebookBuilder builder = new NotebookBuilder();
	static DesktopBuilder builderB = new DesktopBuilder();

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
		ProductInventory.addCatalogToInventory(catalog);
		cart1.setIdCart(0);
		
		listOfCarts.addCartToList(cart1);
		listOfCarts.addCartToList(cart2);

		
		cart2.setIdCart(1);
		
	}
	
	@Test
	@Order(1) 
	public void addProductToCartTest2() {
		//Verification
		InventoryException exception = Assertions.assertThrows(InventoryException.class, ()-> cart1.addProductToCartById(1, 1, 11));
		Assertions.assertEquals("Quantity to remove larger than stock", exception.getMessage());
	}
	
	@Test
	@Order(2) 
	public void removeProductToCartTest() throws InventoryException {
		//Action
		
		//CART 1
		cart1.addProductToCartById(0,1, 5);
		cart1.addProductToCartById(0,1, 2 );
		
		//CART 2
		cart2.addProductToCartById(1, 2, 3);
		
		
		//Verify that you cannot return more products than reserved;
		InventoryException exception = Assertions.assertThrows(InventoryException.class, ()-> cart1.removeProductFromCartById(0,1, 8));
		Assertions.assertEquals("Quantity to remove larger than stock", exception.getMessage());
		InventoryException exception2 = Assertions.assertThrows(InventoryException.class, ()-> cart2.removeProductFromCartById(1, 2, 4));
		Assertions.assertEquals("Quantity to remove larger than stock", exception2.getMessage());

		//CLEANING CARTS
		cart1.removeProductFromCartById(0, 1, 7);
		cart2.removeProductFromCartById(1,2, 3);
	}
	
	
	@Test
	@Order(3) 
	void removeProductFromCart() throws InventoryException {
		
		//Actions
		cart1.addProductToCartById(0,1, 7);  // CART : 7
		cart1.removeProductFromCartById(0,1, 5);  // CART: 2
		
		//Product 2
		cart1.addProductToCartById(0,2, 8);   // CART : 10   
		cart1.removeProductFromCartById(0,2, 4);   // CART 6

		
		
		//Verify Product 1
		assertEquals(2, inventory.getStockReserved(1));
		assertEquals(8, inventory.getProductStock(1));
		assertEquals(6, cart1.getCart().size());  
		
		//Verify Product 2
		assertEquals(4, inventory.getStockReserved(2));
		assertEquals(6, inventory.getProductStock(2));  
		
		
		//CLEANING CART
		cart1.removeProductFromCartById(0,1, 2);
		cart1.removeProductFromCartById(0,2, 4);
		
	}
	
	@Test
	@Order(4) 
	void testCartPrice() throws InventoryException {
		//Action
		cart1.addProductToCartById(0,1, 1);
		cart1.addProductToCartById(0,2, 1);
		
		
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
