package br.com.outletstore.order;

import br.com.outletstore.cart.Cart;
import br.com.outletstore.exceptions.ShippingException;
import br.com.outletstore.inventory.ProductInventory;

public class Order {
	
	ProductInventory inventory = ProductInventory.getInstance();
	

	private int id;
	
	private  Cart cart;
	
	private Double shipping;

	private Integer status;
	

	public Order(int id, Cart cart, Double shipping, OrderStatus status, String shippingMethod) {
		super();
		this.id = id;
		this.cart = cart;
		this.shipping = shipping;
		this.status = status.getCod();
	}
	
	
	
	//GETTERS AND SETTERS
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
	

//	public void setShipping(String shipping) throws ShippingException {
//		this.shipping = cart.getShipping();
//	}
	
	public Double getShipping() {
		return shipping;
	}



	@Override
	public String toString() {
		return "Order: " + cart.getCart()  + "\n";
	}



	/**
	 * @return the totalPrice
	 * @throws ShippingException 
	 */
	public Double getTotalPrice() throws ShippingException {
		return (cart.getCartPrice()+cart.getShipping());
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
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}









	}




	







	
	


