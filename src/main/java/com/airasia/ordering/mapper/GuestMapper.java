package com.airasia.ordering.mapper;


import org.apache.ibatis.annotations.Param;

import com.airasia.ordering.enities.Guest;

/**
 * @author Avinash Gurumurthy
 * Mapper interface for CRUD operations for Orders table
 *
 */
public interface GuestMapper {
	
	/**
	 * This method gets the Guest object with all required properties and creates an entry in Guest table,
	 * Once Guest entry is created successfully guestID is returned
	 * @param order
	 * @return
	 */
	public int createGuestDetails(@Param("guest") Guest guest);
	
	
}
