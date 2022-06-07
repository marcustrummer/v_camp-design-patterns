package br.com.outletstore.client;

import java.util.Scanner;

import br.com.outletstore.backoffice.BackOffice;
import br.com.outletstore.cart.Cart;
import br.com.outletstore.cart.CartList;
import br.com.outletstore.director.Catalog;
import br.com.outletstore.inventory.ProductInventory;
import br.com.outletstore.order.Order;
import br.com.outletstore.order.OrderList;
import br.com.outletstore.order.OrderStatus;

public class Client {

	public static void main(String[] args) throws Exception {

		Catalog catalog = new Catalog();
		Cart cart1 = new Cart();
		Cart cart2 = new Cart();
		Cart cart3 = new Cart();
		BackOffice backOffice = new BackOffice();
		OrderList list = OrderList.getInstance();
		// Adding Products to Catalog
		catalog.addProductToCatalog(1);
		catalog.addProductToCatalog(2);
		catalog.addProductToCatalog(3);
		catalog.addProductToCatalog(4);
		ProductInventory.addCatalogToInventory(catalog);
		//Carts
		cart1.setIdCart(0);
		cart2.setIdCart(1);
		cart3.setIdCart(2);
		
		CartList listOfCarts = CartList.getInstance();
		
		listOfCarts.addCartToList(cart1);
		listOfCarts.addCartToList(cart2);
		listOfCarts.addCartToList(cart3);
		
		cart1.addProductToCartById(0, 1, 1);   //ADD 02 NOTEBOOKs(SKU = 1) TO CART1
		cart1.addProductToCartById(0, 3, 1);   //ADD 01 iPad(SKU = 3) TO CART1
		
		cart2.addProductToCartById(1, 4, 1);   //ADD 01 Alexa(SKU=4) TO CART2
		cart2.addProductToCartById(1, 2, 1);  //ADD 01 PC(SKU=2) TO CART2
		
		cart3.addProductToCartById(2, 4, 1);   //ADD 01 Alexa(SKU=4) TO CART3
		cart3.addProductToCartById(2, 3, 1);  //ADD 01 iPad(SKU=3) TO CART3
		
		
		Order order1 = new Order(1, cart1, cart1.getShipping(), OrderStatus.PAID, cart1.getShippingMethod());
		
		Order order2 = new Order(2, cart2, cart2.getShipping(), OrderStatus.PENDING, cart2.getShippingMethod());

		Order order3 = new Order(3, cart3, cart3.getShipping(), OrderStatus.COMPLETED, cart3.getShippingMethod());
		
		list.addObserver(backOffice);
		
		
		list.addOrderToList(order1);
		list.addOrderToList(order2);
		list.addOrderToList(order3);
		
		//UPDATIN ORDERS

		

		System.out.println("UPDATING ORDER...");
		list.updateOrderStatus(order1, 1);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		list.updateOrderStatus(order2, 2);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.updateOrderStatus(order3, 4);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
}
