<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="csdit.BOARD_FDTO, csdit.PJ2020DAO"%>

<%
	request.setCharacterEncoding("utf-8");
	
	String i = request.getParameter("num");
	int num = Integer.parseInt(i);
	String title = request.getParameter("title");
	String date = request.getParameter("date");
	String content = request.getParameter("content");
	
	BOARD_FDTO dto = new BOARD_FDTO();
	dto.setF_NUM(num);
	dto.setF_TITLE(title);
	dto.setF_DATE(date);
	dto.setF_CONTENT(content);
	PJ2020DAO	dbPro = new PJ2020DAO();
	dbPro.F_BOARD_Change(dto,"u");
	
	response.sendRedirect("F_BOARD.jsp?num="+num);

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