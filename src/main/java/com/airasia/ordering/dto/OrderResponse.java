package com.airasia.ordering.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Avinash Gurumurthy
 * The class maps to the API response payload after successful order creation
 *
 */
@Data
public class OrderResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private String status;
	private String message;
}
