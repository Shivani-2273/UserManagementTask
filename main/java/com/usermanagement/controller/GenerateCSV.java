package com.usermanagement.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usermanagement.services.UserServiceImpl;
import com.usermanagement.services.UserService;

/**
 * Servlet implementation class GenerateCSV
 */
@WebServlet("/GenerateCSV")
public class GenerateCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateCSV() {
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
		try {
			//get start date and end date for getting login info of users
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			UserService userService = new UserServiceImpl();
			
			//all method to generate csv file
			userService.getCSVFile(startDate, endDate);
			
			RequestDispatcher req = request.getRequestDispatcher("LoginInfo.jsp");
			req.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
