package com.usermanagement.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	
		public static MyConnection obj=null;
		public static Connection connection=null;
		private MyConnection() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		public static MyConnection getInstance() {
			if(obj!=null) {
				return obj;
			}else {
				obj=new MyConnection();
				return obj;
			}
		}
		
		public Connection getConnection() throws SQLException,ClassNotFoundException {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/usermanagement","root","qwertyuiop");
			return connection;
		}
}
