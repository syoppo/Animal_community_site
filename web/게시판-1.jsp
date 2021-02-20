<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@page import="csdit.BOARD_FDTO, csdit.PJ2020DAO"%>
<%
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
	String ss=sf.format(new java.util.Date());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8"); //받아오는 값들을 한글로 인코딩합니다.

	String U_ID = request.getParameter("U_ID"); //write.jsp에서 name에 입력한 데이터값
	String F_TITLE = request.getParameter("F_TITLE"); //write.jsp에서 title에 입력한 데이터값
	String F_CONTENT = request.getParameter("F_CONTENT"); //write.jsp에서F_CONTENT에 입력한 데이터값
	
	BOARD_FDTO dto = new BOARD_FDTO();
	dto.setF_TITLE(F_TITLE);
	dto.setF_DATE(ss);
	dto.setF_CONTENT(F_CONTENT);
	dto.setU_ID(U_ID);
	PJ2020DAO	dbPro = new PJ2020DAO();
	dbPro.F_BOARD_Change(dto, "i");
	response.sendRedirect("LIST.jsp");
	
%>

</body>
</html>