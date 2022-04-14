package com.usermanagement.DAO;

import java.sql.SQLException;
import java.util.List;

import com.usermanagement.model.Address;

public interface AddressDAO {

	/**
	 * This method will add all details of address into address table
	 * 
	 * @param userId
	 * @param addr_obj
	 * @throws SQLException 
	 */
	void addAddress(int userId, Address addr_obj) throws SQLException;

	/**
	 * This will update address details(add new,update exisitin & delete exsiting)
	 * @param userId
	 * @param addr_obj
	 * @throws SQLException
	 */
	void updateAddress(int userId, Address addr_obj) throws SQLException;

	/**
	 * this is will give address details of given userId
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	List<Address> getAddress(int userId) throws SQLException;

}
