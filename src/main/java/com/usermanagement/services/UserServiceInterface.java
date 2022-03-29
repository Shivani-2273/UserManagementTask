package com.usermanagement.services;
import java.io.IOException;
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

public interface UserServiceInterface {
	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 */
	public boolean compareLoginDetails(User obj) throws ClassNotFoundException, SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException;
	
	
	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int addUser(User user) throws ClassNotFoundException, SQLException, IOException;
	
	/**
	 * 
	 * @param addr_obj
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int addAddress(int userId,Address addr_obj) throws ClassNotFoundException, SQLException;
	
	
	/**
	 * 
	 * @param user
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<User> getAllUser(User user) throws ClassNotFoundException, SQLException;
	
	/**
	 * 
	 * @param userId
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void deleteUser(String userId) throws ClassNotFoundException, SQLException;
	
	/**
	 * 
	 * @param obj
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void resetPassword(User obj) throws ClassNotFoundException, SQLException;

}
