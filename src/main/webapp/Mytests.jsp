<%@ page import ="java.sql.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
table{width:80%;border-collapse: collapse}
th{padding:20px;}
td{padding:20px;border:1px solid black;}
tr{border:1px solid black;}
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

<%
String email=(String)session.getAttribute("email");
Class.forName("com.mysql.jdbc.Driver");
Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes","root","abcd");
Statement st=co.createStatement();
ResultSet r=st.executeQuery("select id from admin where email='"+email+"'");
r.next();
String db="m"+r.getString("id");
r=st.executeQuery("select * from "+db);
%>
<center>
<h1>My Tests</h1>
<br>
<table border="1">
<tr>
	<th> Subject </th>
	<th> Score </th>
	<th> Date and Time </th>
</tr>

<%
while(r.next())
{
	String result=r.getString("Tests");
	int i=result.lastIndexOf('(');
	int j=result.lastIndexOf(')');
	String subject=result.substring(0,i);
	String score=result.substring(i+1,j);
	String date=result.substring(j+1);
	%>
	<tr>
	<th> <%= subject %></th>
	<th> <%= score %></th>
	<th> <%= date %></th>
	</tr>
	<%
}
co.close();
response.setHeader("pragma-control","no-cache");
response.setHeader("cache-control","no-cache, no-store, must-revalidate");
response.setIntHeader("expires",0);
%>

</table>
<br>
<br><br>
</center>
</body>
</html>