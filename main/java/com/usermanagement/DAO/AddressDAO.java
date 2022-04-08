package com.usermanagement.DAO;

import java.util.List;

import com.usermanagement.model.Address;

public interface AddressDAO {

	
	/**
	 * This method will add all details of address into address table
	 * @param userId
	 * @param addr_obj
	 */
	public void addAddress(int userId,Address addr_obj);
	
	/**
	 * This method will list default address details
	 * @param user_id
	 * @return
	 */
	public List<Address> getDefaultAddress(int user_id);

	/**
	 * This method will list other address
	 * @param user_id
	 * @return
	 */
	public List<Address> getOtherAddress(int user_id);
	
	public void updateAddress(int userId,Address addr_obj);
	
	
	public List<Address> getAddress(int userId);

}
