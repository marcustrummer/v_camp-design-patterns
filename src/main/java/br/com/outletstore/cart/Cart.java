package br.com.outletstore.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.outletstore.entity.Product;
import br.com.outletstore.exceptions.InventoryException;
import br.com.outletstore.exceptions.ShippingException;
import br.com.outletstore.inventory.ProductInventory;
import br.com.outletstore.shipping.AeroShipping;
import br.com.outletstore.shipping.RoadShipping;
import br.com.outletstore.shipping.Shipping;

public class Cart {

	ProductInventory inventory = ProductInventory.getInstance();

	private int idCart;

	private List<Product> cart = new ArrayList<>();

	CartList listOfCart = CartList.getInstance();

	public List<Product> getCart() {
		return cart;
	}

	public void addProductToCartById(int idCart, int sku, int quantity) throws InventoryException {
		inventory.removeProductFromStock(sku, quantity);
		Iterator<Cart> it = listOfCart.getCarts().iterator();
		while (it.hasNext()) {
			Cart c = it.next();
			if (c.getIdCart() == idCart) {
				for (Product product : inventory.getInventory()) {
					// finding the product by sku
					if (product.getSku() == sku) {
						while (quantity != 0) {
							c.cart.add(product);
							quantity--;
						}
					}
				}
			}
		}
	}

	public void removeProductFromCartById(int idCart, int sku, int quantity) throws InventoryException {

		Iterator<Cart> ite = listOfCart.getCarts().iterator();
		Iterator<Cart> it = listOfCart.getCarts().iterator();
		int productCount=0;
		while (ite.hasNext()) {

			Cart c = ite.next();
			if (c.getIdCart() == idCart) {
				for (int i = 0; i < cart.size(); i++) {
					if (cart.get(i).getSku() == sku) {
							productCount++;
					}
				}
			}
		}
		if (quantity > productCount) {
			throw new InventoryException("Quantity to remove larger than stock");
		}
		inventory.returnProductsToStock(sku, quantity);
		while (it.hasNext()) {

			Cart c = it.next();
			if (c.getIdCart() == idCart) {
				for (int i = 0; i < cart.size(); i++) {
					if (cart.get(i).getSku() == sku) {
						for (int j = 0; j < quantity; j++) {
							cart.remove(i);
						}
						break;
					}
				}
			}
		}
		// Checks if the amount of products being returned to stock is not larger than
		// quantity in cart

	}

	public Double getCartPrice() {
		Double cartPrice = 0d;
		for (Product product : cart) {
			cartPrice += product.getPrice();
		}
		return cartPrice;
	}

	public Double getCartWeight() {
		Double cartWeight = 0d;
		for (Product product : cart) {
			cartWeight += product.getWeight();
		}
		return cartWeight;

	}

	public Double getShipping() throws ShippingException {
		Shipping shipping;
		if (getCartWeight() <= 10) {
			shipping = new AeroShipping();
		} else {
			shipping = new RoadShipping();
		}
		return shipping.getPrice(this);
	}

	public String getShippingMethod() {
		Shipping shipping;
		if (getCartWeight() <= 10) {
			shipping = new AeroShipping();
		} else {
			shipping = new RoadShipping();
		}
		return shipping.getType();
	}

	@Override
	public String toString() {
		return  cart + "\n";
	}

	/**
	 * @return the idCart
	 */
	public int getIdCart() {
		return idCart;
	}

	/**
	 * @param idCart the idCart to set
	 */
	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}

//	preço total(`getTotal`), peso total(`getWeight`)
//	e a Classe `Shipping` e que também possui instâncias de `Aero` e `Road`.

}
