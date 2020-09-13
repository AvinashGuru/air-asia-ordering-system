package com.airasia.ordering.mapper.utils;

public interface Constants {
	
	String EMPTY_REQ_ERROR = "Invalid request, Request details are not present";
	String EMPTY_ROOM_DTLS_ERR = "Invalid request, Room details are not present";
	String INVALID_ROOM_DTLS_ERR = "Invalid room details , Room details for hotel ID: %s and room ID: %s is not present";
	String GUEST_LIMIT_ERR = "Number of guest permitted in Room %s is only %s";
	String GENERIC_ERR_MSG = "Error while processing request";
	String DB_DATE_FORMAT = "yyyy-MM-dd";
	String ORDER_CREATED_MSG = "Order Created Successfully , Order ID :  %s";
	String SUCCESS = "Success";
	String ORDER_CREATE_URL = "/ordering/create";
	String SUCCESS_JSON_FILE = "success.json";
	String MISSING_EMAIL = "EmailIdMissing.json";
	String MISSING_HOTEL_ID = "HotelIDMissing.json";
	String INVALID_DATE = "InvalidDateFormat.json";
	String MISSING_NAME = "NameMissing.json";
	String MISSING_ROOM_DTLS = "RoomDetailsMissing.json";
	String MISSING_ROOM_ID = "RoomIDMissing.json";
	String INVALID_ROOM_ID = "InvalidRoomID.json";
	String INVALID_HOTEL_ID = "InvalidHotelID.json";
}
