package com.airasia.ordering.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.airasia.ordering.enities.Room;
import com.airasia.ordering.mapper.RoomMapper;

/**
 * @author Avinash Gurumurthy
 * Implementation class for ROOM Dao interface, this class has method for getting room details
 *
 */
@Repository
public class RoomDaoImpl implements RoomDao {
	
	@Autowired
	RoomMapper mapper;

	@Override
	public List<Room> getRoomsByIds(Long hotelId, List<Long> roomIds) {
		return mapper.getRoomsByIds(hotelId, roomIds);
	}

}
