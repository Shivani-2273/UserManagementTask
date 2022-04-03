package com.usermanagement.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usermanagement.model.Address;
import com.usermanagement.utility.MyConnection;

import java.util.logging.Logger;

public class AddressDAOImpl implements AddressDAO {

	Connection connection;
	PreparedStatement pstmt = null;
	private static Logger logger = Logger.getLogger(AddressDAOImpl.class.getName());

	public AddressDAOImpl() {
		try {
			connection = MyConnection.getInstance().getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			logger.info(e.toString());

		}
	}

	@Override
	public void addAddress(int userId,Address addr_obj) {
		try {
			PreparedStatement pstmt = connection.prepareStatement(
					"insert into address(user_id,street_address_line,city,state,pin,is_default) values(?,?,?,?,?,?)");

			pstmt.setInt(1,userId);
			pstmt.setString(2, addr_obj.getAddressLine());
			pstmt.setString(3, addr_obj.getCity());
			pstmt.setString(4, addr_obj.getState());
			pstmt.setString(5, addr_obj.getPin());
			pstmt.setInt(6,addr_obj.getIsDefault());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.info(e.toString());

		}
	}

	@Override
	public List<Address> getDefaultAddress( int user_id) {
		List<Address> list = new ArrayList<Address>();
		
		try {
			pstmt = connection.prepareStatement("select * from address where user_id =? and is_default=1");
			pstmt.setInt(1, user_id);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				//boolean is_default=res.getBoolean("is_default")
				Address obj = new Address();
				obj.setAddressId(res.getInt("address_id"));
				obj.setUserId(res.getInt("user_id"));
				obj.setAddressLine(res.getString("street_address_line"));
				obj.setCity(res.getString("city"));
				obj.setState(res.getString("state"));
				obj.setPin(res.getString("pin"));
			
				list.add(obj);
			}
		} catch (SQLException e) {
			logger.info(e.toString());

		}
		return list;

	}

	@Override
	public List<Address> getOtherAddress(int user_id) {
		List<Address> list = new ArrayList<Address>();
		try {
			pstmt = connection.prepareStatement("select * from address where user_id =? and is_default=0");
			pstmt.setInt(1, user_id);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				Address	obj = new Address();
				obj.setAddressLine(res.getString("street_address_line"));
				obj.setCity(res.getString("city"));
				obj.setState(res.getString("state"));
				obj.setPin(res.getString("pin"));
				list.add(obj);
			}
		} catch (SQLException e) {
			logger.info(e.toString());

		}
		return list;
	}
}
