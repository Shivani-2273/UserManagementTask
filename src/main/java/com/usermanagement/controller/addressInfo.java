package com.usermanagement.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.usermanagement.model.Address;
import com.usermanagement.services.AddressService;
import com.usermanagement.services.AddressServiceImpl;

/**
 * Servlet implementation class addressInfo
 */
@WebServlet("/AddressInfo")
public class AddressInfo extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AddressInfo.class.getName());


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddressInfo() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		try {
			AddressService addressService = new AddressServiceImpl();
			List<Address> defaultAddress = addressService.getDefaultAddress(user_id);
			System.out.println(defaultAddress);  
			HttpSession defaultAddressSession = request.getSession();
			defaultAddressSession.setAttribute("defaultAddress", defaultAddress);

			List<Address> otherAddress = addressService.getOtherAddress(user_id);
			defaultAddressSession.setAttribute("otherAddress", otherAddress);
			RequestDispatcher req = request.getRequestDispatcher("AddressInfo.jsp");
			req.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			logger.info(e.toString());
		}

	}

}
