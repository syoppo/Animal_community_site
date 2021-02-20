<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<style>
	#areaMain{padding:10%; margin-left:10%; margin-right:10%;}
	
	.aText{
	 	color: black; /*글자 색변경*/
	}

ul{
   list-style:none;
   }
li {
    margin: 0 0 0 0;
    padding: 0 0 0 0;
    border : 0;
    float: left;
}
</style>
<head>
	<meta charset="UTF-8">
	<title>ANIMAL MAIN</title>
</head>
<body>
	<header>
		<jsp:include page="top.jsp" flush="false"/>
	</header>
	<section id="areaMain">
		<!-- 포토게시판 영역 시작 -->
		<div id="photo" style="margin-bottom:30%;">
			<h5><a href="P_List.jsp" class="aText">포토게시판</a>	<a href="P_List.jsp" style="float: right;"><strong>더보기</strong><i class="fa fa-plus" aria-hidden="true"></i></a>
			</h5>
			<div class="container">
				<ul>

        </ul>
			</div>
		</div>
		<!-- 포토게시판 영역 끝 -->
		<br>
		<!-- 정보게시판 영역 시작 -->
		<div id="info" style="margin-bottom:30%;">
			<h5><a href="I_List.jsp" class="aText">정보게시판</a>	<a href="I_List.jsp" style="float: right;"><strong>더보기</strong><i class="fa fa-plus" aria-hidden="true"></i></a>
			</h5>
			<div class="container">
				<ul>

        </ul>
			</div>
		</div>
		<!-- 정보게시판 영역 끝 -->
		<br>
		<!-- 자유게시판 영역 시작 -->		
		<div id="free" style="margin-bottom:30%;">
			<h5><a href="LIST.jsp" class="aText">자유게시판</a>	<a href="LIST.jsp" style="float: right;"><strong>더보기</strong><i class="fa fa-plus" aria-hidden="true"></i></a></h5>
			<div class="container">
				<ul>

        </ul>
			</div>
		</div>
		<!-- 자유게시판 영역 끝 -->		
	</section>
</body>
</html>