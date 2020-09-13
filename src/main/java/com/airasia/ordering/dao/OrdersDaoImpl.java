package com.airasia.ordering.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.airasia.ordering.enities.OrderDetails;
import com.airasia.ordering.enities.Orders;
import com.airasia.ordering.mapper.OrderMapper;

/**
 * @author Avinash Gurumurthy
 * Implementation class for Orders DAO Interface
 *
 */
@Repository
public class OrdersDaoImpl implements OrdersDao {
	
	@Autowired
	OrderMapper mapper;

	@Override
	public int createOrder(Orders order) {
		return mapper.createOrder(order);
	}

	@Override
	public int createOrderDetails(List<OrderDetails> orderDetails) {
		return mapper.createOrderDetails(orderDetails);
	}

}
