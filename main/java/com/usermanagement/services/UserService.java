package com.usermanagement.services;
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

public interface UserService {
	
	/**
	 * This method will return true if login details are correct
	 * @param obj
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public boolean compareLoginDetails(User obj) throws ClassNotFoundException, SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException;
	
	
	/**
	 * This method will add details of user into user table of database
	 * @param user
	 * @param image
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int addUser(User user) throws ClassNotFoundException, SQLException, IOException;
	
	
	
	/**
	 * This method return list of all normal user
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<User> getAllUser() throws ClassNotFoundException, SQLException;
	
	/**
	 * This method will delete user from table
	 * @param userId
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void deleteUser(String userId) throws ClassNotFoundException, SQLException;
	
	/**
	 * This method will reset password of user
	 * @param obj
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void resetPassword(User obj) throws ClassNotFoundException, SQLException;
	
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<User> displayProfile(User user);
	
	
	/**
	 * 
	 * @param user
	 */
	public int updateProfile(User user);
	
	
	public List<User> displayUserDetails(int userId);
	
	public void getCSVFile(String startDate,String endDate) throws ClassNotFoundException, SQLException;
	
	public boolean checkEmail(String email);
	

}
