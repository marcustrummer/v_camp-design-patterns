package br.com.outletstore.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.outletstore.entity.Product;
import br.com.outletstore.exceptions.InventoryException;
import br.com.outletstore.inventory.ProductInventory;

public class Cart {

	ProductInventory inventory = ProductInventory.getInstance();

	private static List<Product> cart = new ArrayList<>();

	public List<Product> getCart() {
		return cart;
	}

	public void addProductToCart(int sku, int quantity) throws InventoryException {

		for (Product product : inventory.getInventory()) {
			// finding the product by sku
			if (product.getSku() == sku) {
				inventory.removeProductFromStock(sku, quantity);
				while (quantity != 0) {
					cart.add(product);
					quantity--;
				}
			}

		}
	}

	public void removeProductFromCart(int sku, int quantity) throws InventoryException {
		int countProducts = (int) cart.stream().filter(prod -> prod.getSku() == sku).count();
		// Checks if the amount of products being returned to stock is not larger than
		// quantity in cart
		if (quantity > countProducts) {
			throw new InventoryException("Quantity to remove larger than stock");
		}

		inventory.returnProductsToStock(sku, quantity);
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getSku() == sku) {
				for (int j = 0; j < quantity; j++) {
					cart.remove(i);
				}
				break;
			}
		}

//		cart.removeIf(prod -> prod.getSku() == sku);

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

//	preço total(`getTotal`), peso total(`getWeight`)
//	e a Classe `Shipping` e que também possui instâncias de `Aero` e `Road`.

}
