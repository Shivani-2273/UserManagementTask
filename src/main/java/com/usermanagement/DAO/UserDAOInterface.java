package com.usermanagement.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.usermanagement.model.Address;
import com.usermanagement.model.User;

public interface UserDAOInterface {

	/**
	 * 
	 * @param obj
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 */
	public boolean userLogin(User obj) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException;

	/**
	 * 
	 * @throws IOException
	 */
	public int userRegister(User obj, InputStream image) throws IOException;

	/**
	 * 
	 * @param addr_obj
	 */
	public int addAddress(int userId, Address addr_obj);

	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getAllUser(User user);

	/**
	 * 
	 * @param userId
	 */
	public void deleteUser(String userId);

	/**
	 * 
	 * @param user
	 */
	public void resetPassword(User user);

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 */
	public List<User> getCSVFile(User user);

	/**
	 * 
	 * @param obj
	 * @param user_id
	 * @return
	 */
	public List<Address> getDefaultAddress(Address obj, int user_id);

	/**
	 * 
	 * @param obj
	 * @param user_id
	 * @return
	 */
	public List<Address> getOtherAddress(Address obj, int user_id);
}
