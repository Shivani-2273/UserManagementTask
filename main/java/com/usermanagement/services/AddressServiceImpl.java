package com.usermanagement.services;

import java.sql.SQLException;
import java.util.List;

import com.usermanagement.DAO.AddressDAO;
import com.usermanagement.DAO.AddressDAOImpl;
import com.usermanagement.model.Address;

public class AddressServiceImpl implements AddressService {

	AddressDAO addressDao = new AddressDAOImpl();

	@Override
	public void addAddress(int userId,Address addr_obj) throws ClassNotFoundException, SQLException {
		addressDao.addAddress(userId,addr_obj);

	}

	@Override
	public List<Address> getDefaultAddress(int user_id) throws ClassNotFoundException, SQLException {
		List<Address> list = addressDao.getDefaultAddress(user_id);
		return list;
	}

	@Override
	public List<Address> getOtherAddress(int user_id) throws ClassNotFoundException, SQLException {
		List<Address> list = addressDao.getOtherAddress(user_id);
		return list;
	}

	@Override
	public void updateAddress(int userId,Address addr_obj) {
		addressDao.updateAddress(userId,addr_obj);
	}

	@Override
	public List<Address> getAddress(int userId) {
		List<Address> list=addressDao.getAddress(userId);
		return list;
	}

}
