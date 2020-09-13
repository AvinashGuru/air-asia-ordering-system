package com.airasia.ordering.enities;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * @author Avinash Gurumurthy
 * Entity class for Order Details table
 *
 */
@Data
public class OrderDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long orderID;
	private Long hotelID;
	private Long roomID;
	private Integer roomCount;
	private BigDecimal price;
}
