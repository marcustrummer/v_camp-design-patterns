package br.com.outletstore.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.outletstore.exceptions.OrderException;
import br.com.outletstore.exceptions.ShippingException;

//Crie uma classe chamada `OrderList` que utilize o padrão `Iterator` e `Singleton`
//para armazenar uma lista de instâncias da classe `Order`,
//que irá ser consumida pela classe `Backoffice` para varrer a listagem de pedidos
//e exibir os dados referente aos pedidos.
public class OrderList implements Iterable<Order> {

	private static OrderList orderList;

	private List<IOrderListOserver> observers = new ArrayList<>();

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

	public void addOrderToList(Order order) throws OrderException {
		if (order == null) {
			throw new OrderException("Order is null!");
		}

		if (order.getStatus() == OrderStatus.CANCELED) {
			throw new OrderException("Order canceled and cannot be added to list");
		}
		orders.add(order);
		observers.forEach(o -> {
			try {
				o.renderOrderList(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	public void RemoveOrderFromList(Order order) throws OrderException {
		if (orders.isEmpty()) {
			throw new OrderException("Order cannot be remoed. Order list is empty!");
		}
		if (order == null) {
			throw new OrderException("Order is null!");
		}

		orders.remove(order);
		observers.forEach(o -> {
			try {
				o.renderOrderList(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public String getOrderPriceById(int id) throws ShippingException, OrderException {
		Iterator<Order> it = OrderList.orders.iterator();
		String result = null;
		while (it.hasNext()) {
			Order o = it.next();
			if (o.getId() == id)
				result = "Order price: " + o.getTotalPrice();
		}

		if (result == null) {
			throw new OrderException("Order not found");
		}
		return result;
	}

	public String getOrderShippingById(int id) throws OrderException {
		Iterator<Order> it = OrderList.orders.iterator();
		String result = null;
		while (it.hasNext()) {
			Order o = it.next();
			if (o.getId() == id)
				result = "Order shipping price: " + o.getShipping();
		}
		if (result == null) {
			throw new OrderException("Order not found");
		}

		return result;
	}

	public String getOrderShippingMethodById(int id) throws OrderException {
		Iterator<Order> it = OrderList.orders.iterator();
		String result = null;
		while (it.hasNext()) {
			Order o = it.next();
			if (o.getId() == id)
				result = "Order shipping price: " + o.getShippingMethod();
		}
		if (result == null) {
			throw new OrderException("Order not found");
		}
		return result;
	}

	public void addObserver(IOrderListOserver observer) throws OrderException {
		
		if(observer == null) {
			throw new OrderException("Observer cannot be null!");
		}
		observers.add(observer);
	}

	public void removeObserver(IOrderListOserver observer) throws OrderException {
		if(observer == null) {
			throw new OrderException("Observer cannot be null!");
		}
		observers.remove(observer);
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
