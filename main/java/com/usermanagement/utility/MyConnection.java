package com.usermanagement.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	
	
		protected static MyConnection obj=null;
		protected static Connection connection=null;
		
		//register driver class for database connection
		private MyConnection() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		//create object if it is null
		public static MyConnection getInstance() {
			if(obj!=null) {
				return obj;
			}else {
				obj=new MyConnection();
				return obj;
			}
		}
		
		//provide database connection 
		public Connection getConnection() throws SQLException,ClassNotFoundException {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/usermanagement","root",null);
			return connection;
		}
		
	
}
