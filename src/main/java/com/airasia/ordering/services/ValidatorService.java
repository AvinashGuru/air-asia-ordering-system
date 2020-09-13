package com.airasia.ordering.services;

import java.util.List;

import com.airasia.ordering.dto.OrderRequest;
import com.airasia.ordering.enities.Room;
import com.airasia.ordering.exceptions.BusinessValidationException;

/**
 * @author Avinash Gurumurthy
 * 
 * This class is used to validate the request passed as parameters.
 * Following validations are added
 * Rooms available for selected date by the user 
 * Total number of  persons allowed in a room as per the ROOM information and validates against the request passed
 */
public interface ValidatorService {
	
	/**
	 * This method is to validate the request passed as a parameter is proper .
	 * This method has checks on whether passed request is empty
	 * If request is not empty then checks all mandatory fields are provided in the payload, if not then throws exception 
	 * @param request
	 * @throws BusinessValidationException
	 */
	public void validateRequest(OrderRequest request) throws BusinessValidationException, Exception;

	/**
	 * This method validates the actual allowed capacity of all the rooms vs the capacity provided by the user while booking
	 * If there is any request exceeding actual allowed capacity then exception is thrown along with the Room ID
	 * @param request
	 * @param roomDetails
	 * @throws BusinessValidationException
	 */
	public void validateRoomCapacity(OrderRequest request, List<Room> roomDetails )  throws BusinessValidationException;
}
