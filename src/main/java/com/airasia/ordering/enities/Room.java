package com.airasia.ordering.enities;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * @author Avinash Gurumurthy
 * Entity class for Room table
 *
 */
@Data
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long hotelID;
	private Long roomID;
	private String roomName;
	private int totalRooms;
	private int adultCapacity;
	private int childCapacity;
	private BigDecimal price;
}
