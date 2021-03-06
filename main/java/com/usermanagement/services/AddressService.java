package com.usermanagement.services;

import java.sql.SQLException;
import java.util.List;

import com.usermanagement.model.Address;

public interface AddressService {
	
	/**
	 * This method will add all details of address into address table 
	 * @param userId
	 * @param addr_obj
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	 void addAddress(int userId,Address addr_obj) throws ClassNotFoundException, SQLException;

	
	/**
	 * This will update address details of given user id
	 * @param userId
	 * @param addr_obj
	 * @throws SQLException 
	 */
	 void updateAddress(int userId,Address addr_obj) throws SQLException;
	
	/**
	 * Get list of all address of given user id
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	 List<Address> getAddress(int userId) throws SQLException;
	
}
