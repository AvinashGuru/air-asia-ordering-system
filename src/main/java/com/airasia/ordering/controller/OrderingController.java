package com.airasia.ordering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airasia.ordering.dto.OrderRequest;
import com.airasia.ordering.dto.OrderResponse;
import com.airasia.ordering.exceptions.BusinessValidationException;
import com.airasia.ordering.mapper.utils.Constants;
import com.airasia.ordering.services.OrderingService;

import lombok.extern.log4j.Log4j2;


/**
 * @author Avinash Gurumurthy
 * 
 * Controller class for mapping the order creation request and responds with a success or failure message
 *
 */
@Controller
@RequestMapping("/ordering")
@Log4j2
public class OrderingController {
	
	@Autowired
	OrderingService orderingservice;

	private static final String CREATE_ORDER = "/create";

	@PostMapping(value = CREATE_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderResponse> processStatements(@RequestBody OrderRequest request) throws Exception {
		
		OrderResponse response = new OrderResponse();
		try {
			response = orderingservice.createOrder(request);
		} catch (BusinessValidationException e) {
			log.info("Validation failed while creating order : {} ",e.getMessage());
			response.setStatus(e.getType());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			log.info("Error occured while creating order : {} ",e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
			response.setMessage(Constants.GENERIC_ERR_MSG);
		}
		
		HttpStatus httpStatus = HttpStatus.CREATED;
		if (HttpStatus.BAD_REQUEST.name().equalsIgnoreCase(response.getStatus())) {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		if (HttpStatus.NOT_FOUND.name().equalsIgnoreCase(response.getStatus())) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		if (HttpStatus.INTERNAL_SERVER_ERROR.name().equalsIgnoreCase(response.getStatus())) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<OrderResponse>(response, httpStatus);
	}
}
