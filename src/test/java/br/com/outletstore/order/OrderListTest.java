package br.com.outletstore.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.outletstore.backoffice.BackOffice;
import br.com.outletstore.builder.DesktopBuilder;
import br.com.outletstore.builder.NotebookBuilder;
import br.com.outletstore.cart.Cart;
import br.com.outletstore.cart.CartList;
import br.com.outletstore.director.Catalog;
import br.com.outletstore.exceptions.InventoryException;
import br.com.outletstore.exceptions.OrderException;
import br.com.outletstore.exceptions.ShippingException;
import br.com.outletstore.inventory.ProductInventory;

class OrderListTest {

	static Cart cart1 = new Cart();
	
	static Cart cart2 = new Cart();
	
	static CartList listOfCarts = CartList.getInstance();

	static ProductInventory inventory = ProductInventory.getInstance();
	static Catalog catalog = new Catalog();
	static NotebookBuilder builder = new NotebookBuilder();
	static DesktopBuilder builderB = new DesktopBuilder();
	OrderList list = OrderList.getInstance();

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
		
		cart1.addProductToCartById(0, 1, 2);   //ADD 01 NOTEBOOK(SKU = 1) TO CART1
		cart1.addProductToCartById(0, 2, 1);   //ADD 01 NOTEBOOK(SKU = 1) TO CART1
		cart2.addProductToCartById(1, 2, 1);   //ADD 01 PC(SKU=2) TO CART2
		
	}
	
	
	@Test
	void addOrderToListTest() throws ShippingException {
		//Action
		
		Order order1 = new Order(1, cart1, cart1.getShipping(), OrderStatus.PAID, cart1.getShippingMethod());
		
		Order order2 = new Order(2, cart2, cart2.getShipping(), OrderStatus.PENDING, cart2.getShippingMethod());
		
		//Verify
		   //CART1
		assertEquals(1, order1.getId());
		assertEquals(cart1, order1.getCart());
		assertEquals(OrderStatus.PAID, order1.getStatus());
		assertEquals("Road", cart1.getShippingMethod());
		
		  //CART2
		assertEquals(2, order2.getId());
		assertEquals(cart2, order2.getCart());
		assertEquals(OrderStatus.PENDING, order2.getStatus());
		assertEquals("Aero", cart2.getShippingMethod());
	}
	
	@Test
	void addOrderToListTestFail() throws ShippingException {
		//Action	
		Order order1 = new Order(1, cart1, cart1.getShipping(), OrderStatus.PAID, cart1.getShippingMethod());
		Order order2 = new Order(2, cart2, cart2.getShipping(), OrderStatus.PENDING, cart2.getShippingMethod());
		//Verify
		   //CART1
		
		assertNotEquals(1, order2.getId());
		assertNotEquals(cart1, order2.getCart());
		assertNotEquals(OrderStatus.PAID, order2.getStatus());
		assertNotEquals("Road", cart2.getShippingMethod());
		
		  //CART2
		assertNotEquals(2, order1.getId());
		assertNotEquals(cart2, order1.getCart());
		assertNotEquals(OrderStatus.PENDING, order1.getStatus());
		assertNotEquals("Aero", cart1.getShippingMethod());
	}
	
	@Test
	void testAddingOrderCanceledToList() throws ShippingException {
		//Action	
		Order order1 = new Order(1, cart1, cart1.getShipping(), OrderStatus.CANCELED, cart1.getShippingMethod());
		OrderException exception = Assertions.assertThrows(OrderException.class, ()-> list.addOrderToList(order1));
		Assertions.assertEquals("Order canceled and cannot be added to list", exception.getMessage());
	}
	
	@Test
	void testRemovingOrderCanceledToList() throws ShippingException {
		//Action	
		Order order1 = new Order(1, cart1, cart1.getShipping(), OrderStatus.CANCELED, cart1.getShippingMethod());
		OrderException exception = Assertions.assertThrows(OrderException.class, ()-> list.addOrderToList(order1));
		Assertions.assertEquals("Order canceled and cannot be added to list", exception.getMessage());
	}
	
	@Test
	void testAddingOrderNullToList() throws ShippingException, OrderException {
		//Verify	
		Order order1 = null;
		OrderException exception = Assertions.assertThrows(OrderException.class, ()-> list.addOrderToList(order1));
		Assertions.assertEquals("Order is null!", exception.getMessage());
	}
	
	
	@Test
	void testRemovingOrderNullToList() throws ShippingException, OrderException {
		//Action
		Order order1 = null;
		Order order2 = new Order(2, cart2, cart2.getShipping(), OrderStatus.PENDING, cart2.getShippingMethod());
		list.addOrderToList(order2);
		
		//Verify	
		OrderException exception = Assertions.assertThrows(OrderException.class, ()-> list.RemoveOrderFromList(order1));
		Assertions.assertEquals("Order is null!", exception.getMessage());
	}
	
	@Test
	void getOrderPriceByIdTest() throws ShippingException, OrderException{
		
		//Action
		Order order1 = new Order(1, cart1, cart1.getShipping(), OrderStatus.PENDING, cart1.getShippingMethod());
		list.addOrderToList(order1);
		
		

		//Verify
		assertEquals(33883.0, order1.getTotalPrice());
		assertEquals("Order price: 33883.0", list.getOrderPriceById(1));
		OrderException exception = Assertions.assertThrows(OrderException.class, ()-> list.getOrderPriceById(2));
		Assertions.assertEquals("Order not found", exception.getMessage());
	}
	
	@Test
	void getOrderShippingByIdTest() throws ShippingException, OrderException{
		
		//Action
		Order order1 = new Order(1, cart1, cart1.getShipping(), OrderStatus.PENDING, cart1.getShippingMethod());
		list.addOrderToList(order1);
		//Verify
		assertEquals(3083.0, order1.getShipping());
		assertEquals("Order shipping price: 3083.0", list.getOrderShippingById(1));
		OrderException exception = Assertions.assertThrows(OrderException.class, ()-> list.getOrderShippingById(2));
		Assertions.assertEquals("Order not found", exception.getMessage());
	}
	
	@Test
	void addOberverTest() throws ShippingException, OrderException{
		
		//Action
		Order order1 = new Order(1, cart1, cart1.getShipping(), OrderStatus.PENDING, cart1.getShippingMethod());
		list.addOrderToList(order1);
		
		BackOffice office = new BackOffice();
		
		//Verify
		
		list.addObserver(office);
		OrderException exception = Assertions.assertThrows(OrderException.class, ()-> list.addObserver(null));
		Assertions.assertEquals("Observer cannot be null!", exception.getMessage());
	}
	
	@Test
	void removeOberverTest() throws ShippingException, OrderException{
		
		//Action
		Order order1 = new Order(1, cart1, cart1.getShipping(), OrderStatus.PENDING, cart1.getShippingMethod());
		list.addOrderToList(order1);
		
		BackOffice office = new BackOffice();
		
		//Verify
		
		list.addObserver(office);
		OrderException exception = Assertions.assertThrows(OrderException.class, ()-> list.removeObserver(null));
		Assertions.assertEquals("Observer cannot be null!", exception.getMessage());
	}
	
	
	
	
	
	

	
	

}


