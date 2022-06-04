package br.com.outletstore.order;

import br.com.outletstore.exceptions.ShippingException;

public interface IOrderListOserver {
	void renderOrderList(OrderList orderList) throws ShippingException;
}
