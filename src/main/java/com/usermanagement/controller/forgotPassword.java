package com.usermanagement.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usermanagement.model.User;
import com.usermanagement.services.UserServiceImpl;
import com.usermanagement.services.UserService;

/**
 * Servlet implementation class forgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ForgotPassword.class.getName());

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPassword() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		try {
			UserService userService = new UserServiceImpl();
			
			//set email and password reset password
			user.setEmail(request.getParameter("email"));
			String password = request.getParameter("password");

			//password as encrypted format
			String input = password;
			byte[] encryptionBytes = com.usermanagement.utility.Encryption_Decryption.encrypt(input);
			String pass = new String(encryptionBytes);
			user.setPassword(pass);

			//call reset password method
			userService.resetPassword(user);
			RequestDispatcher req = request.getRequestDispatcher("UserLogin.jsp");
			req.forward(request, response);

		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException | InvalidAlgorithmParameterException | ClassNotFoundException | SQLException e) {
			logger.info(e.toString());

		}
	}

}
