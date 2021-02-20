<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="csdit.PJ2020DAO,csdit.WRITERDTO" %>

<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="dto" class="csdit.WRITERDTO">
	<jsp:setProperty name="dto" property="*"/>
</jsp:useBean>

<%
	PJ2020DAO dbPro = PJ2020DAO.getInstance();

	if(true==dbPro.U_ID_Check(dto.getId())){//값이 true면 중복되는 아이디가 없다는 것이기에 유저정보 inselt
		dbPro.WRITERChange(dto,"i");
		response.sendRedirect("main.jsp");
	}else{
		%>
		<script>
		alert("이미 존재하는 아이디 입니다.");
		location.href = "signUp.jsp";
		</script>
		<% 
	}

	
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