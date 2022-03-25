package services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import DAO.UserDAOClass;
import model.Address;
import model.User;

public class UserServiceImpl implements UserServiceInterface {
	
	
	@Override
	public boolean compareLoginDetails(User obj) throws ClassNotFoundException, SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		UserDAOClass user=new UserDAOClass();
		boolean flag=user.userLogin(obj);
		return flag;
	}

	@Override
	public void addUser(User obj) throws ClassNotFoundException, SQLException, IOException {
		UserDAOClass user=new UserDAOClass();
		user.userRegister(obj);
		
	}

	@Override
	public void addAddress(Address addr_obj,int id) throws ClassNotFoundException, SQLException {
		UserDAOClass user=new UserDAOClass();
		user.addAddress(addr_obj,id);
	}

	
}
