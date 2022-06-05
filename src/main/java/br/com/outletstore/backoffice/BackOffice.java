package br.com.outletstore.backoffice;

import java.util.Iterator;

import br.com.outletstore.exceptions.ShippingException;
import br.com.outletstore.order.IOrderListOserver;
import br.com.outletstore.order.Order;
import br.com.outletstore.order.OrderList;

public class BackOffice implements IOrderListOserver {

	OrderList orders = OrderList.getInstance();

	@Override
	public void renderOrderList(OrderList orderList) throws ShippingException {	
		Iterator<Order> it = orders.getOrders().iterator();
		while(it.hasNext()) {
			Order order = it.next();
			System.out.println("Order:"+ order.getId() +  " " );
			System.out.println("Products: " +order.getCart() + "]");
			System.out.println("Status: \n" +"[ "+ order.getStatus()+" ]");
			System.out.println("Shipping: \n" + "[ " +order.getShipping() + " ]");
			System.out.println("Shipping: \n" + "[ " +order.getShippingMethod() + " ]");
			System.out.println("Total price: \n" + "[ " + order.getTotalPrice() + " ]" + "\n\n\n");
		}

	}
}
