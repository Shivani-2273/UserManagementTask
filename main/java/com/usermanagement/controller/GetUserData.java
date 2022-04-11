package com.usermanagement.controller;

import java.io.IOException;
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
import com.usermanagement.services.AddressService;
import com.usermanagement.services.AddressServiceImpl;
import com.usermanagement.services.UserService;
import com.usermanagement.services.UserServiceImpl;

/**
 * Servlet implementation class GetUserData
 */
@WebServlet("/GetUserData")
public class GetUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserData() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }
    	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userId=request.getParameter("userId");
		HttpSession session=request.getSession();
		UserService userService=new UserServiceImpl();
		
		List<User> userData=userService.displayUserDetails(Integer.parseInt(userId));
		
		
		User user=userData.get(0);
		
		session.setAttribute("CurrentUser", user);
	
		AddressService addressService=new AddressServiceImpl();
		// getall address
		List<Address> allAddressList = addressService.getAddress(Integer.parseInt(userId));
		session.setAttribute("allAddressList", allAddressList);
		
	
		RequestDispatcher req = request.getRequestDispatcher("Register.jsp?user=adminEdit");
		req.forward(request, response); 

	}
	

}
