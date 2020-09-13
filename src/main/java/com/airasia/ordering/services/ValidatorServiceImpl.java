package com.airasia.ordering.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.airasia.ordering.dao.RoomDao;
import com.airasia.ordering.dto.OrderRequest;
import com.airasia.ordering.dto.Rooms;
import com.airasia.ordering.dto.ValidatedObject;
import com.airasia.ordering.enities.Room;
import com.airasia.ordering.exceptions.BusinessValidationException;
import com.airasia.ordering.mapper.utils.Constants;

import lombok.extern.log4j.Log4j2;

/**
 * @author Avinash Gurumurthy
 * 
 * This is the implementation class for ValidatorService Interface
 *
 */
@Service
@Log4j2
public class ValidatorServiceImpl implements ValidatorService{
	
	@Autowired
	RoomDao roomDao;
	
	@Autowired
	ObjectValidatorService<ValidatedObject> objectValidaorService;

	/**
	 * This method is to validate the request passed as a parameter is proper .
	 * This method has checks on whether passed request is empty
	 * If request is not empty then checks all mandatory fields are provided in the payload, if not then throws exception 
	 * @param request
	 * @throws BusinessValidationException
	 */
	@Override
	public void validateRequest(OrderRequest request) throws BusinessValidationException, Exception {
		try {
			objectValidaorService.validate(request);
			if(request.getRooms() == null || request.getRooms().isEmpty()) {
				throw new BusinessValidationException(Constants.EMPTY_ROOM_DTLS_ERR);
			}
			for(Rooms room : request.getRooms()) {
				objectValidaorService.validate(room);
			}
		}catch (BusinessValidationException e) {
			log.error("Validation error {} ",e.getMessage());
			e.setType(HttpStatus.BAD_REQUEST.name());
			throw  e;
		}
	}


	/**
	 * This method validates the actual allowed capacity of all the rooms vs the capacity provided by the user while booking
	 * If there is any request exceeding actual allowed capacity then exception is thrown along with the Room ID
	 * @param request
	 * @param roomDetails
	 * @throws BusinessValidationException
	 */
	@Override
	public void validateRoomCapacity(OrderRequest request, List<Room> roomDetails) throws BusinessValidationException {
		Long hotelId = request.getHotelID();
		for(Rooms room : request.getRooms()) {
			if(null != room) {
				Optional<Room> roomDtlObj = roomDetails.stream().filter(roomDtl -> ( (roomDtl.getHotelID() == hotelId)  && (roomDtl.getRoomID() == room.getRoomID())) ).findFirst();
				if(!roomDtlObj.isPresent()) {
					BusinessValidationException e = new BusinessValidationException(String.format(Constants.INVALID_ROOM_DTLS_ERR, hotelId, room.getRoomID()));
					e.setType(HttpStatus.NOT_FOUND.name());
					throw e;
				}else {
					Room roomDtls = roomDtlObj.get();
					if(room.getTotalGuests() > roomDtls.getAdultCapacity()) {
						BusinessValidationException e = new BusinessValidationException(String.format(Constants.GUEST_LIMIT_ERR, room.getRoomID(), roomDtls.getAdultCapacity()));
						e.setType(HttpStatus.BAD_REQUEST.name());
						throw e;
					}
				}
			}
		}
	}
	
}
