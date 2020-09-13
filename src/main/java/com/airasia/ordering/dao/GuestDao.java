package com.airasia.ordering.dao;

import com.airasia.ordering.enities.Guest;

/**
 * @author Avinash Gurumurthy
 * DAO Repository interface for creating Guest details for an order
 *
 */
public interface GuestDao {

	/**
	 * Gets information for Guest like name, phone, email etc and populates to database,
	 * returns guest Id as response
	 * @param guest
	 * @return
	 */
	public int createGuestDetails(Guest guest);
}
