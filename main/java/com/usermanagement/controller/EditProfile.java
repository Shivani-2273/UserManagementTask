package com.usermanagement.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
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
		logger.info("address list of logged in user" + list.toString());
		User user = (User) session.getAttribute("CurrentUser");
		logger.info("Current logged in user details" + user.toString());

		Address addr_obj = new Address();
		try {
			UserService userService = new UserServiceImpl();
			AddressService addressService = new AddressServiceImpl();
			// get details of user
			user.setFirstName(request.getParameter("firstname"));
			user.setLastName(request.getParameter("lastname"));
			user.setEmail(request.getParameter("email"));
			user.setContactNo(request.getParameter("contact"));


			String[] lang = request.getParameterValues("language");
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < lang.length; ++i) {
			    buffer.append(lang[i]+",");
			}
			String languages = buffer.toString();
			String savedString=languages.substring(0, languages.length()-1);

			user.setLanguages(savedString);
			
			user.setBirthDate(request.getParameter("birthDate"));
			user.setGender(request.getParameter("gender"));

			Part img_file = request.getPart("img");
			if(img_file.getSize() == 0) {
				String base64Image=request.getParameter("oldImg");
				InputStream imageStream=new ByteArrayInputStream(base64Image.getBytes());
				InputStream is=Base64.getDecoder().wrap(imageStream);
				user.setImage(is);
			}else {
				InputStream image = img_file.getInputStream();
				user.setImage(image);
			}			
			
			// call update profile method to update user details
			int userId = userService.updateProfile(user);

			// get address id from database
			String addrId[] = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				addrId[i] = list.get(i).getAddressId();

			}

			// get address id from hidden field
			String[] addressId = request.getParameterValues("addressId[]");
//			14.04		
			List<String> addressIdList = Arrays.asList(addressId);
			String remove = "";
			for (int i = 0; i < addrId.length; i++) {
				if (!addressIdList.contains(addrId[i])) {
					remove += addrId[i] + " ";
				}
			}

//			StringBuffer buf=new StringBuffer();
//			for (int i = 0; i < addrId.length; i++) {
//				buf.append(addrId[i]+" ");
//			}
//			String remove=buf.toString();
			
			
			
			
			
			// get address details and store into array
			String[] addressLine = request.getParameterValues("Address[]");
			String[] city = request.getParameterValues("City[]");
			String[] state = request.getParameterValues("State[]");
			String[] pin = request.getParameterValues("Pin[]");

			int loopCounter = 0;
			while (loopCounter < addressLine.length) {

				// for update changes
				addr_obj.setAddressId(addressId[loopCounter]);
				addr_obj.setAddressLine(addressLine[loopCounter]);
				addr_obj.setCity(city[loopCounter]);
				addr_obj.setState(state[loopCounter]);
				addr_obj.setPin(pin[loopCounter]);
				// set remove address id for delete
				addr_obj.setRemoveAddressId(remove);

				// call update address method to update address details
				addressService.updateAddress(userId, addr_obj);

				loopCounter++;
			}

			// get user name from url to redirect page after updating details
			String userName = (String) session.getAttribute("userName");
			if (userName.equals("adminEdit")) {
				response.sendRedirect("AdminDashboard.jsp");
			} else {
				
				List<User> Profilelist = userService.displayProfile(user);
				session.setAttribute("profileData", Profilelist);
				List<Address> addressDetails = addressService.getAddress(userId);
				session.setAttribute("addressDetails", addressDetails);
				List<Address> allAddressList = addressService.getAddress(userId);
				session.setAttribute("allAddressList", allAddressList);
				session.setAttribute("CurrentUser", user);
				response.sendRedirect("UserDashboard.jsp");
			}

		} catch (RuntimeException e) {
		    throw e;
		}  catch (Exception e) {
			logger.info(e.toString());
		}

	}

}
