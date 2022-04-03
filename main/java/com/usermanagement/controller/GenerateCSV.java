package com.usermanagement.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.usermanagement.model.User;
import com.usermanagement.services.UserServiceImpl;
import com.usermanagement.services.UserService;

/**
 * Servlet implementation class GenerateCSV
 */
@WebServlet("/GenerateCSV")
public class GenerateCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateCSV() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = new User();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		UserService service = new UserServiceImpl();
		
			List<User> list  ;
			//service.getCSVFile(user)
			File file = new File("S:\\UserLoginInfo_CSV\\Login.csv");
			try {
				FileWriter outputfile = new FileWriter(file);
				CSVWriter writer = new CSVWriter(outputfile);

				String[] Header= {"First Name","Last Name","Date/Time"};
				writer.writeNext(Header);

			} catch (IOException e) {
				e.printStackTrace();
			}

			RequestDispatcher req = request.getRequestDispatcher("LoginInfo.jsp");
			req.forward(request, response);
		
	}

}
