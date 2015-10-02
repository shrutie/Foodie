package existing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.PreparedStatement;


public class existingUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
      public existingUser() 
    {
        super();
    }
      protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
      {
    	  String cpass = null,cemail=null;
    	  response.setContentType("text/html");
    	  PrintWriter out =response.getWriter();
    	  
  		
  		String pass= request.getParameter("pass");
  		String email = request.getParameter("Email");
  		System.out.println("Email:" + email);
  		System.out.println("Password:" +pass);
  		String sql="select * from users where email=?";
  		Connection con=null;
  		try
  		{
  			
  			Class.forName("com.mysql.jdbc.Driver");
  			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","mysql");
  			PreparedStatement ps=  (PreparedStatement) con.prepareStatement(sql);
  			ps.setString(1,email);
  			ResultSet rs=ps.executeQuery();
  		while(rs.next())
  		{
  			cpass=rs.getString("password");
  			cemail=rs.getString("email");
   			System.out.println( "value from database(password):" +cpass);
  			System.out.println("value from database(email):" +cemail);
  			
  		if(cpass.equals(pass)==true && cemail.equals(email)==true)
  			{ 
  			request.getRequestDispatcher("profile.html").forward(request, response);
  			}
  		else
  		{
  			out.print("user doesn't exist");
  		}
  		}
  		}
  		catch (Exception e) 
  		{
  			out.println("Exception Occured");
  			e.printStackTrace();
  		}
  	
  		
  	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest(request,response);
	}
}
