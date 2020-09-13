package com.airasia.ordering.enities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

/**
 * @author Avinash Gurumurthy
 * Entity class for Orders table
 *
 */
@Data
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long orderId;
	private String orderStatus;
	private LocalDate orderDate;
	private Long hotelID;
	private Long guestID;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private BigDecimal orderPrice;
}
