<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<style>
	#areaMain{padding:10%; margin-left:10%; margin-right:10%;}
</style>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/tooplate-style.css">
	<link rel="stylesheet" href="css/login.css">
</head>

<body>
	<jsp:include page="top.jsp" flush="false"/>	
	<section id = "areaMain">
		<div class="container">
	    <div class="row">
	      <div class="col-lg-10 col-xl-9 mx-auto">
	        <div class="card card-signin flex-row my-5">
	          <div class="card-img-left d-none d-md-flex">
	             <!-- Background image for card set in CSS! -->
	          </div>
	          <div class="card-body">
	            <h2 class="card-title text-center">회원가입</h2>
	            <form action="signUpPro.jsp" method="post" class="form-signin">
	              <div class="form-label-group">
	                <input type="text" id="id" name="id" class="form-control" required autofocus>
	                <label for="id">ID</label>
	              </div>
	
	              <div class="form-label-group">
	                <input type="password" id="pw" name="pwd" class="form-control" required>
									<label for="id">비밀번호</label>
	              </div>
	              
	              <div class="form-label-group">
	                <input type="text" id="name" name="name" class="form-control" required>
									<label for="id">닉네임</label>
	              </div>
	              <div class="text-center">
		              <button type="submit" class="btn btn-lg btn-primary text-uppercase">회원가입</button>
		              <button type="button" class="btn btn-lg btn-primary text-uppercase" onclick="location.href='main.jsp'">취소</button>
	              </div>
	            </form>
	          </div>
	        </div>
	      </div>
	    </div>
  	</div>
	</section>
</body>
</html>