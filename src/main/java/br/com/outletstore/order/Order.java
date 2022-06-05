package br.com.outletstore.order;

import br.com.outletstore.cart.Cart;
import br.com.outletstore.exceptions.ShippingException;
import br.com.outletstore.inventory.ProductInventory;

public class Order {

	ProductInventory inventory = ProductInventory.getInstance();

	private int idOrder;

	private Cart cart;

	private Double shipping;

	private Integer status;

	public Order(int id, Cart cart, Double shipping, OrderStatus status, String shippingMethod) {
		super();
		this.idOrder = id;
		this.cart = cart;
		this.shipping = shipping;
		this.status = status.getCod();
	}

	// GETTERS AND SETTERS
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public OrderStatus getStatus() {
		return OrderStatus.toEnum(status);
	}

	public void setStatus(OrderStatus status) {
		this.status = status.getCod();
	}

	public Double getShipping() {
		return shipping;
	}

	@Override
	public String toString() {
		return "Order: " + cart.getCart() + "\n";
	}

	/**
	 * @return the totalPrice
	 * @throws ShippingException
	 */
	public Double getTotalPrice() throws ShippingException {
		return (cart.getCartPrice() + cart.getShipping());
	}

	/**
	 * @return the shippingMethod
	 */
	public String getShippingMethod() {
		return cart.getShippingMethod();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return idOrder;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.idOrder = id;
	}

}
