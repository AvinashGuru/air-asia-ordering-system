package com.airasia.ordering.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airasia.ordering.dao.GuestDao;
import com.airasia.ordering.dao.OrdersDao;
import com.airasia.ordering.dao.RoomDao;
import com.airasia.ordering.dto.OrderRequest;
import com.airasia.ordering.dto.OrderResponse;
import com.airasia.ordering.dto.Rooms;
import com.airasia.ordering.enities.Guest;
import com.airasia.ordering.enities.OrderDetails;
import com.airasia.ordering.enities.Orders;
import com.airasia.ordering.enities.Room;
import com.airasia.ordering.exceptions.BusinessValidationException;
import com.airasia.ordering.mapper.utils.Constants;
import com.airasia.ordering.mapper.utils.DateUtils;

import lombok.extern.log4j.Log4j2;

/**
 * @author Avinash Gurumurthy
 * 
 * This class is the implementation class for the OrderingService Interface
 *
 */
@Service
@Log4j2
public class OrderingServiceImpl implements OrderingService {
	
	@Autowired
	ValidatorService validatorService;
	
	@Autowired
	RoomDao roomDao;
	
	@Autowired
	GuestDao guestDao;
	
	@Autowired
	OrdersDao ordersDao;

	/**
	 * Method used to validate the request passed as parameter, based on the validation either creates order  and order details , else throw error message as the response
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws BusinessValidationException 
	 */
	@Override
	@Transactional
	public OrderResponse createOrder(OrderRequest request) throws BusinessValidationException, Exception {
		OrderResponse resp = new OrderResponse();
		validatorService.validateRequest(request);
		List<Rooms> rooms = request.getRooms();
		Long hotelId =request.getHotelID();
		List<Long> roomIds = rooms.stream().map(Rooms::getRoomID).collect(Collectors.toList());

		List<Room> roomDetails = roomDao.getRoomsByIds(hotelId, roomIds);
		log.info("Room Details Obtained {} ",roomDetails);
		validatorService.validateRoomCapacity(request, roomDetails);

		Long orderId = createOrderDetails(request, roomDetails);
		resp.setStatus(Constants.SUCCESS);
		resp.setMessage(String.format(Constants.ORDER_CREATED_MSG, orderId));


		return resp;
	}

	/**
	 * This method, gets details of order from request and creates Guest details, Order details etc
	 * we are also calculating total price of the order, by summing up all the price of the rooms in the order
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	private Long createOrderDetails(OrderRequest request, List<Room> roomDetails) throws Exception {
		Guest guest = createGuestObjectFromReq(request);
		
		Orders orders =  new Orders();
		List<OrderDetails> orderDtls = createOrderDtlsObjectFromReq(request, roomDetails);
		if(orderDtls == null || orderDtls.isEmpty()) {
			log.error("Order Details is empty, unable to proceed to create order ");
			throw new RuntimeException("Order Details is empty, unable to proceed to create order ");
		}
	
		guestDao.createGuestDetails(guest);
		Long guestId = guest.getGuestID();
		log.info("Guest Details are created with ID : {} ", guestId);
		
		// calculating total price of the order, by summing up all the price of the rooms in the order
		BigDecimal price  = orderDtls.stream().map(orderDtl -> orderDtl.getPrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
		log.info("Price of the order {} ", price);
		
		orders.setOrderStatus("Created");
		orders.setHotelID(request.getHotelID());
		orders.setGuestID(guestId);
		orders.setOrderPrice(price);
		orders.setCheckInDate(DateUtils.getDateFromString(request.getCheckInDate(), Constants.DB_DATE_FORMAT));
		orders.setCheckOutDate(DateUtils.getDateFromString(request.getCheckOutDate(), Constants.DB_DATE_FORMAT));
		
		ordersDao.createOrder(orders);
		Long ordersId = orders.getOrderId();
		log.info("Order entry is created with ID: {} ", ordersId);
		
		orderDtls.forEach( orderDtl -> orderDtl.setOrderID(ordersId));
		ordersDao.createOrderDetails(orderDtls);
		log.info("Order details are created for Order : {} ", ordersId);
		log.info("Order creation process completed");
		
		return ordersId;
	}

	/**
	 * This method creates the Guest Entity object from request
	 * @param request
	 * @return
	 */
	private Guest createGuestObjectFromReq(OrderRequest request) {
		Guest guest = new Guest();
		guest.setFirstName(request.getFirstName());
		guest.setLastName(request.getLastName());
		guest.setEmailID(request.getEmailAddress());
		guest.setContactNum(request.getContactNum());
		return guest;
	}
	
	
	/**
	 * This method creates the Order Details Entity object from request
	 * @param request
	 * @return
	 */
	private List<OrderDetails> createOrderDtlsObjectFromReq(OrderRequest request, List<Room> roomDetails) {
		List<OrderDetails> orderDtls = new ArrayList<>();
		Long hotelId = request.getHotelID();
		for(Rooms roomReq : request.getRooms()) {
			if(null != roomReq) {
				Optional<Room> roomDtlObj = roomDetails.stream().filter(roomDtl -> ( (roomDtl.getHotelID() == hotelId)  && (roomDtl.getRoomID() == roomReq.getRoomID())) ).findFirst();
				if(roomDtlObj.isPresent()) {
					Room roomDtls = roomDtlObj.get();
					OrderDetails ord = new OrderDetails();
					ord.setHotelID(hotelId);
					ord.setRoomID(roomDtls.getRoomID());
					ord.setRoomCount(roomReq.getTotalRooms());
					if(roomDtls.getPrice() != null && roomReq.getTotalRooms() !=null) {
						BigDecimal orderDetailPrice = roomDtls.getPrice().multiply(new BigDecimal(roomReq.getTotalRooms()));
						ord.setPrice(orderDetailPrice);
					}else {
						log.error(" Room Price or total rooms is not retireved properly for Room ID {} ",roomReq.getRoomID());
						ord.setPrice(new BigDecimal(0));
					}
					orderDtls.add(ord);
				}
			}
		}
		return orderDtls;
	}

}
