<%@ page import="java.sql.*" import ="javax.mail.*" import ="javax.mail.internet.*" 
import="java.util.*" import ="Aabhas.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
</head>
<body>
<%
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

		Properties properties =new Properties();
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.ssl.trust","smtp.gmail.com");
		MimeMessage msg=new MimeMessage(Session.getInstance(properties)); 
		msg.setSender(new InternetAddress("aa.s1151998@gmail.com"));
		InternetAddress rr[]= {new InternetAddress("aa.s1151998@gmail.com"),
				new InternetAddress("aabhassaxena@outlook.com")};
		msg.addRecipients(Message.RecipientType.TO,rr);
		msg.setSubject("My First Mail");
		MimeBodyPart m1=new MimeBodyPart();
		MimeBodyPart m2=new MimeBodyPart();
		MimeBodyPart m3=new MimeBodyPart();
		m1.setContent("<h1 style='color:red'>First Line</h1>","text/html");
		//m2.attachFile(new File("e:\\excel.xlsx"));
		m3.setContent("<h3 style='color:yellow'>Second Line</h3>","text/html");
		MimeMultipart m=new MimeMultipart();
		m.addBodyPart(m1);
		m.addBodyPart(m2);
		m.addBodyPart(m3);
		msg.setContent(m);
		Transport.send(msg);
		System.out.println("Mail sent to "+rr.length+" receiver(s)");
	

%>
<h2>Score is:<%=c%></h2>
<%
response.setHeader("pragma-control","no-cache");
response.setHeader("cache-control","no-cache, no-store, must-revalidate");
response.setIntHeader("expires",0);
%>
</body>
</html>