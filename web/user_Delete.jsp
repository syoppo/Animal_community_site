<%@page import="csdit.PJ2020DAO"%>
<%@page import="csdit.WRITERDTO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
    
<%
	String id = request.getParameter("id");
	
	WRITERDTO dto = new WRITERDTO();
	PJ2020DAO	dbPro = new PJ2020DAO();
	dto.setId(id);
	dbPro.WRITERChange(dto,"d");
	session.invalidate();
	
	response.sendRedirect("main.jsp");
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	
</body>
</html>