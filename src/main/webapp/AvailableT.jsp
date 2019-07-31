<%@ page import="java.sql.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<%
String se=(String)session.getAttribute("email");




if(se==null)
{
	response.sendRedirect("index");
	return;
}
%>
<body>

<ul class="topnav">
  <li><a style="background-color:darkblue;" href="UserD.jsp">Home</a></li>
  <li><a href="AvailableT.jsp">Available Tests</a></li>
  <li><a style="background-color:darkblue;" href="Mytests.jsp">My tests</a></li>
  <li><a href="UserD.jsp?l=1">Logout</a></li>
  
</ul>
<br><br><br>

<center>
<h1 style="color:darkblue">INSTRUCTIONS</h1>
<div style="font-size:20px; border:5px solid black;border-radius:20px;width:80%;height:200px">
1) Do not refresh the test page.<br><br>
2) Do not do anything with the page URL.<br><br>
3) Doing any of the above things will cancel your test.<br><br>
</div>
<% 
Class.forName("com.mysql.jdbc.Driver");
Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes","root","abcd");
Statement st=co.createStatement();
ResultSet r=st.executeQuery("select subject from subjects");
%>
<form action='test.jsp' method='post'>
<br><br>
<select style='height:30px;border-radius:5px;width:250px' name="subject">
<%
while(r.next())
{
	%>
	<option value="<%=r.getString("subject")%>"><%=r.getString("subject")%></option>
	<%
}
%>
</select>
<br>
<br>
<input style="width:300px;height:50px;color:white;border:1px solid white;border-radius:50px;background-color: darkblue;" type="submit" name="submit" value="Take Test">
</form>
<%
co.close();
response.setHeader("pragma-control","no-cache");
response.setHeader("cache-control","no-cache, no-store, must-revalidate");
response.setIntHeader("expires",0);
%>
</center>
</body>
</html>