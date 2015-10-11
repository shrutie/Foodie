package existing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class bookedSeats extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public bookedSeats()
    {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		String name=request.getParameter("Name");
		String num=request.getParameter("Number");
		String arrdate =request.getParameter("arrdate");
		String arrtime= request.getParameter("arrtime");
		String dur=request.getParameter("duration");
		
		System.out.print("Details:" +name+ "   " + num + " ");
		String sql = "insert into bookedSeats values(?,?,?,?,?)";
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","mysql");
			ps= (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2,num);
			ps.setString(3,arrdate);
			ps.setString(4,arrtime);
			ps.setString(5,dur);
			int s= ps.executeUpdate();
			ResultSet st= ps.getResultSet();
			String cdate= st.getString("arrivaldate");
			String ctime=st.getString("arrivaltime");
			if(cdate.equals(arrdate) && ctime.equals(arrtime))
			{
				JOptionPane.showMessageDialog(null, "Warning Text!",
						"Warning Message!", JOptionPane.WARNING_MESSAGE); 
			}
			else if(s>0)
			{
				out.print("done database dekh");
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

}
