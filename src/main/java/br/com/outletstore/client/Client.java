package br.com.outletstore.client;

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
		BackOffice backOffice = new BackOffice();
		OrderList list = OrderList.getInstance();
		// Adding Products to Catalog
		catalog.addProductToCatalog(1);
		catalog.addProductToCatalog(2);
		ProductInventory.addCatalogToInventory(catalog);
		//Carts
		cart1.setIdCart(0);
		cart2.setIdCart(1);
		
		CartList listOfCarts = CartList.getInstance();
		
		listOfCarts.addCartToList(cart1);
		listOfCarts.addCartToList(cart2);
		
		cart1.addProductToCartById(0, 1, 2);   //ADD 01 NOTEBOOK(SKU = 1) TO CART1
		cart1.addProductToCartById(0, 2, 1);   //ADD 01 NOTEBOOK(SKU = 1) TO CART1
		cart2.addProductToCartById(1, 2, 1);   //ADD 01 PC(SKU=2) TO CART2
		
		Order order1 = new Order(1, cart1, cart1.getShipping(), OrderStatus.PAID, cart1.getShippingMethod());
		
		Order order2 = new Order(2, cart2, cart2.getShipping(), OrderStatus.PENDING, cart2.getShippingMethod());

		
		list.addObserver(backOffice);
		
		
		list.addOrderToList(order1);
		list.addOrderToList(order2);
		
		
		
		
		
		
		
		
		
		


		


	}
}
