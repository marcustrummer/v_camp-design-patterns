package br.com.outletstore.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Crie uma classe chamada `OrderList` que utilize o padrão `Iterator` e `Singleton`
//para armazenar uma lista de instâncias da classe `Order`,
//que irá ser consumida pela classe `Backoffice` para varrer a listagem de pedidos
//e exibir os dados referente aos pedidos.
public class OrderList implements Iterator<Order> {

	// The field storing the instance of singleton must be declared as static.
	private static OrderList orderList;

	private static List<Order> orders = new ArrayList<>();

	// The static method that gives access to the singleton
	public static OrderList getInstance() {
		// Makes sure the instance of singleton
		// wasn't initialized by another thread yet
		if (orderList == null) {
			orderList = new OrderList(orders);
		}
		return orderList;
	}

	// The constructor of the singleton must always be declared as
	// private to make sure noone can make new instances of the singleton
	// by using the operator `new`
	// i.e, OrderList orderList = new OrderList();
	private OrderList(List<Order> orders) {
		OrderList.orders = orders;
	}

	@Override
	public boolean hasNext() {
		return OrderList.getInstance().hasNext();
	}

	@Override
	public Order next() {
		return orderList.next();
	}

}
