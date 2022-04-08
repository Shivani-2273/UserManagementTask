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
	 */
	public boolean userLogin(User obj) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException;

	/**
	 * This method will add details of user into user table of database
	 * @param obj
	 * @param image
	 * @return
	 * @throws IOException
	 */
	public int userRegister(User obj) throws IOException;

	
	/**
	 * This method return list of all normal user
	 * @return
	 */
	public List<User> getAllUser();

	/**
	 * This method will delete user from table
	 * @param userId
	 */
	public void deleteUser(String userId);

	/**
	 * This method will reset password of user
	 * @param user
	 */
	public void resetPassword(User user);

	/**
	 * This method will display user profile on home page after login
	 * @param user
	 * @return
	 */
	public List<User> displayProfile(User user);
	
	/**
	 * This method will update user profile and store updated details in database
	 * @param user
	 */
	public int updateProfile(User user);
	
	public void  getCSVFile(String startDate,String endDate);
	
	public boolean checkEmail(String email);

	
}
