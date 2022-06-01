package br.com.outletstore.shipping;

import br.com.outletstore.cart.Cart;
import br.com.outletstore.exceptions.ShippingException;

public class Shipping implements IShipping{

	
	public Shipping obtainShipping(Cart cart) {
		if (cart.getCartWeight() > 10) {
			return new RoadShipping();
		} else {
			return new AeroShipping();
		}
	}
	
	
	
	
	
	@Override
	public Double getPrice(Cart cart) throws ShippingException {
		// TODO Auto-generated method stub
		return 0d;
	}

	@Override
	public String getType(String type) {
		// TODO Auto-generated method stub
		return type;
	}
	
	

}
