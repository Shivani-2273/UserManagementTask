package com.usermanagement.controller;

import java.io.IOException;
import java.util.logging.Logger;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usermanagement.services.UserServiceImpl;
import com.usermanagement.services.UserService;

/**
 * Servlet implementation class deleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DeleteUser.class.getName());


	public DeleteUser() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String userId = request.getParameter("userId");
			UserService userService = new UserServiceImpl();

			userService.deleteUser(userId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.info(e.toString());

		}

	}

}
