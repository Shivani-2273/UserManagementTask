package com.usermanagement.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usermanagement.model.Address;
import com.usermanagement.model.User;
import com.usermanagement.services.UserServiceImpl;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		UserServiceImpl service=new UserServiceImpl();
		
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));
		user.setEmail(request.getParameter("email"));
		user.setContactNo(request.getParameter("contact"));
		user.setGender(request.getParameter("gender"));
		user.setBirthDate(request.getParameter("birthDate"));	
		
		String languages="";
		String[] lang=request.getParameterValues("language");
		for(int i=0;i<lang.length;i++) {
			languages+=lang[i]+" ";
		}
		user.setLanguages(languages);
		
		String password=request.getParameter("password");	
		try {
			String input=password;
			byte[] encryptionBytes=com.usermanagement.utility.Encryption_Decryption.encrypt(input);
			String pass=new String(encryptionBytes);
			user.setPassword(pass);
			
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		int userId = 0;
		try {
			userId = service.addUser(user);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		
		String[] addressLine=request.getParameterValues("Address[]");
		String[] city=request.getParameterValues("City[]");
		String[] state=request.getParameterValues("State[]");
		String[] pin=request.getParameterValues("Pin[]");
		
		int loopCounter=0;
		while(loopCounter < addressLine.length) {
			Address addr_obj=new Address();
			addr_obj.setAddressLine(addressLine[loopCounter]);
			addr_obj.setCity(city[loopCounter]);
			addr_obj.setState(state[loopCounter]);
			addr_obj.setPin(pin[loopCounter]);
			
			try {
				int result=service.addAddress(userId,addr_obj);
				loopCounter++;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	
		RequestDispatcher req=request.getRequestDispatcher("UserLogin.jsp");
		req.forward(request, response);
		

		
	}

}
