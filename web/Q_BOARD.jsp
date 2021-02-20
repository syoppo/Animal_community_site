<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="csdit.BOARD_QDTO, csdit.PJ2020DAO, csdit.BOARD_ADTO"%>
<%
	request.setCharacterEncoding("utf-8");
	
	String i = request.getParameter("num");
	int num = Integer.parseInt(i);//넘어온 num을 변환
	
	BOARD_QDTO dto = new BOARD_QDTO();
	BOARD_ADTO dtoA = new BOARD_ADTO();
	PJ2020DAO	dbPro = new PJ2020DAO();
	dto = dbPro.BOARD_Q(num);
	
	//댓글====================================================================
	int numOfPages = 5;//한화면에 표시되는 페이지 수
	int numOfRecords = 20;//레코드 수
	
	//page 전달 예외사항, 번호를 얻고 null이가나 빈문자열이아니라는 것을 확인
	String page_ = request.getParameter("p");
	int p = 1; //초기값
	//번호 null "" 일경우 page_값을 int 변환해서 getList에 전달
	if(page_ != null && !page_.equals(""))
		p = Integer.parseInt(page_);
	
	//pageing패키지의 getList()호출 현재 페이지번호를 매개변수로 전달
	ArrayList<BOARD_ADTO> dtos = dbPro.getList_A(p, numOfRecords, num);
	//전체 레코드 수를 추출
	int count = dbPro.getCount_A(num);
	
	int startNum =p-((p-1)% numOfPages);//화면에 출력될 첫 페이지 번호값 계산
	int lastNum = (int) Math.ceil((double)count/numOfRecords);//마지막 출력한 페이지
	//double Math.ceil(n) --> 소수값 이 있으면 올림함

%>
<!DOCTYPE html>
<html>
<style>
	#areaMain{padding:10%; margin-left:10%; margin-right:10%;}
</style>
<head>
	<meta charset="UTF-8">
	<title>질문 게시글</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
<body>
<header>
		<jsp:include page="top.jsp" flush="false"/>
	</header>
	<section id="areaMain">
	<br>
	<div class="container">
	<br>
	<h1 class="text-center font-weight-bold">질문게시판</h1>
	
			<div class="form-group">
				<label for="id">글번호</label>
				<label class="form-control"><%=num %></label>
			</div>
			<div class="form-group">
				<label for="id">제목</label>
				<label class="form-control"><%=dto.getQ_TITLE() %></label>
			</div>
			<div class="form-group">
				<label for="id">날짜</label>
				<label class="form-control"><%=dto.getQ_DATE() %></label>
			</div>
			<div class="form-group">
				<label for="id">내용</label>
				<label class="form-control"><%=dto.getQ_CONTENT() %></label>
			</div>
			<div class="form-group">
				<label for="id">작성자</label>
				<label for="id" class="form-control"><%=dto.getU_ID() %></label>
			</div>
			
			<br><br><br><br>
			
			<div class="form-group text-center">
				<button type="button" class="btn btn-primary" onclick="location.href='Q_List.jsp'">목록</button>
				<button type="button" class="btn btn-primary" onclick="location.href='adminBoardFUpdateForm.jsp'">수정</button>
			</div>
			<hr>
			<%		
				if(session.getAttribute("id") == null) {//현제 있는 세션을 확인하여 로그인 되어있지 않으면 로그인 화면으로 이동
					%>
					<label>댓글은 로그인 후 이용가능합니다.</label>
					<%
				}else{
					%>
					<!--댓글 보내기  -->
					<form action="A_board_Pro.jsp" method="post">
					<div class="form-group">
					댓글<textarea class="form-control" name="A_CONTENT" cols="50" rows="2"></textarea>
					</div>
					<input type= "hidden" name="Q_NUM" value="<%=num%>"/>
					<input type="submit" value="댓글달기" class="btn btn-primary" style="float:right;">
				</form>			
					<%
						}
					%>			
					<br><br>

 
 	<!-- 댓글 리스트 -->
		<%
		out.print("총 댓글 : " + count + "개");
	 %>
	 
<%
	if(count==0) {
%>
			<label>등록된 글이 없습니다.</label>
<%
	 	} else {
	 		
	 		for(int z=0; z<dtos.size(); z++){
				dtoA = dtos.get(z);
				String content = dtoA.getA_CONTENT();
				String date = dtoA.getA_DATE();
				String writer = dtoA.getU_ID();					
%>

	<div class="container">
		<hr>
		<label class="form-control"><%=content %></label>
		
		<label><%="작성자-["+dbPro.U_NICK(writer)+"]" %></label>
		<label style="float:right;"><%="작성일-"+date %></label>
				
	</div>
<% 
	 		}
	}
%>

 					<!-- 현제페이지/전체페이지 출력-->
 <div class="d-flex justify-content-end">
 	<span><%=p %></span>&nbsp;&nbsp;/ <%=lastNum %> pages
 	
 </div>
 
 <!-- 페이지 나누기 -->
 <div class="d-flex justify-content-center">
	 <ul class="pagination pagination-sm">
	 	<% if(startNum > 1) { %>
	 		<li class = "page-itme"><a class="page-link" href="?p=<%=startNum-1%>">Prev</a></li>
	 	<%}else{ //1보다 작거나 같은 것은 가장 첫페이지 %>
	 		<li class = "page-itme"><a class="page-link" style="color:gray" onclick="alert('이전 페이지가 없습니다.')" href="#">Prev</a></li>
	 	<%} //if
	 		for(int z = 0; z < numOfPages; z++){//한 화면에 numOfPages개 페이지가 표현된다
	 			if(p==(startNum+z))
	 				pageContext.setAttribute("colorValue", "orange");
	 			else
	 				pageContext.setAttribute("colorValue", "gray");
	 			if(startNum+z <= lastNum){
	 	%>	
	 	<li class = "page-itme"><a class="page-link" style="color:${coloValue}" href="?p=<%=startNum+z %>"><%=startNum+z %></a></li>
	 	<% }//if
	 			}//for
	 			if(startNum + numOfPages <= lastNum){	//레코드 마지막 번호를 알고있어야함%>
	 				<li class="page-item"><a class="page-link" href="?p=<%=startNum+5 %>">Next</a></li>
		<%}else{ %>	 		
	 		<li class = "page-itme"><a class="page-link" style="gray" onclick="alert('다음페이지가 없습니다')" href="#">Next</a></li>
	 	<%}// %>
	 </ul>
 </div>
 
 </div>
</section>		
</body>
</html>