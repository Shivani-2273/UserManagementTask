package com.usermanagement.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.usermanagement.model.Address;
import com.usermanagement.model.User;
import com.usermanagement.services.UserServiceImpl;

/**
 * Servlet implementation class addressInfo
 */
@WebServlet("/addressInfo")
public class addressInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addressInfo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Address addr_obj=new Address();
		User user=new User();
		int user_id=Integer.parseInt(request.getParameter("user_id"));
		UserServiceImpl service=new UserServiceImpl();
		
		try {
			List<Address> defaultAddress=service.getDefaultAddress(addr_obj, user_id);
			HttpSession session=request.getSession();
			session.setAttribute("defaultAddress",defaultAddress);
			
			List<Address> otherAddress=service.getOtherAddress(addr_obj, user_id);
			session.setAttribute("otherAddress", otherAddress);
			RequestDispatcher req = request.getRequestDispatcher("AddressInfo.jsp");
			req.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
