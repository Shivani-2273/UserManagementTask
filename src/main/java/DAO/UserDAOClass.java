package DAO;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import model.Address;
import model.User;

import utility.Encryption_Decryption;
import utility.MyConnection;

public class UserDAOClass implements UserDAOInterface{

	
	PreparedStatement pstmt=null;
	Connection connection=null;
	
	public UserDAOClass() throws ClassNotFoundException, SQLException {
		connection=MyConnection.getInstance().getConnection();
	}
	
	@Override
	public boolean userLogin(User obj) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		try {
			pstmt=connection.prepareStatement("select * from user where email=?");
			pstmt.setString(1,obj.getEmail());
			//pstmt.setString(2, obj.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {	
				boolean status=rs.getBoolean(11);
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("password"));
				obj.setIsAdmin(status);
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public void userRegister(User obj) throws IOException {
		try {
			pstmt=connection.prepareStatement("insert into user(first_name,last_name,email,password,contact_no,date_of_birth,language,gender,is_admin) values(?,?,?,?,?,?,?,?,0)");
			pstmt.setString(1, obj.getFirstName());
			pstmt.setString(2,obj.getLastName());
			pstmt.setString(3, obj.getEmail());
			pstmt.setString(4,obj.getPassword());
			pstmt.setString(5,obj.getContactNo());
			pstmt.setString(6, obj.getBirthDate());
			pstmt.setString(7, obj.getLanguages());
			pstmt.setString(8, obj.getGender());
			//pstmt.setBlob(9, obj.getBase64Image());
			
			/*Blob blob=rs.getBlob("image");
			InputStream is=blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = is.read(buffer)) != -1) {
			    outputStream.write(buffer, 0, bytesRead);
			}
			 
			byte[] imageBytes = outputStream.toByteArray();
			 
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			is.close();
			outputStream.close();
			obj.setBase64Image(base64Image);
			*/
			
		

			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAddress(Address addr_obj,int id) {
		try {
			pstmt=connection.prepareStatement("insert into address(user_id,street_address_line,city,state,pincode) values(?,?,?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, addr_obj.getAddress());
			pstmt.setString(3, addr_obj.getCity());
			pstmt.setString(4, addr_obj.getState());
			pstmt.setString(5, addr_obj.getPin());
			
			pstmt.executeUpdate();	

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	
	
	
	

}
