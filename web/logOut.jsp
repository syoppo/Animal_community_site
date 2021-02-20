<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id");
	session.invalidate();
%>
<script>
	alert("로그아웃되었습니다.");
	location.href = "main.jsp";
</script>