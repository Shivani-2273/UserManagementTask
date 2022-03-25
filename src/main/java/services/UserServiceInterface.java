package services;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import model.Address;
import model.User;

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
	 * @param obj
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public void addUser(User obj) throws ClassNotFoundException, SQLException, IOException;
	
	/**
	 * 
	 * @param addr_obj
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void addAddress(Address addr_obj,int id) throws ClassNotFoundException, SQLException;

}
