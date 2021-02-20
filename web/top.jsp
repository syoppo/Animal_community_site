<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="csdit.WRITERDTO, csdit.PJ2020DAO"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TOP</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/tooplate-style.css">
	<link rel="stylesheet" href="css/login.css">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	
</head>
<style>
.slideIn {
  -webkit-animation-name: slideIn;
  animation-name: slideIn;
}
</style>
<script language = "javascript">  // 자바 스크립트 시작
function searchCheck()
  {
   var form = document.search;
   
   if( !form.ward.value )   // form 에 있는  검색값이 없을 때
   {
    alert( "검색 할 문자 입력" ); // 경고창 띄움
    return;
   }
   
  if(form.Board.value == "F")//검색에 따라 form action 을 바꿔 게시판선택 
   {
   	form.action="LIST.jsp";
   	
   }else if(form.Board.value == "P"){
	  form.action="P_List.jsp";
	  
   }else if(form.Board.value == "Q"){
	  form.action="Q_List.jsp";
	   
   }else if(form.Board.value == "I"){
		form.action="I_List.jsp";
		   
	 }
 
  form.submit();//해당 패이지로 전송
  }
 </script>
<body>

<%
	PJ2020DAO	dbPro = new PJ2020DAO();
	String id = null;
	if(session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
	}
%>
	<!-- ======= Top Bar ======= -->
	<!-- 제일 상단 -->
	<nav class="navbar navbar-light bg-light static-top">
    <div class="container d-flex justify-content-end">
      <div class="social-links">
      <%
				if(id == null) {
			%>
				<a href=loginForm.jsp class="btn">로그인</a>
				<a href=signUp.jsp class="btn">회원가입</a>
			<%
				} else {
			%>
				<%=dbPro.U_NICK(id) %>
				<a class="btn" href="logOut.jsp">로그아웃</a>
			<%
			
				}
			
			%>
      </div>
    </div>
  </nav>
  <br>
  <!-- 제일 상단 끝 -->
  	
	<!-- page header -->
	<div class="container mx-auto">
	
      <div class="row w-75">
        <div class="col-sm-6 social-links text-right">
          <a href=main.jsp class="btn d-inline-block text-uppercase" style="font-size:25px;">ANIMAL</a>
        </div>
        
        
        
        <!-- 검색 기능 -->
          <form name="search" method="post" class="form-inline">
							<div class="form-group mb-2"> 
                <select name="Board" class="form-control">
									<option value="F">자유게시판</option>
									<option value="I">정보게시판</option>
									<option value="P">포토게시판</option>
									<option value="Q">질문게시판</option>
								</select>
              </div>
              
              <div class="form-group mx-sm-3 mb-2">
                <input type="text" name="ward" class="form-control" placeholder="검색어를 입력해주세요.">
              </div>
              

                <button type="submit" class="btn btn-primary mb-2" onclick="searchCheck()">검색</button>

              
			    </form>
			     <!-- 검색 기능 -->
      </div>
	</div>


      <!-- navbar -->
    <div class="tm-nav-section">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <nav class="navbar navbar-expand-md navbar-light">
              <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#tmMainNav"
                aria-controls="tmMainNav"
                aria-expanded="false"
                aria-label="Toggle navigation"
              >
                <span class="navbar-toggler-icon"></span>
              </button>

              <div class="collapse navbar-collapse" id="tmMainNav">
                <ul class="navbar-nav mx-auto tm-navbar-nav">
                  <li class="nav-item">
                    <a class="nav-link" href="I_List.jsp">정보게시판</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="Q_List.jsp">질문게시판</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="LIST.jsp">자유게시판</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="P_List.jsp">포토게시판</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="user_info.jsp">회원정보</a>
                  </li>
                  
                </ul>
              </div>
            </nav>
            
          </div>
        </div>
      </div>
    </div>
</body>
</html>