<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@page import="csdit.BOARD_ADTO, csdit.PJ2020DAO"%>
<%
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
	String ss=sf.format(new java.util.Date());
%>

<%
	request.setCharacterEncoding("utf-8");

	String id =(String) session.getAttribute("id");//로그인된 아이디 세션으로 받아오기

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
	String Q_NUM = request.getParameter("Q_NUM");
	String Q_CONTENT = request.getParameter("A_CONTENT");
	
	PJ2020DAO	dbPro = new PJ2020DAO();
	BOARD_ADTO dto = new BOARD_ADTO();
	
	dto.setQ_NUM(Integer.parseInt(Q_NUM));
	dto.setA_DATE(ss);
	dto.setA_CONTENT(Q_CONTENT);
	dto.setU_ID(id);
	
	dbPro.A_BOARD_Change(dto, "i");
	response.sendRedirect("Q_BOARD.jsp?num="+Q_NUM);//다시 게시글로 이동
	
%>

</body>
</html>