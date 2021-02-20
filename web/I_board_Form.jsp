<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="csdit.PJ2020DAO"%>
<%
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
	String ss=sf.format(new java.util.Date());
	PJ2020DAO	dbPro = new PJ2020DAO();
%>

<%
	request.setCharacterEncoding("utf-8");

	String id = null;

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
%>

<!DOCTYPE html>
<html>
<style>
	#areaMain{padding:10%; margin-left:10%; margin-right:10%;}
</style>
<script>
function writeCheck()
  {
   var form = document.writeform;
   
   if( !form.I_FILE.value )   // form 에 있는 name 값이 없을 때
   {
    alert( "이미지파일이 없습니다" ); // 경고창 띄움
    form.I_FILE.focus();   // form 에 있는 name 위치로 이동
    return;
   }
   
  if( !form.I_TITLE.value )
   {
    alert( "제목을 적어주세요" );
    form.I_TITLE.focus();
    return;
   }
 
  if( !form.I_CONTENT.value )
   {
    alert( "내용을 적어주세요" );
    form.I_CONTENT.focus();
    return;
   }
 
  form.submit();
  }
 </script>
<head>
	<meta charset="UTF-8">
	<title>정보 게시판</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<header>
		<jsp:include page="top.jsp" flush="false"/>
	</header>
	<section id = "areaMain">
	
	<br>
	<div class="container">
		<h2 class="text-center">정보 게시판</h2>
		
		<form name="writeform" action="I_board_Pro.jsp" method="post" enctype="multipart/form-data">
			<div class="form-group">
			작성자 <input type="text" name="U_ID" class="form-control" value=<%=id %> readonly>
			</div>
			
			<div class="form-group">
			제목 <input type="text" name="I_TITLE" class="form-control">
			</div>
			<div class="form-group">
			<label class="form-control"><%=ss %></label>
			</div>
			<div class="form-group">
			<textarea name="I_CONTENT" rows="13" class="form-control"></textarea>
			</div>
			<input type="file" accept="image/*" name="I_FILE" class="btn btn-success"><br><br>
			<input type="button" value="업로드" class="btn bnt-primary" onclick="writeCheck()">
		</form>
	</div>
	</section>
</body>
</html>