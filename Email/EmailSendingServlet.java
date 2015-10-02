package com.Email;


import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.piet.DBInfo;



public class EmailSendingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// reads form fields
		String email = request.getSession().getAttribute("email").toString();
		String subject = " Receipt";
		String	content = "";
		String resultMessage = "";
		 Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs = null,rs1 = null;
		    PreparedStatement ps1=null;
		    con=DBInfo.getConnection();
		    int f=0;

				String name=null;
				Integer id=null;
				  String query			=	"select bookingid,name from receipt where email=?";
				  
				  try 
				  {
					ps=con.prepareStatement(query);	
					   ps.setString(1, request.getSession().getAttribute("email").toString());
					 rs=ps.executeQuery();
					
					
					while(rs.next())
					{   id=rs.getInt(1);
						name=rs.getString(2);
						System.out.println(name);
						System.out.println(email);
					}
				
				  }
				  catch (SQLException e) 
				  {
						// TODO Auto-generated catch block
						e.printStackTrace();
				  }
				 
				  content=name+" your Booking Id is: "+id;
				 try 
				 {
					 EmailUtility.sendEmail(host, port, user, pass, email, subject,content);
					 //resultMessage = "Booking Id has been sent to your E-mali ID.";
					response.sendRedirect("emailsent.html");
					// PrintWriter printWriter = response.getWriter();
					//printWriter.print(resultMessage);
				 } 
				 catch (Exception ex) 
				 {
					 ex.printStackTrace();
					 resultMessage = "There were an error: " + ex.getMessage();
				 }
	}
}