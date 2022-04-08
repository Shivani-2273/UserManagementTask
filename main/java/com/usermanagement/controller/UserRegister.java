package com.usermanagement.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
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
import com.usermanagement.services.UserServiceImpl;
import com.usermanagement.services.AddressService;
import com.usermanagement.services.AddressServiceImpl;
import com.usermanagement.services.UserService;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
@MultipartConfig
public class UserRegister extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UserRegister.class.getName());


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegister() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		try {
			AddressService addressService = new AddressServiceImpl();
			UserService userService = new UserServiceImpl();

			//set all basic details of user
			user.setFirstName(request.getParameter("firstname"));
			user.setLastName(request.getParameter("lastname"));
			user.setEmail(request.getParameter("email"));
			user.setContactNo(request.getParameter("contact"));
			user.setGender(request.getParameter("gender"));
			user.setBirthDate(request.getParameter("birthDate"));

			
			Part img_file = request.getPart("img");
			InputStream image = img_file.getInputStream();
			user.setImage(image);
			
			String languages = "";
			String[] lang = request.getParameterValues("language");
			for (int i = 0; i < lang.length; i++) {
				languages += lang[i] + ",";
			}
			String check_languges=languages.substring(0,languages.length()-1);
			user.setLanguages(check_languges);

			//set password in encrypted format
			String password = request.getParameter("password");
			String input = password;
			byte[] encryptionBytes = com.usermanagement.utility.Encryption_Decryption.encrypt(input);
			String pass = new String(encryptionBytes);
			user.setPassword(pass);

			//call add user method to add user into database
			int userId=userService.addUser(user);
			
			//int is_default=Integer.parseInt(request.getParameter("is_default"));
			/*
			 * int[] is_default_values={}; String[]
			 * is_default=request.getParameterValues("is_default[]"); for(int
			 * i=0;i<is_default.length;i++) {
			 * is_default_values[i]=Integer.parseInt(is_default[i]); }
			 */
			
			//String[] is_default=request.getParameterValues("is_default[]");
			/*
			 * String[] is_default_values=request.getParameterValues("is_default[]");
			 * for(int i=0;i<is_default_values.length;i++) {
			 * System.out.print(is_default_values[i]); } int[] is_default={}; for(int
			 * i=0;i<is_default_values.length;i++) { if(is_default_values[i].equals("on")) {
			 * is_default[i]=1; }else { is_default[i]=0; } }
			 */
			
			String[] addressLine = request.getParameterValues("Address[]");
			String[] city = request.getParameterValues("City[]");
			String[] state = request.getParameterValues("State[]");
			String[] pin = request.getParameterValues("Pin[]");

			int loopCounter = 0;
			while (loopCounter < addressLine.length) {
				Address addr_obj = new Address();
				
				
				addr_obj.setAddressLine(addressLine[loopCounter]);
				addr_obj.setCity(city[loopCounter]);
				addr_obj.setState(state[loopCounter]);
				addr_obj.setPin(pin[loopCounter]);
				//addr_obj.setIsDefault(is_default[loopCounter]);

				addressService.addAddress(userId,addr_obj);
				loopCounter++;
			}
			HttpSession session=request.getSession();
			String userName=(String) session.getAttribute("userName");
			
			if(userName!=null) {
				response.sendRedirect("AdminDashboard.jsp");
			}else {
				response.sendRedirect("UserLogin.jsp");
			}
		

		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException | InvalidAlgorithmParameterException | ClassNotFoundException
				| SQLException e) {
			logger.info(e.toString());

		}

	}

}
