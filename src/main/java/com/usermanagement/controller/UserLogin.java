package com.usermanagement.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.usermanagement.model.User;
import com.usermanagement.services.UserServiceImpl;
import com.usermanagement.utility.Encryption_Decryption;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		user.setEmail(request.getParameter("email"));
		//user.setPassword(request.getParameter("password"));
		String password=request.getParameter("password");
	
		try {
			String input=password;
			byte[] encryptionBytes=com.usermanagement.utility.Encryption_Decryption.encrypt(input);
			String pass=new String(encryptionBytes);
			user.setPassword(pass);	
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	
		UserServiceImpl service=new UserServiceImpl();
		boolean isValid = false;
		HttpSession session=request.getSession();
		
		try {
			isValid=service.compareLoginDetails(user);
		} catch (SQLException | InvalidKeyException | ClassNotFoundException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}		
		if(isValid) {
			if(user.getIsAdmin()) {
				response.sendRedirect("AdminDashboard.jsp");
			}else {
				session.setAttribute("CurrentUser", user);
				response.sendRedirect("UserDashboard.jsp");
			}
		}else{
			RequestDispatcher req=request.getRequestDispatcher("UserLogin.jsp");
			req.forward(request, response);
		}
		
		
	}

}
