<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="csdit.BOARD_IDTO, csdit.PJ2020DAO"%>
<%
	request.setCharacterEncoding("utf-8");
	
	String i = request.getParameter("num");
	int num = Integer.parseInt(i);//넘어온 num을 변환
	
	BOARD_IDTO dto = new BOARD_IDTO();
	PJ2020DAO	dbPro = new PJ2020DAO();
	dto = dbPro.BOARD_I(num);

	String id = null;
	String U_class = null;
	//로그인되어있으면 id값 받아오기. 
	if(session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
		U_class = (String) session.getAttribute("class");
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>정보게시판</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

	<style>
	#areaMain{padding:10%; margin-left:10%; margin-right:10%;}
	</style>

</head>
<body>
<header>
		<jsp:include page="top.jsp" flush="false"/>
	</header>
<section id = "areaMain">
	<br>

	<div class="container">
	<br>
	<h1 class="text-center font-weight-bold">정보게시판</h1>
	
			<div class="form-group">
				<label for="id">글번호</label>
				<label class="form-control"><%=num %></label>
			</div>
			<div class="form-group">
				<label for="id">제목</label>
				<label class="form-control"><%=dto.getI_TITLE() %></label>
			</div>
			<div class="form-group">
				<label for="id">날짜</label>
				<label class="form-control"><%=dto.getI_DATE() %></label>
			</div>
			<div class="form-group">
				<label for="id">내용</label>
				<label class="form-control"><%=dto.getI_CONTENT() %></label>
			</div>
			<div class="form-group text-center">
				<label class="form-control"><img src="http://localhost:8080/webPJ2020_1/IuploadFiles/<%=dto.getI_FILE()%>"></label>
			</div>
			<div class="form-group">
				<label for="id">작성자</label>
				<label class="form-control" for="id"><%=dbPro.U_NICK(dto.getU_ID()) %></label>
			</div>
			
			<div class="form-group text-center">
				<button type="button" class="btn btn-primary" onclick="location.href='LIST.jsp'">목록</button>
				<%
				if(id!=null)//null 오류방지
					if(id.equals(dto.getU_ID()) || U_class != null){ %>
					<button type="button" class="btn btn-primary" >수정</button>
				<%} %>
			</div>
	</div>
</body>
</html>