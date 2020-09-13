package com.airasia.ordering.enities;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Avinash Gurumurthy
 * Entity class for Guest table
 */
@Data
public class Guest implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long guestID;
	private String firstName;
	private String lastName;
	private String contactNum;
	private String emailID;
}
