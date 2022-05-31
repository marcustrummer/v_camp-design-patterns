package br.com.outletstore.cart;

import java.util.ArrayList;
import java.util.List;

import br.com.outletstore.entity.Product;
import br.com.outletstore.inventory.ProductInventory;

public class Cart {

	public double cartPrice;
	public double cartWeight;
	public double totalShippingValue;
	public int cartTotalProducts;
	
	ProductInventory inventory = ProductInventory.getInstance();
	
	
	private static List<Product> cart = new ArrayList<>();
	
	public List<Product> getCart(){
		return cart;
	}
	
	
	
	public void addProductToCart(int sku, int quantity){

		for (Product product : inventory.getInventory()) {
			//finding the product by sku
			if(product.getSku() == sku) {
				//Checking if the stock available can provide the amount of itens requested
				if( inventory.getProductStock(sku) < quantity) {
					System.out.println("GOTCHA!\n\n\n" + " You are trying to buy more products"
							+ " than the actual stock.. you mean bastard!");
					break;
				}
				
				
				
				
				inventory.removeProductFromStock(sku, quantity);
				while(quantity != 0) {
					cart.add(product);
					
					quantity--;
				}
			}
			
		}
	}
	
	public void removeProductFromCart(int sku, int quantity) {
		
		
		for(Product product : inventory.getInventory()) {
			if(product.getSku() == sku) {
				
				if(inventory.getStockReserved(sku) < quantity) {
					System.out.println("GOTCHA!\n\n\n" + "There are less than " + quantity + 
							" products in your cart, sorry!");
					inventory.getStockReserved(sku);
					break;
				}
				
				
				inventory.returnProductToStock(sku, quantity);
				while(quantity != 0) {
					cart.remove(product);
				}
			}
		}
	}
	
	public Double getCartPrice() {
		Double cartPrice =0d;
		
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
