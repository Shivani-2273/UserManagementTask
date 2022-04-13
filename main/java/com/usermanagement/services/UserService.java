package com.usermanagement.services;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
	 boolean compareLoginDetails(User obj) throws ClassNotFoundException, SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException;
	
	
	/**
	 * This method will add details of user into user table of database
	 * @param user
	 * @param image
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	 int addUser(User user) throws ClassNotFoundException, SQLException, IOException;
	
	
	
	/**
	 * This method return list of all normal user
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	 List<User> getAllUser() throws ClassNotFoundException, SQLException;
	
	/**
	 * This method will delete user from table
	 * @param userId
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	 void deleteUser(String userId) throws ClassNotFoundException, SQLException;
	
	/**
	 * This method will reset password of user
	 * @param obj
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	 void resetPassword(User obj) throws ClassNotFoundException, SQLException;
	
	
	/**
	 * 
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	 List<User> displayProfile(User user) throws SQLException;
	
	
	/**
	 * 
	 * @param user
	 */
	 int updateProfile(User user);
	
	/**
	 * This will display user details 
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	 List<User> displayUserDetails(int userId) throws SQLException;
	
	/**
	 * this is will generate csv file
	 * @param startDate
	 * @param endDate
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	 void getCSVFile(String startDate,String endDate) throws ClassNotFoundException, SQLException;
	
	/**
	 * this is compare email with stored email in database
	 * @param email
	 * @return
	 * @throws SQLException 
	 */
	 boolean checkEmail(String email) throws SQLException;
	

}
