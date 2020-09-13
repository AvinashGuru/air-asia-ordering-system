package com.airasia.ordering.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.airasia.ordering.enities.Room;

/**
 * @author Avinash Gurumurthy
 * Mapper interface for CRUD operations for Orders table
 *
 */
public interface RoomMapper {
	
	/**
	 * This method gets the hotel Id and room Id as input and gets all matching rooms in the ROOM table 
	 * @param hotelId
	 * @param roomId
	 * @return
	 */
	public List<Room> getRoomsByIds(@Param("hotelId") Long hotelId, @Param("roomIds") List<Long> roomIds);
	
}
