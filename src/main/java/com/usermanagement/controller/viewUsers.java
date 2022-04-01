package com.usermanagement.controller;

import java.io.IOException;
import java.util.logging.Logger;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

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
 * Servlet implementation class viewUsers
 */
@WebServlet("/ViewUsers")
public class ViewUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ViewUsers.class.getName());


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewUsers() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			UserService userService = new UserServiceImpl();
			
			//get all user and store in list format
			List<User> list = userService.getAllUser();
			HttpSession session = request.getSession();
			
			//store list as session attribute and iterate this list on jsp page
			session.setAttribute("UserList", list);
			RequestDispatcher req = request.getRequestDispatcher("ViewUsers.jsp");
			req.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			logger.info(e.toString());

		}

	}

}
