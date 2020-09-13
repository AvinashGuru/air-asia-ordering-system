package com.airasia.ordering.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.airasia.ordering.enities.OrderDetails;
import com.airasia.ordering.enities.Orders;

/**
 * @author Avinash Gurumurthy
 * Mapper interface for CRUD operations for Orders table
 *
 */
public interface OrderMapper {
	
	/**
	 * This method gets the order object with all required properties and creates an entry in order table,
	 * Once Order entry is created successfully orderId is returned
	 * @param order
	 * @return
	 */
	public int createOrder(@Param("order") Orders order);
	
	
	/**
	 * This method gets the list of Room informations for an order and creates entry in order details table
	 * @param orderDetails
	 * @return
	 */
	public int createOrderDetails(@Param("orderDetails") List<OrderDetails> orderDetails);
	
	
}
