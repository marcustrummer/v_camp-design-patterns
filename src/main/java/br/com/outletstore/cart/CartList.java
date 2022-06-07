package br.com.outletstore.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class CartList implements Iterable<Cart>{
	
	private static CartList cartList;
	
	private static List<Cart> carts = new ArrayList<>();
	
	private CartList(List<Cart> carts) {
		CartList.carts = carts;
	}
	
	public static CartList getInstance() {
		if (cartList == null) {
			cartList = new CartList(carts);
		}
		return cartList;
	}
	
	public List<Cart> getCarts() {
		return CartList.carts;
	}
	
	
	public void addCartToList(Cart cart) {
			carts.add(cart);
	}
	
	
	public Optional<Cart> getCartById(int id){
		
		Optional<Cart> c = CartList.carts.stream().filter(cart -> cart.getIdCart() == id).findAny();
		if (c.isEmpty()) {
			return null;
		}
		return c;
		
//		Iterator<Cart> it = CartList.carts.iterator();
//		while(it.hasNext()) {
//			Cart o = it.next();
//			if(o.getIdCart() == id) {
//				return o;
//			}
//		}
//		return null;
	}

	@Override
	public Iterator<Cart> iterator() {
		Iterator<Cart> it = new Iterator<Cart>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < carts.size() && carts.get(currentIndex) != null;
			}

			@Override
			public Cart next() {
				return carts.get(currentIndex++);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return it;
	}

}
