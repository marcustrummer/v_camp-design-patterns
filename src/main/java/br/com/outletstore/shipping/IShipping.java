package br.com.outletstore.shipping;

import br.com.outletstore.cart.Cart;
import br.com.outletstore.exceptions.ShippingException;

public interface IShipping {

	
	Double getPrice(Cart cart) throws ShippingException;
	
	String getType(String type);
}
