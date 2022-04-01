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
import javax.servlet.http.HttpSession;

import com.usermanagement.model.User;
import com.usermanagement.services.UserServiceImpl;
import com.usermanagement.services.UserService;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UserLogin.class.getName());


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

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
			
			//set email id and password for login
			user.setEmail(request.getParameter("email"));
			String password = request.getParameter("password");
			
			//password as encrypted format
			String input = password;
			byte[] encryptionBytes = com.usermanagement.utility.Encryption_Decryption.encrypt(input);
			String pass = new String(encryptionBytes);
			user.setPassword(pass);
			
			//if valid then check for status and not if not valid then redirect to login page
			boolean isValid = false;
			HttpSession session = request.getSession();

			isValid = userService.compareLoginDetails(user);

			if (isValid) {
				if (user.getIsAdmin()) {
					response.sendRedirect("AdminDashboard.jsp");
				} else {
					session.setAttribute("CurrentUser", user);
					response.sendRedirect("UserDashboard.jsp");
				}
			} else {
				RequestDispatcher req = request.getRequestDispatcher("UserLogin.jsp");
				req.forward(request, response);
			}
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException | InvalidAlgorithmParameterException | ClassNotFoundException
				| SQLException e) {
			logger.info(e.toString());

		}

	}

}
