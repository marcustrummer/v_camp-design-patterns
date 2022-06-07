package br.com.outletstore.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.outletstore.builder.DesktopBuilder;
import br.com.outletstore.builder.NotebookBuilder;
import br.com.outletstore.cart.Cart;
import br.com.outletstore.cart.CartList;
import br.com.outletstore.director.Catalog;
import br.com.outletstore.exceptions.ShippingException;
import br.com.outletstore.inventory.ProductInventory;
import br.com.outletstore.order.OrderList;

class ShippingTest {
	static Cart cart1 = new Cart();
	
	static Cart cart2 = new Cart();
	
	static CartList listOfCarts = CartList.getInstance();

	static ProductInventory inventory = ProductInventory.getInstance();
	static Catalog catalog = new Catalog();
	static NotebookBuilder builder = new NotebookBuilder();
	static DesktopBuilder builderB = new DesktopBuilder();
	OrderList list = OrderList.getInstance();
	

	@BeforeAll
	static void setup() throws Exception {
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
		
		cart1.addProductToCartById(0, 1, 2);   //ADD 01 NOTEBOOK(SKU = 1) TO CART1
		cart1.addProductToCartById(0, 2, 1);   //ADD 01 NOTEBOOK(SKU = 1) TO CART1
		cart2.addProductToCartById(1, 2, 1);   //ADD 01 PC(SKU=2) TO CART2
	}
	
	@Test
	public void getPriceShippingRoad() throws ShippingException {
		//Verify
		assertEquals(3083.0, cart1.getShipping());
		assertEquals("Road", cart1.getShippingMethod());
	}
	
	@Test
	public void getPriceShippingAero() throws ShippingException {
		//Verify
		assertEquals(681.0, cart2.getShipping());
		assertEquals("Aero", cart2.getShippingMethod());
	}
	
	@Test
	public void checksCartWithoutMinumValue() throws ShippingException {
		//Verify
		Cart cart = new Cart();
		
		
		ShippingException exception = Assertions.assertThrows(ShippingException.class, ()-> cart.getShipping());
		Assertions.assertEquals("Minimum value not reached", exception.getMessage());
	}
	
	
	


}
