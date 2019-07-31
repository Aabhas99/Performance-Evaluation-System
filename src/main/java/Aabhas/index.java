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

public class index extends HttpServlet
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
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		int regis=0;
		if(name!=null)
		{
			try 
			{
				db(name,email,password);
				regis=1;
			}
			catch (Exception e) 
			{
				regis=-10;
			}
		}
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		pw.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<link rel=\"icon\" href=\"IMG_20180529_131814.jpg\" >\r\n" + 
				"<title>Home</title>\r\n" + 
				"</head>\r\n" + 
				"<body style='background-size: cover;height:100%;background-image: url(\"images/bg.jpg\")'>\r\n" + 
				"\r\n" + 
				"<center>\r\n" + 
				"\r\n" + 
				"<header style=\"background-color:black\"><br><br>" + 
				"	<form style='display:inline-block;' action='Admin' method='post' >\r\n" + 
				"<input style=\"background-color:darkblue;text-align:center;color:white;\r\n" + 
				"border-radius:2000px;height:40px;width:280px\" type=\"submit\" name=\"s\" "
				+ "value=\"Admin Login\">\r"+
				"</form>"+
				"<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>\r" + 

				
				
				
				"	<form style='display:inline-block;' action='User' method='post' >\r\n" + 
				"<input style=\"background-color:darkblue;text-align:center;color:white;\r\n" + 
				"border-radius:2000px;height:40px;width:280px\" type=\"submit\" name=\"s\" "
				+ "value=\"User Login\">\r"+
				"</form><br><br>"+ 
	 
				
				"</header>\r\n"); 
				if(regis==1)
				{
					pw.println("<h2 style='color:green';>Registration successful</h2>");
				}
				else if(regis==-10)
				{
					pw.println("<h2 style='color:red';>Registration unsuccessful!!! Try"
							+ " alternative credentials</h2>");
				}
				pw.println("\r\n" + 
				"<br>\r\n" + 
				"<br>\r\n" + 
				"<fieldset style='border-radius:30px;width:300px'>\r\n" + 
				"\r\n" + 
				"<legend><h2>SIGN UP</h2></legend>\r\n" + 
				"\r\n" + 
				"<form action='index' method='post' >\r\n" + 
				"\r\n" + 
				"<input style='width:250px;border-radius:30px;border-bottom-color:darkblue;"
				+ "border-right-color:darkblue;\r\n" + 
				"height:30px;text-align:center' \r\n" + 
				"name='name' type=\"text\" value=\"\" placeholder='Name' required><br>"
				+ "<br><br>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<input style='width:250px;border-radius:30px;border-bottom-color:darkblue;"
				+ "border-right-color:darkblue;\r\n" + 
				"height:30px;text-align:center' \r\n" + 
				"name='email' type=\"email\" value=\"\" placeholder='E-mail' required>"
				+ "<br><br><br>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<input style='width:250px;border-radius:30px;border-bottom-color:darkblue"
				+ ";border-right-color:darkblue;\r\n" + 
				"height:30px;text-align:center' \r\n" + 
				"name='password' type=\"password\" value=\"\" "
				+ "placeholder='Password' required>"
				+ "<br><br><br>\r\n" + 
				"\r\n" + 
				"<input style=\"background-color:darkblue;text-align:center;color:white;\r\n" + 
				"border-radius:2000px;height:30px;width:120px\" type=\"submit\" name=\"s\" "
				+ "value=\"Sign-up\"><br>\r\n" + 
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
	public void db(String name,String email,String password)throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
				"root", "abcd");
		Statement st=co.createStatement();
			st.executeUpdate("insert into admin(name,email,password) "
					+ "values('"+name+"','"+email+"',sha1('"+password+"'))");
			ResultSet rs=st.executeQuery("select id from admin where email='"+email+"'");
			rs.next();
			String t=rs.getString("id");
			st.executeUpdate("create table M"+t+"(Tests varchar(50))");
			co.close();
	}
}
