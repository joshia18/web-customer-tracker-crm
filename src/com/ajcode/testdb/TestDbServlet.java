package com.ajcode.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//writing my own code here
		
		//setup connection variables
		
		String user = "springstudent";
		String password = "springstudent";
		
		//useSSL=false to avoid warning messages from the driver
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSl=false&serverTimezone=UTC";
		
		//"com.mysql.cj.jdbc.Driver" -> this is the name of jdbc driver class from mysql
		String driver = "com.mysql.cj.jdbc.Driver";
		
		//get connection to database
		try {
			PrintWriter out = response.getWriter();
			
			out.println("Connecting to database: " + jdbcUrl);
			
			Class.forName(driver);
			
			Connection myconn = DriverManager.getConnection(jdbcUrl, user, password);
			
			out.println("SUCCESS!!");
			
			myconn.close();
		}
		catch(Exception exc) {
			exc.printStackTrace();
			//this is done so we can see it in the browser when things go bad
			throw new ServletException(exc);
		}
	}

}
