package Aabhas;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.sql.*;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
class subauth extends Authenticator
{
	protected PasswordAuthentication getPasswordAuthentication()
	{
		PasswordAuthentication authentication=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
					"root", "abcd");
			Statement st=co.createStatement();
			ResultSet rs=st.executeQuery("select * from pass");
			rs.next();
			String e=rs.getString("e");
			String p=rs.getString("p");
			authentication = new PasswordAuthentication
					(e,p);
			
		}
		catch(Exception e)
		{
			
		}
		return authentication;
	}
}
public class result extends HttpServlet 
{
	public void service(HttpServletRequest request, HttpServletResponse res)throws ServletException
	{
		
		try
		{
			//System.out.println("a");
			Class.forName("com.mysql.jdbc.Driver");
			Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
					"root", "abcd");
			Statement st=co.createStatement();
			String s=request.getParameter("subject");
			if(s==null)
			{
				
			}
			ResultSet r=st.executeQuery("select * from "+s);
			int i=0;
			int c=0;
			while(r.next())
			{
				i++;
				String returned_ans=request.getParameter(""+i);
				if(returned_ans!=null)
				{
					if(r.getString("ans").equals(returned_ans))
					{
						c++;
					}
				}
				
			}
			//System.out.println("a");
			HttpSession ss=request.getSession();
			String email=(String)ss.getAttribute("email");
			r=st.executeQuery("select id from admin where email='"+email+"'");
			System.out.println("a");
			r.next();
			//System.out.println("a");
			String db="m"+r.getString("id");
			r=st.executeQuery("select * from "+db);
			String tests=s+" ("+c+")"+request.getParameter("date");
			st.executeUpdate("insert into "+db+" values('"+tests+"')");
			Properties properties =new Properties();
			properties.put("mail.smtp.host","smtp.gmail.com");
			properties.put("mail.smtp.port","587");
			properties.put("mail.smtp.starttls.enable","true");
			properties.put("mail.smtp.auth","true");
			properties.put("mail.smtp.ssl.trust","smtp.gmail.com");
			MimeMessage msg=new MimeMessage(Session.getInstance(properties,new subauth())); 
			
			msg.setSender(new InternetAddress("aa.s1151998@gmail.com"));
			
			//System.out.println((String)ss.getAttribute("email"));
			InternetAddress re[]= {new InternetAddress((String)ss.getAttribute("email"))};
			
			msg.addRecipients(Message.RecipientType.TO,re);
			msg.setSubject("Test score of test at Performance Evaluation System");
			MimeBodyPart m1=new MimeBodyPart();
			m1.setContent("<h1 style='color:black'>Your Score of "+s+" test is "+c+"</h1>","text/html");
			MimeMultipart m=new MimeMultipart();
			m.addBodyPart(m1);
			msg.setContent(m);
			Transport.send(msg);
			System.out.println("Mail sent");
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			pw.println("<center><h1 style='color:black'>Your Score of "+s+" test is "+c+"</h1>");
			pw.println("<h2>Mail has been sent to your given email id<h2>");
			pw.println("<a href='UserD.jsp'>Home page</a></center>");
			res.setHeader("pragma-control","no-cache");
			res.setHeader("cache-control","no-cache, no-store, must-revalidate");
			res.setIntHeader("expires",0);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
