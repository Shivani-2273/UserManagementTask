package controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.User;
import services.UserServiceImpl;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		User user=new User();
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));
		user.setEmail(request.getParameter("email"));
		user.setContactNo(request.getParameter("contact"));
		user.setGender(request.getParameter("gender"));
		user.setBirthDate(request.getParameter("birthDate"));	
		String languages="";
		String[] lang=request.getParameterValues("language");
		for(int i=0;i<lang.length;i++) {
			languages+=lang[i]+" ";
		}
		user.setLanguages(languages);
		//user.setBase64Image("image");
		//String user_image=request.getParameter("image");
		
		//File image=new File(user_image);
		//FileInputStream fis=new FileInputStream(image);
		//user.setImage(fis);
		
		//user.setPassword(request.getParameter("password"));
		String password=request.getParameter("password");
		try {
			String input=password;
			byte[] encryptionBytes=utility.Encryption_Decryption.encrypt(input);
			String pass=new String(encryptionBytes);
			user.setPassword(pass);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		} 
		
		/*for Address*/
		Address addr_obj =new Address();
		addr_obj.setAddress(request.getParameter("Address[0][Street_Address]"));
		addr_obj.setState(request.getParameter("Address[0][State]"));
		addr_obj.setCity(request.getParameter("Address[0][City]"));
		addr_obj.setPin(request.getParameter("Address[0][Pin]"));
		
		List<Address> list=new ArrayList<Address>();
		list.add(addr_obj);
		//HttpSession session=request.getSession();
		//User session_obj=(User) session.getAttribute("CurrentUser");
		//int user_id=session_obj.getUserId();
		int user_id=user.getUserId();
		
		UserServiceImpl service=new UserServiceImpl();
		try {
			service.addUser(user);
			service.addAddress(addr_obj,user_id);
			RequestDispatcher req=request.getRequestDispatcher("UserLogin.jsp");
			req.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
		
	}

}
