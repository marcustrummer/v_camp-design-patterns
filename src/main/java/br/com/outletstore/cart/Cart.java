package br.com.outletstore.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//		Optional<Product> prod = ce.get().cart.stream().filter(p->p.getSku()== sku).findAny();
//		ce.get().cart.add(prod);
		// List<Product> aux = ce.get().getCart().stream().filter(prod ->
		// prod.getSku()==sku).toList();
		Optional<Cart> ce = listOfCart.getCarts().stream().filter(cart -> cart.getIdCart() == idCart).findAny();
		inventory.removeProductFromStock(sku, quantity);
		for (Product product : inventory.getInventory()) {
			if (product.getSku() == sku) {
				for(int i=0; i<quantity;i++) {
					ce.get().cart.add(product);
				}
			}
		}
	}

	public void removeProductFromCartById(int idCart, int sku, int quantity) throws InventoryException {
		Optional<Cart> ce = listOfCart.getCarts().stream().filter(cart -> cart.getIdCart() == idCart).findAny();
		int prodCount = (int) ce.get().cart.stream().filter(p -> p.getSku() == sku).count();
		if (quantity > prodCount) {
			throw new InventoryException("Quantity to remove larger than stock");
		}
		inventory.returnProductsToStock(sku, quantity);
		// List<Product> aux = ce.get().getCart().stream().filter(prod ->
		// prod.getSku()==sku).toList();
		for (int i = 0; i < quantity; i++) {
			if (cart.get(i).getSku() == sku) {
				for (int j = 0; j < quantity; j++) {
					ce.get().cart.remove(i);
				}
				break;
			}
		}
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
		return cart + "\n";
	}

	public int getIdCart() {
		return idCart;
	}

	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}

}
