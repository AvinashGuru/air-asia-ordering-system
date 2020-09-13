package com.airasia.ordering.dto;

import java.io.Serializable;

import com.airasia.ordering.annotations.MandatoryProp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Avinash Gurumurthy
 * 
 * Request mapping class for individual rooms booked in an order. An order can have multiple rooms. 
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Rooms extends ValidatedObject implements  Serializable {
	private static final long serialVersionUID = 1L;
	@MandatoryProp(message = "Please provide Room ID")
	private Long roomID;
	private String roomName;
	@MandatoryProp(message = "Please provide number of guests", type = "Integer")
	private Integer totalGuests;
	@MandatoryProp(message = "Please provide number of rooms", type = "Integer")
	private Integer totalRooms;
}
