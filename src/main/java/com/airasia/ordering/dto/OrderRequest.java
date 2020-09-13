package com.airasia.ordering.dto;

import java.io.Serializable;
import java.util.List;

import com.airasia.ordering.annotations.MandatoryProp;
import com.airasia.ordering.mapper.utils.Constants;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Avinash Gurumurthy
 *
 * Class to map the request JSON,required to create an order
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderRequest extends ValidatedObject implements Serializable{
	private static final long serialVersionUID = 1L;
	@MandatoryProp(message = "Please provide First Name")
	private String firstName;
	@MandatoryProp(message = "Please provide Last Name")
	private String lastName;
	@MandatoryProp(message = "Please provide Email Address")
	private String emailAddress;
	@MandatoryProp(message = "Please provide Contact Number")
	private String contactNum;
	@MandatoryProp(message = "Please provide hotel ID")
	private Long hotelID;
	private String hotelName;
	@MandatoryProp(message = "Please provide hotel Check In Date", type = "Date", format = Constants.DB_DATE_FORMAT)
	private String checkInDate;
	@MandatoryProp(message = "Please provide hotel Check Out Date", type = "Date", format = Constants.DB_DATE_FORMAT)
	private String checkOutDate;
	@MandatoryProp(message = "Please provide Room Details", type = "collection")
	private List<Rooms> rooms;
}
