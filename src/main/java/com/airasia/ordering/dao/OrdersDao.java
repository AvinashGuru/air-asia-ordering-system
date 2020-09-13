package com.airasia.ordering.dao;

import java.util.List;

import com.airasia.ordering.enities.OrderDetails;
import com.airasia.ordering.enities.Orders;

/**
 * @author Avinash Gurumurthy
 * DAO Repository interface for creating Order and Order details
 *
 */
public interface OrdersDao {

	/**
	 * This method gets the order object with all required properties and creates an entry in order table,
	 * Once Order entry is created successfully orderId is returned
	 * @param order
	 * @return
	 */
	public int createOrder(Orders order);
	
	
	/**
	 * This method gets the list of Room informations for an order and creates entry in order details table
	 * @param orderDetails
	 * @return
	 */
	public int createOrderDetails(List<OrderDetails> orderDetails);
}
