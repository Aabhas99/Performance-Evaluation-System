<%@ page import="java.sql.*" import="java.time.*" import="java.time.format.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>


<%
String subject=request.getParameter("subject");
%>
<h2><%=subject%></h2>
<hr>
<form action=result method="post">
<%
Class.forName("com.mysql.jdbc.Driver");
Connection co=DriverManager.getConnection("jdbc:mysql://localhost:3306/pes",
		"root", "abcd");
Statement st=co.createStatement();
ResultSet r=st.executeQuery("select * from "+subject);
int i=0;
while(r.next())
{
	i++;
	String Q=r.getString("Q");
	String o1=r.getString("o1");
	String o2=r.getString("o2");
	String o3=r.getString("o3");
	String o4=r.getString("o4");
%>
<br>
<br>
<span>Q<%=i%>)</span><span><%=i%></span><br>
<span><input type="radio" name="<%=i%>" value="<%=o1%>">
1)&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><span><%=o1%></span><br>
<span><input type="radio" name="<%=i%>" value="<%=o2%>">
2)&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><span><%=o2%></span><br>
<span><input type="radio" name="<%=i%>" value="<%=o3%>">
3)&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><span><%=o3%></span><br>
<span><input type="radio" name="<%=i%>" value="<%=o4%>">
4)&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><span><%=o4%></span><br>
<br>
<br>
<hr>
<%
}
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
LocalDateTime now = LocalDateTime.now();
String date=dtf.format(now);

%>
<input type="text" value="<%=subject%>" name="subject" hidden>
<input type="text" value="<%=date%>" name="date" hidden>
<input type="submit" value="Submit Test" name="submit">
</form>
<%
response.setHeader("pragma-control","no-cache");
response.setHeader("cache-control","no-cache, no-store, must-revalidate");
response.setIntHeader("expires",0);
%>
</center>
</body>
</html>