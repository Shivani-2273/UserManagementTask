package com.usermanagement.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.usermanagement.model.Address;
import com.usermanagement.model.User;
import com.usermanagement.utility.MyConnection;

public class UserDAOClass implements UserDAOInterface{

	private static Logger logger=Logger.getLogger(UserDAOClass.class.getName());
	PreparedStatement pstmt=null;
	Connection connection=null;
	User user=new User();
	public UserDAOClass() throws ClassNotFoundException, SQLException {
		connection=MyConnection.getInstance().getConnection();
		}
	
	@Override
	public boolean userLogin(User obj) {
		try {
			pstmt=connection.prepareStatement("select * from user where email=? and password=?");
			pstmt.setString(1,obj.getEmail());
			pstmt.setString(2,obj.getPassword());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {	
				boolean status=rs.getBoolean(11);
				
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("password"));
				obj.setIsAdmin(status);
				pstmt=connection.prepareStatement("update user set updated_at =CURRENT_TIMESTAMP where email=? and is_admin=0");
				pstmt.setString(1, obj.getEmail());
				pstmt.executeUpdate();
				return true;
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public int userRegister(User obj,InputStream image) throws IOException {
		int id=0;
		try {
			pstmt=connection.prepareStatement("insert into user(first_name,last_name,email,password,contact_no,date_of_birth,language,gender,profile_image,is_admin) values(?,?,?,?,?,?,?,?,?,0)");
			pstmt.setString(1, obj.getFirstName());
			pstmt.setString(2, obj.getLastName());
			pstmt.setString(3, obj.getEmail());
			pstmt.setString(4,obj.getPassword());
			pstmt.setString(5,obj.getContactNo());
			pstmt.setString(6, obj.getBirthDate());
			pstmt.setString(7, obj.getLanguages());
			pstmt.setString(8, obj.getGender());
			pstmt.setBlob(9, image);
	
			pstmt.executeUpdate();	
			pstmt=connection.prepareStatement("select user_id from user");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				id=rs.getInt("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public int addAddress(int userId,Address addr_obj) {	
		
		int counter=0;
			try {
				PreparedStatement pstmt=connection.prepareStatement("insert into address(user_id,street_address_line,city,state,pin) values(?,?,?,?,?)");
				
				pstmt.setInt(1,userId);
				pstmt.setString(2,addr_obj.getAddressLine());
				pstmt.setString(3,addr_obj.getCity());
				pstmt.setString(4,addr_obj.getState());
				pstmt.setString(5,addr_obj.getPin());
				counter=pstmt.executeUpdate();
				if(counter!=0) {
					return counter;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return 0;	

	}

	@Override
	public List<User> getAllUser(User user) {
		List<User> list=new ArrayList<User>();	
		try {
				pstmt=connection.prepareStatement("select * from user where is_admin=0");
				ResultSet res=pstmt.executeQuery();
				while(res.next()) {
					user=new User();
					user.setUserId(res.getInt("user_id"));
					user.setFirstName(res.getString("first_name"));
					user.setLastName(res.getString("last_name"));
					user.setEmail(res.getString("email"));
					user.setContactNo(res.getString("contact_no"));
					user.setGender(res.getString("gender"));
					user.setBirthDate(res.getString("date_of_birth"));
					user.setLanguages(res.getString("language"));
					list.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
			
	}

	@Override
	public void deleteUser(String userId) {
		try {
			PreparedStatement pstmt=connection.prepareStatement("delete from user where user_id=?");
			pstmt.setString(1, userId);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resetPassword(User user) {
		try {
			PreparedStatement pstmt=connection.prepareStatement("update user set password=? where email=?");
			pstmt.setString(1,user.getPassword());
			pstmt.setString(2,user.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<User> getCSVFile(User user) {
		List<User> list=new ArrayList<User>();	
		try {
			pstmt=connection.prepareStatement("select first_name,last_name,updated_at from user");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				user=new User();
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setUpdatedAt(rs.getString("updated_at"));
				list.add(user);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public List<Address> getDefaultAddress(Address obj, int user_id) {
		List<Address> list=new ArrayList<Address>();
		try {
			pstmt=connection.prepareStatement("select * from address where user_id =? and is_default=1");
			pstmt.setInt(1, user_id);
			ResultSet res=pstmt.executeQuery();
			while(res.next()) {
				obj=new Address();
				obj.setAddressLine(res.getString("street_address_line"));
				obj.setCity(res.getString("city"));
				obj.setState(res.getString("state"));
				obj.setPin(res.getString("pin"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public List<Address> getOtherAddress(Address obj, int user_id) {
		List<Address> list=new ArrayList<Address>();
		try {
			pstmt=connection.prepareStatement("select * from address where user_id =? and is_default=0");
			pstmt.setInt(1, user_id);
			ResultSet res=pstmt.executeQuery();
			while(res.next()) {
				obj=new Address();
				obj.setAddressLine(res.getString("street_address_line"));
				obj.setCity(res.getString("city"));
				obj.setState(res.getString("state"));
				obj.setPin(res.getString("pin"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
	
	

}
