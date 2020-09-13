package com.airasia.ordering.dao;

import java.util.List;

import com.airasia.ordering.enities.Room;

/**
 * @author Avinash Gurumurthy
 * DAO Repository interface for getting details of ROOM information to create order details
 *
 */
public interface RoomDao {

	public List<Room> getRoomsByIds(Long hotelId, List<Long> roomIds);
}
