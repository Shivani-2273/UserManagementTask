package com.usermanagement.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.usermanagement.model.User;
import com.usermanagement.services.UserService;
import com.usermanagement.services.UserServiceImpl;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static Logger logger = Logger.getLogger(EditProfile.class.getName());

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfile() {
		super();	
	}

	
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

			HttpSession session = request.getSession();
			User user=new User();
			
			user.setFirstName(request.getParameter("firstname"));
			user.setLastName(request.getParameter("lastname"));
			user.setEmail(request.getParameter("email"));
			user.setContactNo(request.getParameter("contact"));
			user.setPassword(request.getParameter("password"));
			
			String languages = "";
			String[] lang = request.getParameterValues("language");
			for (int i = 0; i < lang.length; i++) {
				languages += lang[i] + " ";
			}
			user.setLanguages(languages);
			
			user.setBirthDate(request.getParameter("birthDate"));
			user.setGender(request.getParameter("gender"));

			Part img_file = request.getPart("img");
			InputStream image = img_file.getInputStream();
			user.setImage(image);

			userService.updateProfile(user);
			RequestDispatcher req = request.getRequestDispatcher("AdminDashboard.jsp");
			req.forward(request, response);

		} catch (Exception e) {
			logger.info(e.toString());
		}

	}

}
