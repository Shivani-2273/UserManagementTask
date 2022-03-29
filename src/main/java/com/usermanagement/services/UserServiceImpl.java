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

import com.usermanagement.DAO.UserDAOClass;
import com.usermanagement.model.Address;
import com.usermanagement.model.User;

public class UserServiceImpl implements UserServiceInterface {
	
	
	@Override
	public boolean compareLoginDetails(User obj) throws ClassNotFoundException, SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		UserDAOClass user=new UserDAOClass();
		boolean flag=user.userLogin(obj);
		return flag;
	}

	@Override
	public  int addUser(User obj) throws ClassNotFoundException, SQLException, IOException {
		UserDAOClass user=new UserDAOClass();
		int userId=user.userRegister(obj);
		return userId;
		
	}

	@Override
	public int addAddress(int userId,Address addr_obj) throws ClassNotFoundException, SQLException {
		UserDAOClass user=new UserDAOClass();
		int obj= user.addAddress(userId,addr_obj);
		return obj;
	}

	@Override
	public List<User> getAllUser(User obj) throws ClassNotFoundException, SQLException {
		UserDAOClass user=new UserDAOClass();
		List<User> list=user.getAllUser(obj);
		return list;
		
	}

	@Override
	public void deleteUser(String userId) throws ClassNotFoundException, SQLException {
		UserDAOClass user=new UserDAOClass();
		user.deleteUser(userId);
	}

	@Override
	public void resetPassword(User obj) throws ClassNotFoundException, SQLException {
		UserDAOClass user=new UserDAOClass();
		user.resetPassword(obj);
		
	}
	
	

	
}
