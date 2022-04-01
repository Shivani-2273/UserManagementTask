package com.usermanagement.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.usermanagement.model.User;
import com.usermanagement.utility.MyConnection;

public class UserDAOImpl implements UserDAO {

	Connection connection;
	private static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
	PreparedStatement pstmt = null;

	public UserDAOImpl() {
		try {
			connection = MyConnection.getInstance().getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			logger.info(e.toString());

		}
	}

	User user = new User();

	@Override
	public boolean userLogin(User obj) {
		logger.info("User Data"+obj.toString());

		try {
			pstmt = connection.prepareStatement("select * from user where email=? and password=?");
			pstmt.setString(1, obj.getEmail());
			pstmt.setString(2, obj.getPassword());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				boolean status = rs.getBoolean(11);

				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("password"));
				obj.setIsAdmin(status);
				pstmt = connection
						.prepareStatement("update user set updated_at =CURRENT_TIMESTAMP where email=? and is_admin=0");
				pstmt.setString(1, obj.getEmail());
				pstmt.executeUpdate();
				return true;
			}

		} catch (SQLException e) {
			logger.info(e.toString());

		}

		return false;
	}

	@Override
	public int userRegister(User obj, InputStream image) throws IOException {
		logger.info("User Data"+obj.toString());
		int id = 0;
		try {
			pstmt = connection.prepareStatement(
					"insert into user(first_name,last_name,email,password,contact_no,date_of_birth,language,gender,profile_image,is_admin) values(?,?,?,?,?,?,?,?,?,0)");
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
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				id=rs.getInt("user_id");
			}

		} catch (SQLException e) {
			logger.info(e.toString());

		}
		return id;

	}

	@Override
	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();
		try {
			pstmt = connection.prepareStatement("select * from user where is_admin=0");
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				User user = new User();
				user.setUserId(res.getInt("user_id"));
				user.setFirstName(res.getString("first_name"));
				user.setLastName(res.getString("last_name"));
				user.setEmail(res.getString("email"));
				user.setContactNo(res.getString("contact_no"));
				user.setGender(res.getString("gender"));
				user.setBirthDate(res.getString("date_of_birth"));
				user.setLanguages(res.getString("language"));
				Blob blob = res.getBlob("profile_image");
				byte[] photo = blob.getBytes(1, (int) blob.length());
				String base64Image = Base64.getEncoder().encodeToString(photo);
				user.setBase64Image(base64Image);
				list.add(user);
			}
		} catch (SQLException e) {
			logger.info(e.toString());

		}
		return list;

	}

	@Override
	public void deleteUser(String userId) {
		try {
			
			Statement stmt = connection.createStatement();
			String deleteQuery = "DELETE FROM user WHERE user_id='" + userId + "'";
			stmt.executeUpdate(deleteQuery);
		} catch (SQLException e) {
			logger.info(e.toString());

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

	/*
	 * public List<User> getCSVFile(User user) { List<User> list = new
	 * ArrayList<User>(); try { pstmt = connection.
	 * prepareStatement("select first_name,last_name,updated_at from user");
	 * ResultSet rs = pstmt.executeQuery(); while (rs.next()) { user = new User();
	 * user.setFirstName(rs.getString("first_name"));
	 * user.setLastName(rs.getString("last_name"));
	 * user.setUpdatedAt(rs.getString("updated_at")); list.add(user);
	 * 
	 * } } catch (SQLException e) { e.printStackTrace(); } return list;
	 * 
	 * }
	 */
}
