<%@ page import="java.sql.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 

String logout=request.getParameter("l");
if(logout!=null&&logout.equals("1"))
{
	session.removeAttribute("email");
	response.sendRedirect("index");
	return;
}
String se=(String)session.getAttribute("email");




if(se==null)
{
	response.sendRedirect("index");
	return;
}

%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
body {margin: 0;}

ul.topnav {
width:100%;
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color:black;
}

ul.topnav li {float: left;width:25%;}

ul.topnav li  a{

  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  
}
ul.topnav li form{

  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  
}

li:hover {background-color: darkblue;}

</style>
</head>
<body style='background-size:cover;background-repeat: no-repeat;background-image: url("images/UserD.jpg")'>

<ul class="topnav">
  <li><a style="background-color:darkblue;" href="UserD.jsp">Home</a></li>
  <li><a href="AvailableT.jsp">Available Tests</a></li>
  <li><a style="background-color:darkblue;" href="Mytests.jsp">My tests</a></li>
  <li><a href="UserD.jsp?l=1">Logout</a></li>
  
</ul>

<center>
<br><br><br>
<%
Class.forName("com.mysql.jdbc.Driver");
Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes","root","abcd");
Statement st=co.createStatement();
ResultSet r=st.executeQuery("select name from admin where email='"+se+"'");
r.next();
String User=r.getString("name");
%>
<h1 style="font-size:40px;">Welcome</h1>
<h1 style="font-size:40px;"><%=User%></h1>
<br><br>
<pre style="font-size:30px;">Performance Evaluation System lets you give tests of different programming 
languages and displays your result as well as mail the 
result to you.Along with that you can also 
get details of your previous tests.
</pre>
</center>
<% 
response.setHeader("pragma-control","no-cache");
response.setHeader("cache-control","no-cache, no-store, must-revalidate");
response.setIntHeader("expires",0);
%>
</body>
</html>
