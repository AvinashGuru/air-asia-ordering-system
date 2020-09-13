package com.airasia.ordering.services;

import com.airasia.ordering.dto.OrderRequest;
import com.airasia.ordering.dto.OrderResponse;
import com.airasia.ordering.exceptions.BusinessValidationException;

/**
 * @author Avinash Gurumurthy
 * 
 * Service class for getting order information as request, validating the request using validator service .
 * once validation is success then creates an order and order details in respective tables, provides response status as 'Success' and message as  'Order has been created, Order Id : ${orderId} '
 * If validation fails, returns response with status as 'Validation Failed' and message as Validation failure message, example : 'No Rooms available for selected date, please try for some other dates'
 * If any unexpected error occurs then returns response with status as 'Error' and message as 'Error Occurred while processing the request'
 */

public interface OrderingService {
	
	/**
	 * Method used to validate the request passed as parameter, based on the validation either creates order  and order details , else throw error message as the response
	 * @param request
	 * @return
	 */
	public OrderResponse createOrder(OrderRequest request)  throws BusinessValidationException, Exception ;

}
