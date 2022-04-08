package com.usermanagement.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.usermanagement.model.Address;
import com.usermanagement.model.User;
import com.usermanagement.services.AddressService;
import com.usermanagement.services.AddressServiceImpl;
import com.usermanagement.services.UserService;
import com.usermanagement.services.UserServiceImpl;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
@MultipartConfig
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ForgotPassword.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Address> list = (List<Address>) session.getAttribute("allAddressList");
		
		User user = (User) session.getAttribute("CurrentUser");
		Address addr_obj = new Address();
		try {
			UserService userService = new UserServiceImpl();
			AddressService addressService = new AddressServiceImpl();
			user.setFirstName(request.getParameter("firstname"));
			user.setLastName(request.getParameter("lastname"));
			user.setEmail(request.getParameter("email"));
			user.setContactNo(request.getParameter("contact"));

			String languages = "";
			String[] lang = request.getParameterValues("language");
			for (int i = 0; i < lang.length; i++) {
				languages += lang[i] + ",";
			}
			String check_languges = languages.substring(0, languages.length() - 1);
			user.setLanguages(check_languges);

			user.setBirthDate(request.getParameter("birthDate"));
			user.setGender(request.getParameter("gender"));

			Part img_file = request.getPart("img");
			InputStream image = img_file.getInputStream();
			user.setImage(image);

			int userId = userService.updateProfile(user);

			//for update address on edit page
			/*
			 * int addressId[] = new int[list.size()]; for (int i = 0; i < list.size(); i++)
			 * { addressId[i] = list.get(i).getAddressId(); }
			 */
			
			String[] addressId=request.getParameterValues("addressId[]");
			String[] addressLine = request.getParameterValues("Address[]");
			String[] city = request.getParameterValues("City[]");
			String[] state = request.getParameterValues("State[]");
			String[] pin = request.getParameterValues("Pin[]");

		
			
			
			int loopCounter = 0;
			while (loopCounter < addressLine.length) {

				//for update changes
				//addr_obj.setAddressId(addressId[loopCounter]);
				addr_obj.setAddressId(addressId[loopCounter]);
				addr_obj.setAddressLine(addressLine[loopCounter]);
				addr_obj.setCity(city[loopCounter]);
				addr_obj.setState(state[loopCounter]);
				addr_obj.setPin(pin[loopCounter]);

				addressService.updateAddress(userId, addr_obj);

				loopCounter++;
			}

			String userName = (String) session.getAttribute("userName");
			if (userName.equals("adminEdit")) {
				response.sendRedirect("AdminDashboard.jsp");
			} else {
				response.sendRedirect("UserDashboard.jsp");
			}

		} catch (Exception e) {
			logger.info(e.toString());
		}

	}

}
