package existing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

public class newUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public newUser() {
        super();
       
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
		PrintWriter out= response.getWriter();
		String fname=request.getParameter("first");
		String lname=request.getParameter("second");
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		String cuisine=request.getParameter("cuisine");
		System.out.print("Details:" +fname+ " " + lname + "  " + email + " " + pass + "  " + cuisine);
		String sql = "insert into users values(?,?,?,?,?)";
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","mysql");
			ps= (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1,fname);
			ps.setString(2,lname);
			ps.setString(3,email);
			ps.setString(4,pass);
			ps.setString(5,cuisine);
			int s= ps.executeUpdate();
			if(s>0)
			{
				out.print("Database toh check kar!");
			}
			else
				out.print("Try again");
			
		}
		catch (Exception e)
		{
			out.print("Exception Occured");
			e.printStackTrace();
		}
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    	processRequest(request, response);
	}
}
