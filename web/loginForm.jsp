<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/login.css">
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h4 class="card-title text-center mb-4 mt-1">로그인</h4>
            <form class="form-signin" action="loginPro.jsp" method="post">
              <div class="form-label-group">
                <input type="text" name="id" class="form-control" required autofocus>
                <label for="inputId">아이디</label>
              </div>
              <br>
              <div class="form-label-group">
                <input type="password" name="pwd" class="form-control"required>
                <label for="inputPassword">비밀번호</label>
              </div>
              <br>
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">로그인</button>
              <hr class="my-4">
              <div class="form-group">
              <p class="text-center"><a href="#" class="btn">아이디 비밀번호 찾기</a>
							<a href=signUp.jsp class="btn">회원가입</a></p>
							
							</div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  
</body>
</html>