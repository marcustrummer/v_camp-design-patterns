package br.com.outletstore.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.outletstore.exceptions.ShippingException;

//Crie uma classe chamada `OrderList` que utilize o padrão `Iterator` e `Singleton`
//para armazenar uma lista de instâncias da classe `Order`,
//que irá ser consumida pela classe `Backoffice` para varrer a listagem de pedidos
//e exibir os dados referente aos pedidos.
public class OrderList implements Iterable<Order> {

	private static OrderList orderList;

	private static List<Order> orders = new ArrayList<>();

	private OrderList(List<Order> orders) {
		OrderList.orders = orders;
	}

	public static OrderList getInstance() {
		if (orderList == null) {
			orderList = new OrderList(orders);
		}
		return orderList;
	}
	
	public List<Order> getOrders() {
		return OrderList.orders;
	}
	
	
	public void addOrderToList(Order order) {
		if(order.getStatus()== OrderStatus.CANCELED) {
			System.out.println("This order >>[" + order.getId() + "]<< is cancelled and can not be added to the order list.");
		} else {
			orders.add(order);
		}
	}
	

	public void getOrderPriceById(int id) throws ShippingException {
		Iterator<Order> it = OrderList.orders.iterator();
		while(it.hasNext()) {
			Order o = it.next();
			if(o.getId() == id)
			System.out.println("Order price: " + o.getTotalPrice());
		}
	}
	


	public void getOrderShippingById(int id){
		Iterator<Order> it = OrderList.orders.iterator();
		while(it.hasNext()) {
			Order o = it.next();
			if(o.getId() == id)
			System.out.println("Order shipping price: " + o.getShipping());
		}
	}
	public void getOrderShippingMethodById(int id){
		Iterator<Order> it = OrderList.orders.iterator();
		while(it.hasNext()) {
			Order o = it.next();
			if(o.getId() == id)
			System.out.println("Order shipping price: " + o.getShippingMethod());
		}
	}
	

	@Override
	public Iterator<Order> iterator() {
		Iterator<Order> it = new Iterator<Order>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < orders.size() && orders.get(currentIndex) != null;
			}

			@Override
			public Order next() {
				return orders.get(currentIndex++);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return it;
	}
}
