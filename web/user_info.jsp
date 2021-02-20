
<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="csdit.PJ2020DAO,csdit.WRITERDTO" %>

<%
	request.setCharacterEncoding("utf-8");

	String id = null;
	
	PJ2020DAO dbPro = new PJ2020DAO();

	if(session.getAttribute("id") == null) {//현제 있는 세션을 확인하여 로그인 되어있지 않으면 로그인 화면으로 이동
		%>
		<script>
		alert("로그인후 이용가능합니다.");
		location.href = "loginForm.jsp";
		</script>
		<%
	}else{
		id = (String) session.getAttribute("id");
	}
	
	WRITERDTO dto = new WRITERDTO();
	dto = dbPro.User_info(id);
%>

<script language = "javascript">  // 자바 스크립트 시작

function writeCheck()
  {
   var form = document.writeform;
   
  if( !form.U_PW.value )
   {
    alert( "패스워드를 입력" );
    form.U_TITLE.focus();
    return;
   }
 
  if( !form.U_NAME.value )
   {
    alert( "닉네임을 입력" );
    form.U_NAME.focus();
    return;
   }
 
  form.submit();
  }
 </script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
	#areaMain{padding:10%; margin-left:10%; margin-right:10%;}
</style>
 <head>
	<meta charset="UTF-8">
	<title>유저 정보</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
 <body>
 <jsp:include page="top.jsp"/>
 <section id="areaMain">

 <form name="writeform" action="user_Update.jsp" method="post">
			<div class="form-group">
			<label>아이디</label>
			<input type="text" name="U_ID" class="form-control" value=<%=dto.getId() %> readonly>
			</div>
			<div class="form-group">
			<label>패스워드</label>
			<input type="text" name="U_PW" class="form-control">
			</div>
			<div class="form-group">
			<label>닉네임</label>
			<input type="text" name="U_NAME" class="form-control" value=<%=dto.getName() %>>
			</div>
			<input type="button" value="변경" class="btn bnt-primary" onclick="javascript:writeCheck();">
			<button type="button" class="btn btn-secondary" onclick="location.href='user_Delete.jsp?id=<%=dto.getId() %>'">탈퇴</button>
		</form>
 </section>
</body>
 </html>