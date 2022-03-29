package com.usermanagement.DAO;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
	public boolean userLogin(User obj) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException;
	/**
	 * 
	 * @throws IOException
	 */
	public int userRegister(User obj) throws IOException;
	
	/**
	 * 
	 * @param addr_obj
	 */
	public int addAddress(int userId,Address addr_obj);
	
	
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
}
