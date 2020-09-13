package com.airasia.ordering.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.airasia.ordering.enities.Guest;
import com.airasia.ordering.mapper.GuestMapper;

/**
 * @author Avinash Gurumurthy
 * Implementation class of Guest Dao Interface
 * This class has methods that creates guest information required to create an order
 *
 */
@Repository
public class GuestDaoImpl implements GuestDao {
	
	@Autowired
	GuestMapper mapper;

	@Override
	public int createGuestDetails(Guest guest) {
		return mapper.createGuestDetails(guest);
	}

}
