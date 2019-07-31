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
public class Admin extends HttpServlet
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
			if(email.equals("ab@c.com")&&password.equals("123"))
			{
				HttpSession s=req.getSession();
				s.setAttribute("email",email);
				res.sendRedirect("AdDashboard");
				return;
			}
			else
			{
				error="Invalid Credentials";
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
				"<legend><h2>ADMIN LOGIN</h2></legend>\r\n" + 
				"\r\n" + 
				"<form action='Admin' method='post' >\r\n" + 
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
				"border-radius:2000px;height:30px;width:120px\" type=\"submit\" name=\"sa\" "
				+ "value=\"Sign-up\"><br><br>\r\n" + 
				"\r\n" + 
				"</form>\r\n" + 
				"</fieldset>\r\n" + 
				"</center>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	}
}