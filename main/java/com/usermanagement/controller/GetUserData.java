package com.usermanagement.controller;

import java.io.IOException;
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
	private static Logger logger = Logger.getLogger(GetUserData.class.getName());

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserData() {
        super();
      
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
 
    }
    	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			//get user id from url for updating user details by admin
			String userId=request.getParameter("userId");
			HttpSession session=request.getSession();
			UserService userService=new UserServiceImpl();
	
			//get data of user in form of list
			List<User> userData=userService.displayUserDetails(Integer.parseInt(userId));
	
			User user=userData.get(0);
			logger.info("User details"+user.toString());
			
			
			//store into session
			session.setAttribute("CurrentUser", user);
			AddressService addressService=new AddressServiceImpl();
			
			// getall address of user
			List<Address> allAddressList = addressService.getAddress(Integer.parseInt(userId));
			session.setAttribute("allAddressList", allAddressList);
			
		
			RequestDispatcher req = request.getRequestDispatcher("Register.jsp");
			req.forward(request, response); 
		}catch(Exception e) {
			logger.info(e.toString());
		}
	}
	

}
