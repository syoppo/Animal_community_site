<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@page import="csdit.BOARD_QDTO, java.util.ArrayList, csdit.PJ2020DAO"%>
<%
	request.setCharacterEncoding("UTF-8");//한글깨짐방지
	BOARD_QDTO dto = new BOARD_QDTO();
	PJ2020DAO	dbPro = new PJ2020DAO();
	
	String ward = request.getParameter("ward");//검색어가 있으면 받아오기
	
	int numOfPages = 5;//한화면에 표시되는 페이지 수
	int numOfRecords = 10;//레코드 수
	
	//page 전달 예외사항, 번호를 얻고 null이가나 빈문자열이아니라는 것을 확인
	String page_ = request.getParameter("p");
	int p = 1; //초기값
	//번호 null "" 일경우 page_값을 int 변환해서 getList에 전달
	if(page_ != null && !page_.equals(""))
		p = Integer.parseInt(page_);
	
	ArrayList<BOARD_QDTO> dtos;
	
	
	int count = 0;//미리 변수 선언
	
	if(ward != null)//ward 검색한 단어가 있을 경우 검색어 검색없다면 그냥 불러오기
	{
		dtos = dbPro.getList_Q(p, numOfRecords, ward);//pageing패키지의 getList()호출 현재 페이지번호를 매개변수로 전달
		count = dbPro.getCount_Q(ward);
	}
		else
	{
		dtos = dbPro.getList_Q(p, numOfRecords);
		//전체 레코드 수를 추출
		count = dbPro.getCount_Q();
	}
	
	
	int startNum =p-((p-1)% numOfPages);//화면에 출력될 첫 페이지 번호값 계산
	int lastNum = (int) Math.ceil((double)count/numOfRecords);//마지막 출력한 페이지
	//double Math.ceil(n) --> 소수값 이 있으면 올림함
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
	#areaMain{padding:10%; margin-left:10%; margin-right:10%;}
</style>
 <head>
 <title>질문 게시판</title>
 </head>
 <body>
	<jsp:include page="top.jsp" flush="false"/>
	<section id="areaMain">
	<h2 class="text-center">질문 게시판</h2>
	
	<%
	if(ward != null)
		out.print(ward+"의 검색결과");
	out.print("총 게시물 : " + count + "개");
	%>
	
<table width="100%" cellpadding="0" cellspacing="0" border="0">
  <tr height="4"><td width="4"></td></tr>
 <tr style="background:url('') repeat-x; text-align:center;">
   <td width="5"><img src="" width="4" height="30" /></td>
   <td width="73">번호</td>
   <td width="379">제목</td>
   <td width="100">작성자</td>
   <td width="164">작성일</td>  
   <td width="7"><img src="" width="4" height="30" /></td>
  </tr>
<%
	if(count==0) {
%>
	 		<tr align="center" bgcolor="#FFFFFF" height="30">
	 	   <td colspan="6">등록된 글이 없습니다.</td>
	 	  </tr>
<%
	 	} else {
	 		
	 		for(int i=0; i<dtos.size(); i++){
				dto = dtos.get(i);
				int num = dto.getQ_NUM();
				String title = dto.getQ_TITLE();
				String date = dto.getQ_DATE();
				String content = dto.getQ_CONTENT();
				String writer = dto.getU_ID();					
%>
<tr height="25" align="center">
	<td>&nbsp;</td>
	<td><%=num %></td>
	<td align="left"><a href="Q_BOARD.jsp?num=<%=num %>"><%=title %></a></td>
	<td align="center"><%=dbPro.U_NICK(writer) %></td>
	<td align="center"><%=date %></td>
	<td>&nbsp;</td>
</tr>
  <tr height="1" bgcolor="#D2D2D2"><td colspan="6"></td></tr>
<% 
	 		}
	}
%>
 <tr height="1" bgcolor="#82B5DF"><td colspan="6" width="752"></td></tr>
 </table>
 <!-- 현제페이지/전체페이지 출력-->
 <div class="d-flex justify-content-end">
 	<span><%=p %></span>&nbsp;&nbsp;/ <%=lastNum %> pages
 </div>
 <!-- 페이지 나누기 -->
 <div class="d-flex justify-content-center">
	 <ul class="pagination">
	 	<% if(startNum > 1) { %>
	 		<li class = "page-itme"><a class="page-link" href="?p=<%=startNum-1%>">Prev</a></li>
	 	<%}else{ //1보다 작거나 같은 것은 가장 첫페이지 %>
	 		<li class = "page-itme"><a class="page-link" style="color:gray" onclick="alert('이전 페이지가 없습니다.')" href="#">Prev</a></li>
	 	<%} //if
	 		for(int i = 0; i < numOfPages; i++){//한 화면에 numOfPages개 페이지가 표현된다
	 			if(p==(startNum+i))
	 				pageContext.setAttribute("colorValue", "orange");
	 			else
	 				pageContext.setAttribute("colorValue", "gray");
	 			if(startNum+i <= lastNum){
	 	%>	
	 	<li class = "page-itme"><a class="page-link" style="color:${coloValue}" href="?p=<%=startNum+i %>"><%=startNum+i %></a></li>
	 	<% }//if
	 			}//for
	 			if(startNum + numOfPages <= lastNum){	//레코드 마지막 번호를 알고있어야함%>
	 				<li class="page-item"><a class="page-link" href="?p=<%=startNum+5 %>">Next</a></li>
		<%}else{ %>	 		
	 		<li class = "page-itme"><a class="page-link" style="gray" onclick="alert('다음페이지가 없습니다')" href="#">Next</a></li>
	 	<%}// %>
	 </ul>
 </div>

			<div class="form-group text-right">
				<button type="button" class="btn btn-primary" onclick="location.href='Q_board_Form.jsp'">글쓰기</button>
			</div>
</section>
</body> 
</html>