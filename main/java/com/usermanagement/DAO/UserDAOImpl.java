package com.usermanagement.DAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.usermanagement.model.User;
import com.usermanagement.utility.MyConnection;

public class UserDAOImpl implements UserDAO {

	Connection connection;
	private static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
	PreparedStatement pstmt = null;
	Statement stmt=null;
	ResultSet rs = null; 
	public UserDAOImpl() {
		try {
			connection = MyConnection.getInstance().getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			logger.info(e.toString());

		}
	}

	
	@Override
	public boolean userLogin(User obj,Map<String,String> message) throws SQLException {
		logger.info("User Data"+obj.toString());
		
		try {
			pstmt = connection.prepareStatement("select * from user where email=? and password=?");
			pstmt.setString(1, obj.getEmail());
			pstmt.setString(2, obj.getPassword());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				boolean status = rs.getBoolean(11);
				obj.setUserId(rs.getInt("user_id"));
				obj.setFirstName(rs.getString("first_name"));
				obj.setLastName(rs.getString("last_name"));
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("password"));
				obj.setContactNo(rs.getString("contact_no"));
				obj.setGender(rs.getString("gender"));
				obj.setBirthDate(rs.getString("date_of_birth"));
				obj.setLanguages(rs.getString("language"));
			
				Blob blob = rs.getBlob("profile_image");
				byte[] photo = blob.getBytes(1, (int) blob.length());
				String base64Image = Base64.getEncoder().encodeToString(photo);
				obj.setBase64Image(base64Image);
			
				obj.setIsAdmin(status);
				
				pstmt = connection
						.prepareStatement("update user set updated_at =CURRENT_TIMESTAMP where email=? and is_admin=0");
				pstmt.setString(1, obj.getEmail());
				pstmt.executeUpdate();
				return true;

			}else {
				message.put("message","Invalid  email id and password.");
				return false;
			}

		} catch (SQLException e) {
			logger.info(e.toString());

		}finally {
			rs.close();
			pstmt.close();
			
			
		}

		return false;
	}

	@Override
	public int userRegister(User obj) throws IOException, SQLException {
		logger.info("User Data"+obj.toString());
		int id = 0;

		try {
			pstmt = connection.prepareStatement(
					"insert into user(first_name,last_name,email,password,contact_no,date_of_birth,language,gender,profile_image,is_admin) values(?,?,?,?,?,?,?,?,?,0	)");
			pstmt.setString(1, obj.getFirstName());
			pstmt.setString(2, obj.getLastName());
			pstmt.setString(3, obj.getEmail());
			pstmt.setString(4, obj.getPassword());
			pstmt.setString(5, obj.getContactNo());
			pstmt.setString(6, obj.getBirthDate());
			pstmt.setString(7, obj.getLanguages());
			pstmt.setString(8, obj.getGender());
			pstmt.setBlob(9, obj.getImage());
			
			pstmt.executeUpdate();
			pstmt=connection.prepareStatement("select user_id from user");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				id=rs.getInt("user_id");
			}

		} catch (SQLException e) {
			logger.info(e.toString());

		}finally {
			rs.close();
		}
		return id;

	}

	@Override
	public List<User> getAllUser() throws SQLException {
		List<User> list = new ArrayList<User>();
		try {
			pstmt = connection.prepareStatement("select * from user where is_admin=0");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setContactNo(rs.getString("contact_no"));
				user.setGender(rs.getString("gender"));
				user.setBirthDate(rs.getString("date_of_birth"));
				user.setLanguages(rs.getString("language"));
				
				Blob blob = rs.getBlob("profile_image");
				byte[] photo = blob.getBytes(1, (int) blob.length());
				String base64Image = Base64.getEncoder().encodeToString(photo);
				user.setBase64Image(base64Image);
				list.add(user);
				logger.info("All user details"+user.toString());
			}
		} catch (SQLException e) {
			logger.info(e.toString());

		}finally {
				rs.close();
		
		}
		return list;

	}

	@Override
	public void deleteUser(String userId) throws SQLException {
		try {
			
			 pstmt = connection.prepareStatement("DELETE FROM user WHERE user_id=?");
			 pstmt.setString(1, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.info(e.toString());

		}finally {
			pstmt.close();
		}
	}

	@Override
	public void resetPassword(User user) {
		try {
			pstmt = connection.prepareStatement("update user set password=? where email=?");
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.info(e.toString());
		}

	}

	@Override
	public List<User> displayProfile(User user) throws SQLException {
		List<User> list = new ArrayList<User>();

		try {
			pstmt=connection.prepareStatement("select * from user where email=? and password=?");
			pstmt.setString(1,user.getEmail());
			pstmt.setString(2,user.getPassword());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setContactNo(rs.getString("contact_no"));
				user.setGender(rs.getString("gender"));
				user.setBirthDate(rs.getString("date_of_birth"));
				user.setLanguages(rs.getString("language"));
				
				Blob blob = rs.getBlob("profile_image");
				byte[] photo = blob.getBytes(1, (int) blob.length());
				String base64Image = Base64.getEncoder().encodeToString(photo);
				user.setBase64Image(base64Image);
				list.add(user);
				logger.info("profile list"+user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			rs.close();
		}
		return list;
			
		
	}

	@Override
	public int updateProfile(User user) {
		System.out.println("in update profile function");
		int id=0;
		try {
			pstmt=connection.prepareStatement("UPDATE user SET first_name=?, last_name=?, contact_no=?, date_of_birth=?,language=? ,gender=?,profile_image=? WHERE email=? ");
			pstmt.setString(1,user.getFirstName());
			pstmt.setString(2,user.getLastName());
			pstmt.setString(3,user.getContactNo());
			pstmt.setString(4,user.getBirthDate());
			pstmt.setString(5,user.getLanguages());
			pstmt.setString(6, user.getGender());
			pstmt.setBlob(7,user.getImage());
			pstmt.setString(8, user.getEmail());
			pstmt.executeUpdate();
			id=user.getUserId();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void getCSVFile(String startDate,String endDate) throws SQLException {	
		try {
	
			File file = new File("S:\\UserLoginInfo_CSV\\Login.csv");
			Writer w = new OutputStreamWriter(new FileOutputStream(file),Charset.forName("UTF-8").newEncoder());
			PrintWriter fw = new PrintWriter(w);
			//FileWriter fw = new FileWriter("S:\\UserLoginInfo_CSV\\Login.csv");
				fw.append("First Name");
				fw.append(',');
				fw.append("Last Name");
				fw.append(',');
				fw.append("Date/Time");
				fw.append('\n');
			pstmt=connection.prepareStatement("select first_name,last_name,updated_at from user where is_admin=0 and DATE(updated_at) BETWEEN ? and ? ");
		
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				fw.append(rs.getString(1));
				fw.append(',');
				fw.append(rs.getString(2));
				fw.append(',');
				fw.append(rs.getString(3));
				fw.append('\n');
			}
			fw.flush();
			fw.close();
			
		} catch (IOException | SQLException e) {
			logger.info(e.toString());
		}finally {
				rs.close();
		}
		
	}


	@Override
	public boolean checkEmail(String email) throws SQLException {
		try {
			pstmt=connection.prepareStatement("select email from user where email=?");
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			while(rs.next()) 
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			rs.close();
		}
		return false;
		
		
	}


	@Override
	public List<User> displayUserDetails(int userId) throws SQLException {
		List<User> list = new ArrayList<User>();
		try {
			pstmt=connection.prepareStatement("select * from user where user_id=?");
			pstmt.setInt(1, userId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				User user=new User();
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setContactNo(rs.getString("contact_no"));
				user.setGender(rs.getString("gender"));
				user.setPassword(rs.getString("password"));
				user.setBirthDate(rs.getString("date_of_birth"));
				user.setLanguages(rs.getString("language"));
				
				Blob blob = rs.getBlob("profile_image");
				byte[] photo = blob.getBytes(1, (int) blob.length());
				String base64Image = Base64.getEncoder().encodeToString(photo);
				user.setBase64Image(base64Image);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			rs.close();
		}
		return list;
	}


}

