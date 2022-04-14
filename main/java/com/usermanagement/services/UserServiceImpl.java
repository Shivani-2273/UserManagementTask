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

import com.usermanagement.DAO.UserDAOImpl;
import com.usermanagement.DAO.UserDAO;
import com.usermanagement.model.User;

public class UserServiceImpl implements UserService {
	
	UserDAO userDao=new UserDAOImpl();
	
	@Override
	public boolean compareLoginDetails(User obj,Map<String,String> message) throws ClassNotFoundException, SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		boolean flag=userDao.userLogin(obj,message);
		return flag;
	}

	@Override
	public  int addUser(User obj) throws ClassNotFoundException, SQLException, IOException {
		int userId=userDao.userRegister(obj);		
		return userId;
		
	}

	@Override
	public List<User> getAllUser() throws ClassNotFoundException, SQLException {
		List<User> list=userDao.getAllUser();
		return list;
		
	}

	@Override
	public void deleteUser(String userId) throws ClassNotFoundException, SQLException {
		userDao.deleteUser(userId);
	}

	@Override
	public void resetPassword(User obj) throws ClassNotFoundException, SQLException {
		userDao.resetPassword(obj);
		
	}

	@Override
	public List<User> displayProfile(User user) throws SQLException {
		List<User> list=userDao.displayProfile(user);
		return list;
	}

	@Override
	public int updateProfile(User user) {
		int userId=userDao.updateProfile(user);
		return userId;
	}

	@Override
	public void getCSVFile(String startDate,String endDate) throws ClassNotFoundException, SQLException {
		userDao.getCSVFile(startDate,endDate);
		
	}

	@Override
	public boolean checkEmail(String email) throws SQLException {
		boolean flag=userDao.checkEmail(email);
		return flag;
	}

	@Override
	public List<User> displayUserDetails(int userId) throws SQLException {
		List<User> list=userDao.displayUserDetails(userId);
		return list;
	}
	
	

		
	
	

	
}
