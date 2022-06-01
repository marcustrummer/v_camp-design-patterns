package br.com.outletstore.order;

import br.com.outletstore.cart.Cart;
import br.com.outletstore.shipping.Shipping;

public class Order {
	
	private Cart cart;
	
	private Shipping shipping;
	
	private OrderStatus orderStatus;

	

	public Order(Cart cart, Shipping shipping, OrderStatus orderStatus) {
		super();
		this.cart = cart;
		this.shipping = shipping;
		this.orderStatus = orderStatus;
	}

	//GETTERS AND SETTERS
	public Cart getCart() {
		return cart;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	

}
