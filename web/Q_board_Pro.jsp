<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@page import="csdit.BOARD_QDTO, csdit.PJ2020DAO"%>
<%
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
	String ss=sf.format(new java.util.Date());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8"); //받아오는 값들을 한글로 인코딩합니다.

	String U_ID = request.getParameter("U_ID");
	String Q_TITLE = request.getParameter("Q_TITLE"); 
	String Q_CONTENT = request.getParameter("Q_CONTENT");
	
	PJ2020DAO	dbPro = new PJ2020DAO();
	BOARD_QDTO dto = new BOARD_QDTO();
	
	dto.setQ_TITLE(Q_TITLE);
	dto.setQ_DATE(ss);
	dto.setQ_CONTENT(Q_CONTENT);
	dto.setU_ID(U_ID);
	
	dbPro.Q_BOARD_Change(dto, "i");
	response.sendRedirect("Q_List.jsp");
	
%>

</body>
</html>