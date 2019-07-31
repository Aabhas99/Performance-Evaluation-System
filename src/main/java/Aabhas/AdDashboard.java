package Aabhas;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

public class AdDashboard extends HttpServlet 
{
	public void service(HttpServletRequest req,HttpServletResponse res)
			throws IOException,ServletException
	{
		HttpSession session=req.getSession();
		String logout=req.getParameter("l");
		if(logout!=null&&logout.equals("1"))
		{
			session.removeAttribute("email");
			res.sendRedirect("index");
			return;
		}
		String se=(String)session.getAttribute("email");




		if(se==null)
		{
			res.sendRedirect("index");
			return;
		}
		
		String updateTheQ[]=null;
		ArrayList<String> q=new ArrayList<String>();
		int removeq=0;
		int updateq=0;
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String subject=req.getParameter("Subject");
		String remove=req.getParameter("remove");
		String showQ=req.getParameter("showQ");
		String showUpdateQ=req.getParameter("showUpdateQ");
		String removequ=req.getParameter("removequ");
		String updatequ=req.getParameter("updatequ");
		String rsubjectQ=req.getParameter("rSubQ");
		String updateQ=req.getParameter("updateQ");
		String addQ=req.getParameter("Add Q");
		String updateQu=req.getParameter("Update Q");

		if(subject!=null)
		{
			try 
			{
				db(subject);
			}
			catch(Exception e)
			{
			}
		}
		
		if(showQ!=null)
		{
			try 
			{
				removeq++;
				q=removeQ(rsubjectQ);
				session.setAttribute("Sub",rsubjectQ);
			}
			catch(Exception e)
			{
			}
		}
		
		if(showUpdateQ!=null)
		{
			try 
			{
				updateq++;
				q=updateQ(updateQ);
				session.setAttribute("USub",updateQ);
			}
			catch(Exception e)
			{
			}
		}
		
		
		if(remove!=null)
		{
			try 
			{
				String rsubject=req.getParameter("rSubjects");
				remove(rsubject);
			}
			catch(Exception e)
			{
			}
		}
		
		if(removequ!=null)
		{
			try 
			{
				String ques[]=req.getParameterValues("Questions");
				String sub=(String)session.getAttribute("Sub");
				removeques(ques,sub);
			}
			catch(Exception e)
			{
			}
		}
		
		if(updatequ!=null)
		{
			try 
			{
				String ques=req.getParameter("updateQuestions");
				String sub=(String)session.getAttribute("USub");
				updateTheQ=updateques(ques,sub);
				session.setAttribute("Uq",updateTheQ[0]);
			}
			catch(Exception e)
			{
			}
		}
		
		
		if(addQ!=null)
		{
			try 
			{
				String subjects=req.getParameter("Subjects");
				String Q=req.getParameter("Q");
				String o1=req.getParameter("o1");
				String o2=req.getParameter("o2");
				String o3=req.getParameter("o3");
				String o4=req.getParameter("o4");
				String ans=req.getParameter("ans");
				setQ(Q,o1,o2,o3,o4,subjects,ans);
			}
			catch(Exception e)
			{
			}
		}
		
		if(updateQu!=null)
		{
			try 
			{
				String subjects=(String)session.getAttribute("USub");
				String Q=req.getParameter("UQ");
				System.out.println(subjects+"   "+Q);
				String o1=req.getParameter("Uo1");
				String o2=req.getParameter("Uo2");
				String o3=req.getParameter("Uo3");
				String o4=req.getParameter("Uo4");
				String ans=req.getParameter("Uans");
				updateQ(Q,o1,o2,o3,o4,subjects,(String)session.getAttribute("Uq"),ans);
			}
			catch(Exception e)
			{
			}
		}
		
		
		
		ArrayList<String> al=new ArrayList<String>();
		try 
		{
			al=sub();
		} 
		catch(Exception e)
		{
		}
		
		
		pw.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<link rel=\"icon\" href=\"IMG_20180529_131814.jpg\" >\r\n" + 
				"<title>Admin Dashboard</title>\r\n" + 
				"</head>\r\n" + 
				"<body onload='hideall()' style='background-size: cover;height:100%;background-image: url(\"images/UserD.jpg\")'>"+
				"\r\n" + 
				"\r\n" + 
				
				
				"<script src=\"Javascript/js.js\">\r\n" + 
				"</script>"
				
				+"<ul><li style='float:right;list-style-type:none;background-color:black;width:20%'><a style='padding: 14px 16px;text-align:center;display:block;color:white;text-decoration:none;' "
				+ "href=\"AdDashboard?l=1\">Logout</a></li><ul>"
				+ "<center><br><br><h1>Welcome Admin</h1>\r\n"
				
				
				
				+ "<form style='display:inline-block;' action='AdDashboard' method='post' >\r\n" + 
				"<input style='width:300px;border-radius:10px;\r\n" + 
				"border-bottom-color:green;border-right-color:green;\r\n" + 
				"height:30px;text-align:center' \r\n" + 
				"name='Subject' type=\"text\" value=\"\" placeholder='Enter Subject' required>\r\n" + 
				"<span style='color:white';>lfbvsjdnbsjn</span>\r\n" + 
				"<input style=\"background-color:black;text-align:center;color:white;\r\n" + 
				"border-radius:2000px;height:30px;width:150px\" type=\"submit\" name=\"s\" value=\"Add Subject\"><br><br>\r\n" + 
				"</form><br>"
				+ "<button onclick=\"ShowQ()\">Add Question</button><br><br>\r\n"
				+ "<div id='form'><form action='AdDashboard' method='post' >");
				
pw.println("<h3 style='display:inline-block;'>Select Subject:</h3> <select style='height:30px;border-radius:5px;width:250px' name=\"Subjects\">\r\n");
				
				for(int x=0;x<al.size();x++)
				{
					pw.println("<option value='"+al.get(x)+"'> "+al.get(x)+"</option>");
				}
				pw.println("</select><br><br>"
				
				
				
				+ "<textarea maxlength=\"250\" rows=\"10\" cols=\"80\" style=\"border-radius:10px;text-align:center\"\r\n" + 
				" placeholder=\"Question\" name='Q' required></textarea><br><br>\r\n" + 
				" \r\n" + 
				" \r\n" + 
				" <input style='width:500px;border-radius:10px;\r\n" + 
				"border-bottom-color:green;border-right-color:green;\r\n" + 
				"height:30px;text-align:center' \r\n" + 
				"name='o1' type=\"text\" value=\"\" placeholder='Option 1' required>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<br><br>\r\n" +
				"\r\n" + 
				"\r\n" + 
				"<input style='width:500px;border-radius:10px;\r\n" + 
				"border-bottom-color:green;border-right-color:green;\r\n" + 
				"height:30px;text-align:center' \r\n" + 
				"name='o2' type=\"text\" value=\"\" placeholder='Option 2' required>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<br><br>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<input style='width:500px;border-radius:10px;\r\n" + 
				"border-bottom-color:green;border-right-color:green;\r\n" + 
				"height:30px;text-align:center' \r\n" + 
				"name='o3' type=\"text\" value=\"\" placeholder='Option 3' required>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<br><br>\r\n" +
				"\r\n" + 
				"\r\n" + 
				"<input style='width:500px;border-radius:10px;\r\n" + 
				"border-bottom-color:green;border-right-color:green;\r\n" + 
				"height:30px;text-align:center' \r\n" + 
				"name='o4' type=\"text\" value=\"\" placeholder='Option 4' required><br><br>\r\n" + 
				
				"<input style='width:500px;border-radius:10px;\r\n" + 
				"border-bottom-color:green;border-right-color:green;\r\n" + 
				"height:30px;text-align:center' \r\n" + 
				"name='ans' type=\"text\" value=\"\" placeholder='Answer' required><br><br>\r\n" + 
				
				"<input style=\"background-color:black;text-align:center;color:white;\r\n" + 
				"border-radius:2000px;height:30px;width:150px\" type=\"submit\" name=\"Add Q\" value=\"Add Question\"><br><br></form></div>");
				
				
				
				
				
				
pw.println("<form action='AdDashboard' method='post' >"
		+ "<h3 style='display:inline-block;'>Remove Subject:</h3> <select style='height:30px;border-radius:5px;width:250px' name=\"rSubjects\">\r\n");
				
				for(int x=0;x<al.size();x++)
				{
					pw.println("<option value='"+al.get(x)+"'> "+al.get(x)+"</option>");
				}
				pw.println("</select>"+
						"<span style='color:white';>lfbvsjdnbsjn</span>\r\n" +
						"<input style=\"background-color:black;text-align:center;color:white;\r\n" + 
						"border-radius:2000px;height:30px;width:150px\" type=\"submit\" name=\"remove\" value=\"Remove Subject\"></form><br><br>\r\n" + 
				
						
"<form action='AdDashboard' method='post' >"
+ "<h3 style='display:inline-block;'>Select Subject to remove question:</h3> <select style='height:30px;border-radius:5px;width:250px' name=\"rSubQ\">\r\n");
		
		for(int x=0;x<al.size();x++)
		{
			if(removeq==1&&q.get(q.size()-1).equals(al.get(x)))
			{
				pw.println("<option value='"+q.get(q.size()-1)+"' selected> "+q.get(q.size()-1)+"</option>");
			}
			else
			{
				pw.println("<option value='"+al.get(x)+"'> "+al.get(x)+"</option>");
			}
		}
		pw.println("</select>"+
				"<span style='color:white';>lfbvsjdnbsjn</span>\r\n" +
				"<input style=\"background-color:black;text-align:center;color:white;\r\n" + 
				"border-radius:2000px;height:30px;width:150px\" type=\"submit\" name=\"showQ\" value=\"Show questions\"><br><br>\r\n" + 
				 "</form>");
		if(removeq==1)
		{
			pw.println("<form action='AdDashboard' method='post' >"
					+ "<h3 style='display:inline-block;'>Select question:</h3> <select style='height:30px;border-radius:5px;width:250px' name=\"Questions\" multiple required>\r\n");
			for(int x=0;x<q.size()-1;x++)
			{
				pw.println("<option value='"+q.get(x)+"'> "+q.get(x)+"</option>");
			}
			pw.println("</select>"+
					"<span style='color:white';>lfbvsjdnbsjn</span>\r\n" +
					"<input style=\"background-color:black;text-align:center;color:white;\r\n" + 
					"border-radius:2000px;height:30px;width:150px\" type=\"submit\" name=\"removequ\" value=\"Remove Question\"><br><br>\r\n" + 
					 "</form>");
		}
		
		pw.println("<form action='AdDashboard' method='post' >"
		+ "<h3 style='display:inline-block;'>Select Subject to update question:</h3> <select style='height:30px;border-radius:5px;width:250px' name=\"updateQ\">\r\n");
				
				for(int x=0;x<al.size();x++)
				{
					if(updateq==1&&q.get(q.size()-1).equals(al.get(x)))
					{
						pw.println("<option value='"+q.get(q.size()-1)+"' selected> "+q.get(q.size()-1)+"</option>");
					}
					else
					{
						pw.println("<option value='"+al.get(x)+"'> "+al.get(x)+"</option>");
					}
				}
				pw.println("</select>"+
						"<span style='color:white';>lfbvsjdnbsjn</span>\r\n" +
						"<input style=\"background-color:black;text-align:center;color:white;\r\n" + 
						"border-radius:2000px;height:30px;width:150px\" type=\"submit\" name=\"showUpdateQ\" value=\"Show questions\"><br><br>\r\n" + 
						 "</form>");
				if(updateq==1)
				{
					pw.println("<form action='AdDashboard' method='post' >"
							+ "<h3 style='display:inline-block;'>Select question:</h3> <select style='height:30px;border-radius:5px;width:250px' name=\"updateQuestions\" required>\r\n");
					for(int x=0;x<q.size()-1;x++)
					{
						pw.println("<option value='"+q.get(x)+"'> "+q.get(x)+"</option>");
					}
					pw.println("</select>"+
							"<span style='color:white';>lfbvsjdnbsjn</span>\r\n" +
							"<input style=\"background-color:black;text-align:center;color:white;\r\n" + 
							"border-radius:2000px;height:30px;width:150px\" type=\"submit\" name=\"updatequ\" value=\"Update Question\"><br><br>\r\n" + 
							 "</form>");
				}
		if(updateTheQ!=null)
		{
			pw.println("<form action='AdDashboard' method='post' ><textarea maxlength=\"250\" rows=\"10\" cols=\"80\" style=\"border-radius:10px;text-align:center\"\r\n" + 
					" placeholder=\"Question\"  name='UQ' required>"+updateTheQ[0]+"</textarea><br><br>\r\n" + 
					" \r\n" + 
					" \r\n" + 
					" <input style='width:500px;border-radius:10px;\r\n" + 
					"border-bottom-color:green;border-right-color:green;\r\n" + 
					"height:30px;text-align:center' \r\n" + 
					"name='Uo1' type=\"text\"  value='"+updateTheQ[1]+"' placeholder='Option 1' required>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"<br><br>\r\n" +
					"\r\n" + 
					"\r\n" + 
					"<input style='width:500px;border-radius:10px;\r\n" + 
					"border-bottom-color:green;border-right-color:green;\r\n" + 
					"height:30px;text-align:center' \r\n" + 
					"name='Uo2' type=\"text\"  value='"+updateTheQ[2]+"' placeholder='Option 2' required>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"<br><br>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"<input style='width:500px;border-radius:10px;\r\n" + 
					"border-bottom-color:green;border-right-color:green;\r\n" + 
					"height:30px;text-align:center' \r\n" + 
					"name='Uo3' type=\"text\"  value='"+updateTheQ[3]+"' placeholder='Option 3' required>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"<br><br>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"<input style='width:500px;border-radius:10px;\r\n" + 
					"border-bottom-color:green;border-right-color:green;\r\n" + 
					"height:30px;text-align:center' \r\n" + 
					"name='Uo4' type=\"text\"  value='"+updateTheQ[4]+"' placeholder='Option 4' required><br><br>\r\n" +
					
					"\r\n" + 
					"\r\n" + 
					"<input style='width:500px;border-radius:10px;\r\n" + 
					"border-bottom-color:green;border-right-color:green;\r\n" + 
					"height:30px;text-align:center' \r\n" + 
					"name='Uans' type=\"text\"  value='"+updateTheQ[5]+"' placeholder='Answer' required><br><br>\r\n" +
					
					
					"<input style=\"background-color:black;text-align:center;color:white;\r\n" + 
					"border-radius:2000px;height:30px;width:150px\" type=\"submit\" name=\"Update Q\" value=\"Update Question\"><br><br></form></div>");
					
		}
		
		
				pw.println("</center>");
	}
	public void db(String subject)throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
				"root", "abcd");
		Statement st=co.createStatement();
		st.executeUpdate("insert into subjects values('"+subject+"')");
		st.executeUpdate("create table "+subject+"(Q varchar(250),"
				+ "o1 varchar(250),o2 varchar(250),o3 varchar(250),o4 varchar(250),ans varchar(250),primary key(Q))");
		co.close();
	}
	
	public void setQ(String Q,String o1,String o2,String o3,String o4,String subject,String ans)throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
				"root", "abcd");
		Statement st=co.createStatement();
		st.executeUpdate("insert into "+subject+" values("
				+ "'"+Q+"','"+o1+"','"+o2+"','"+o3+"','"+o4+"','"+ans+"')");
		co.close();
	}
	
	public void updateQ(String Q,String o1,String o2,String o3,String o4,String subject,String condiQ,String ans)throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
				"root", "abcd");
		Statement st=co.createStatement();
		System.out.println(condiQ);
		st.executeUpdate("update "+subject+" set Q='"+Q+"',o1='"+o1+"',o2='"+o2+"',o3='"+o3+"',o4='"+o4+"',ans='"+ans+"' "
				+ "where Q='"+condiQ+"'");
		System.out.println("a");
		co.close();
	}
	
	
	public void remove(String rsubject)throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
				"root", "abcd");
		Statement st=co.createStatement();
		st.executeUpdate("drop table "+rsubject);
		st.executeUpdate("delete from subjects where subject='"+rsubject+"'");
		co.close();
	}
	
	public void removeques(String ques[],String sub)throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
				"root", "abcd");
		Statement st=co.createStatement();
		System.out.println(sub);
		String q="delete from "+sub+" where Q in(";
		for(int x=0;x<ques.length-1;x++)
		{
			q+="'"+ques[x]+"',";
		}
		q+="'"+ques[ques.length-1]+"')";
		st.executeUpdate(q);
		co.close();
	}
	
	public String[] updateques(String ques,String sub)throws Exception
	{
		String updateTheQ[]=new String[6];
		Class.forName("com.mysql.jdbc.Driver");
		Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
				"root", "abcd");
		Statement st=co.createStatement();
		ResultSet r=st.executeQuery("select * from "+sub+" where Q='"+ques+"'");
		r.next();
		updateTheQ[0]=r.getString("Q");
		updateTheQ[1]=r.getString("o1");
		updateTheQ[2]=r.getString("o2");
		updateTheQ[3]=r.getString("o3");
		updateTheQ[4]=r.getString("o4");
		updateTheQ[5]=r.getString("ans");
		co.close();
		return updateTheQ;
	}
	
	
	
	public ArrayList<String> removeQ(String rsubjectQ)throws Exception
	{
		ArrayList<String> q=new ArrayList<String>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
				"root", "abcd");
		Statement st=co.createStatement();
		ResultSet r=st.executeQuery("select Q from "+rsubjectQ);
		while(r.next())
		{
			String aa=r.getString("Q");
			q.add(aa);
		}
		q.add(rsubjectQ);
		co.close();
		return q;
	}
	
	
	public ArrayList<String> updateQ(String updateQ)throws Exception
	{
		ArrayList<String> q=new ArrayList<String>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
				"root", "abcd");
		Statement st=co.createStatement();
		ResultSet r=st.executeQuery("select Q from "+updateQ);
		while(r.next())
		{
			String aa=r.getString("Q");
			q.add(aa);
		}
		q.add(updateQ);
		co.close();
		return q;
	}
	
	
	
	
	public ArrayList<String> sub()throws Exception
	{
		ArrayList<String> al=new ArrayList<String>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
				"root", "abcd");
		Statement st=co.createStatement();
		ResultSet r=st.executeQuery("select subject from subjects");
		
		while(r.next())
		{
			String aa=r.getString("subject");
			al.add(aa);
		}
		co.close();
		return al;
	}
}