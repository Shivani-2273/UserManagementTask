package com.usermanagement.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class ValidationFilter
 */
@WebFilter("/ValidationFilter")
public class ValidationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ValidationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email == null || "".equals(email)) {
			request.setAttribute("emailErrorMsg", "Please enter email!!");
			RequestDispatcher req = request.getRequestDispatcher("UserLogin.jsp");
			req.forward(request, response);
		}

		else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
			request.setAttribute("emailErrorMsg", "Please enter correct email");
			RequestDispatcher req = request.getRequestDispatcher("UserLogin.jsp");
			req.forward(request, response);
		}
		else {
			 chain.doFilter(request, response);
		}

		if (password == null || "".equals(password)) {
			request.setAttribute("passErrorMsg", "Please enter password!!");
			RequestDispatcher req = request.getRequestDispatcher("UserLogin.jsp");
			req.forward(request, response);
		}else {
			 chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
