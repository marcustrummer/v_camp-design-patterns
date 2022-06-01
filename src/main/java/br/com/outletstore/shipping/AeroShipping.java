package br.com.outletstore.shipping;

import br.com.outletstore.cart.Cart;
import br.com.outletstore.exceptions.ShippingException;

public class AeroShipping extends Shipping {

//	Para o cálculo, deverá ser utilizada a classe `Cart`, que deve expor o método com o `getProducts`, `getTotal` and `getWeight`.
//	O valor do frete deve ser 10% do valor da compra onde o valor mínimo deve ser `R$ 7.99`.
//    Também deve ser somado ao valor do frete `R$ 1` para cada produto.

	@Override
	public Double getPrice(Cart cart) throws ShippingException {
		Double deliverPrice = cart.getCartPrice() * 0.1;
		Double costPerItem = 1.0;
		int countProducts = 0;
		
		
		if(cart.getCartPrice() < 7.99)
			throw new ShippingException("Valor minimo nao atingido");
		
		countProducts = (int) cart.getCart().stream().count();
		deliverPrice += costPerItem * countProducts;
		
		return deliverPrice;
	}

	public String getType() {
		return "Aero";
	}

}
