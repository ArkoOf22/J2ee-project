package com.Arko.Registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//PrintWriter out=response.getWriter();
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	String password=request.getParameter("pass");
	String contact=request.getParameter("contact");
	Connection con=null;
	RequestDispatcher dispatcher=null;
//	out.print(name+"\n"+email+"\n"+password+"\n"+contact);
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");

		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?useSSL=false","root","RootPassword");
		PreparedStatement pst=con.prepareStatement("insert into users(uname,upwd,uemail,umobile) values(?,?,?,?)");
		pst.setString(1, name);
		pst.setString(2, password);
		pst.setString(3, email);
		pst.setString(4, contact);
		
		int rowCount=pst.executeUpdate();
		dispatcher=request.getRequestDispatcher("registration.jsp");
		if(rowCount>0)
		{
			request.setAttribute("status", "success");
		}
		else
		{
			request.setAttribute("status", "failed");
		}
		dispatcher.forward(request, response);
			
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	}

}
