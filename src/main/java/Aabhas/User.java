package Aabhas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
public class User extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)
			throws IOException,ServletException
	{
		HttpSession session=req.getSession();
		if(!(session.getAttribute("email")==null))
		{
			res.sendRedirect("UserD");
			return;
		}
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String error="";
		if(email!=null)
		{
			try 
			{
				if(db(email,password))
				{
					HttpSession s=req.getSession();
					s.setAttribute("email",email);
					
					res.sendRedirect("UserD.jsp");
					return;
				}
				else
				{
					error="Invalid Credentials";
				}
			} catch (Exception e) 
			{
			}
		}
		pw.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<link rel=\"icon\" href=\"IMG_20180529_131814.jpg\" >\r\n" + 
				"<title>Home</title>\r\n" + 
				"</head>\r\n" + 
				"<body style='background-size: cover;height:100%;background-image: url(\"images/login.jpg\")'>\r\n" + 
				"\r\n" + 
				"<center><br><br><br><br><br><br><br>\r\n" + 
				"\r\n<h2 style='color:red'>" + error+"</h2>"+
				
				"<fieldset style='border-radius:5px;width:400px'>\r\n" + 
				"\r\n" + 
				"<legend><h2>USER LOGIN</h2></legend>\r\n" + 
				"\r\n" + 
				"<form action='User' method='post' >\r\n" + 
				"\r\n" + 
				
				
				"<input style='width:350px;border-radius:10px;border-bottom-color:darkblue;"
				+ "border-right-color:darkblue;\r\n" + 
				"height:40px;text-align:center' \r\n" + 
				"name='email' type=\"email\" value=\"\" placeholder='E-mail' required>"
				+ "<br><br><br>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				
				
				"<input style='width:350px;border-radius:10px;border-bottom-color:darkblue"
				+ ";border-right-color:darkblue;\r\n" + 
				"height:40px;text-align:center' \r\n" + 
				"name='password' type=\"password\" value=\"\" "
				+ "placeholder='Password' required>"
				+ "<br><br><br>\r\n" + 
				"\r\n" +
				
				"<input style=\"background-color:darkblue;text-align:center;color:white;\r\n" + 
				"border-radius:2000px;height:30px;width:120px\" type=\"submit\" name='sa'"
				+ "value=\"Sign-up\"><br><br>\r\n" + 
				"\r\n" + 
				"</form>\r\n" + 
				"</fieldset>\r\n" + 
				"</center>\r\n" + 
				"</body>\r\n" + 
				"</html>");
		res.setHeader("pragma-control","no-cache");
		res.setHeader("cache-control","no-cache, no-store, must-revalidate");
		res.setIntHeader("expires",0);
	}
	public boolean db(String email,String password)throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
				"root", "abcd");
		Statement st=co.createStatement();
		ResultSet rs=st.executeQuery("select id from admin where email='"+email+"' and "
				+ "password=sha1('"+password+"')");
		if(rs.next())
		{
			return true;
		}
		return false;
	}
}