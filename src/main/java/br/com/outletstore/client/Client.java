package br.com.outletstore.client;

import br.com.outletstore.backoffice.BackOffice;
import br.com.outletstore.cart.Cart;
import br.com.outletstore.director.Catalog;
import br.com.outletstore.inventory.ProductInventory;
import br.com.outletstore.order.Order;
import br.com.outletstore.order.OrderList;
import br.com.outletstore.order.OrderStatus;

public class Client {

	public static void main(String[] args) throws Exception {

		Catalog catalog = new Catalog();
		Cart cart = new Cart();
		Cart cart2 = new Cart();
		BackOffice backOffice = new BackOffice();
		OrderList list = OrderList.getInstance();
		// Adding Products to Catalog
		catalog.addProductToCatalog(1);
		catalog.addProductToCatalog(2);
		ProductInventory.addCatalogToInventory(catalog);
		//Carts
		cart.addProductToCart(1, 1);
		//cart.addProductToCart(2, 3);

		cart2.addProductToCart(2, 1);
		
		// orders
		Order order1 = new Order(1, cart, cart.getShipping(), OrderStatus.PENDING, cart.getShippingMethod());
		Order order2 = new Order(2, cart2, cart2.getShipping(), OrderStatus.PAID, cart2.getShippingMethod());
		list.addOrderToList(order1);
		list.addOrderToList(order2);
		backOffice.renderOrderList(list);
		
		list.getOrderPriceById(2);
		list.getOrderShippingById(2);
		list.getOrderShippingMethodById(1);

	}
}
