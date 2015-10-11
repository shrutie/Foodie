

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.activation.*;


public class Mailserver extends HttpServlet
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	
		String email = request.getParameter("email");
		
		Properties pros = System.getProperties();
		pros.setProperty("mail.smtp.host", "localhost");
		
		
		Session mailsession = Session.getDefaultInstance(properties);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String to=email;
		String from="ishashank22@gmail.com"; // enter the senders email
		String subject = "";// enter a subject
		String body = "";
			
		try
		{
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(body);
			Transport.send(message);
		}
		catch(MessagingException e )
		{
			e.printStackTrace();
		}
		}
		
		
		
		
	}


