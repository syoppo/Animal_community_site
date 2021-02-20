<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	String ss=sf.format(new java.util.Date());
	//java.sql.Date t = java.sql.Date.valueOf(ss);
%>

<%
	request.setCharacterEncoding("utf-8");
	String num = request.getParameter("num");
	String id = request.getParameter("id");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 수정</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container">
	<br>
	<h1 class="text-center font-weight-bold">게시글 수정</h1>
	
		<form action="adminBoardFUpdatePro.jsp" method="post">
			<div class="form-group">
				<label for="id">글번호</label>
				<input type="text" class="form-control" id="num" name="num" value=<%=num %>>
			</div>
			<div class="form-group">
				<label for="id">제목</label>
				<input type="text" class="form-control" id="title" name="title">
			</div>
			<div class="form-group">
				<label for="id">날짜</label>
				<input type="date" class="form-control" id="date" name="date" value=<%=ss %> readonly="readonly">
			</div>
			<div class="form-group">
				<label for="id">내용</label>
				<textarea rows="6" cols="" class="form-control" id="content" name="content"></textarea>
			</div>
			<div class="form-group">
				<label for="id">작성자</label>
				<label for="id"><%=id %></label>
			</div>		
			<div class="text-center">
				<button type="submit" class="btn btn-secondary">변경</button>
				<button type="button" class="btn btn-secondary" onclick="location.href='F_BOARD.jsp?num=<%=num%>'">취소</button>
				<button type="button" class="btn btn-secondary" onclick="location.href='adminBoardFDelete.jsp?num=<%=num%>'">삭제</button>
				<button type="reset" class="btn btn-secondary">다시작성</button>
			</div>
		</form>
	</div>
</body>
</html>