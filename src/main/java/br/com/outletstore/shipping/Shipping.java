package br.com.outletstore.shipping;

import br.com.outletstore.cart.Cart;
import br.com.outletstore.exceptions.ShippingException;

public abstract class Shipping implements IShipping{

	private String type;
	
	
	@Override
	public Double getPrice(Cart cart) throws ShippingException {
		// TODO Auto-generated method stub
		return 0d;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}


	
	

}
