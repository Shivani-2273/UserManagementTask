package DAO;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import model.Address;
import model.User;
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
	 * @param obj
	 * @throws IOException 
	 */
	public void userRegister(User obj) throws IOException;
	
	/**
	 * 
	 * @param addr_obj
	 */
	public void addAddress(Address addr_obj,int id);
}
