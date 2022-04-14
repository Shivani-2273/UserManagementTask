package com.usermanagement.DAO;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.usermanagement.model.User;

public interface UserDAO {

	/**
	 * This method will return true if login details are correct 
	 * @param obj
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 * @throws SQLException 
	 */
	 boolean userLogin(User obj,Map<String,String> message) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, SQLException;

	/**
	 * This method will add details of user into user table of database
	 * @param obj
	 * @param image
	 * @return
	 * @throws IOException
	 * @throws SQLException 
	 */
	 int userRegister(User obj) throws IOException, SQLException;

	
	/**
	 * This method return list of all normal user
	 * @return
	 * @throws SQLException 
	 */
	 List<User> getAllUser() throws SQLException;

	/**
	 * This method will delete user from table
	 * @param userId
	 * @throws SQLException 
	 */
	 void deleteUser(String userId) throws SQLException;

	/**
	 * This method will reset password of user
	 * @param user
	 */
	 void resetPassword(User user);

	/**
	 * This method will display user profile on home page after login
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	 List<User> displayProfile(User user) throws SQLException;
	
	/**
	 * This method will update user profile and store updated details in database
	 * @param user
	 */
	 int updateProfile(User user);
	
	
	/**
	 * This will generate csv file
	 * @param startDate
	 * @param endDate
	 * @throws SQLException 
	 */
	 void  getCSVFile(String startDate,String endDate) throws SQLException;
	
	
	
	/**
	 * This will compare email with stored email
	 * @param email
	 * @return
	 * @throws SQLException 
	 */
	 boolean checkEmail(String email) throws SQLException;

	/**
	 * this is will details of user
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	 List<User> displayUserDetails(int userId) throws SQLException;

	
}
