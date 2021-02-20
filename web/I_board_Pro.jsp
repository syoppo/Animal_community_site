<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="csdit.BOARD_IDTO, csdit.PJ2020DAO"%>

<%
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
	String ss=sf.format(new java.util.Date());
%>
<% 
	request.setCharacterEncoding("utf-8");
	
	String dir = application.getRealPath("/IuploadFiles");
	int maxSize = 5*1024*1024;//파일사이즈
	
	String encType = "utf-8";
	
	File f = new File(dir);
	
	if(!f.exists())
		f.mkdir();
	
	//request
	//savedirectory
	//maxPostSize
	//encoding
	//fileRenamePolocy
	
	MultipartRequest multi = new MultipartRequest(request, dir, maxSize, encType, new DefaultFileRenamePolicy());
	
	String writer = multi.getParameter("U_ID");
	String title = multi.getParameter("I_TITLE");
	String content = multi.getParameter("I_CONTENT");
	String filename = multi.getFilesystemName("I_FILE");
	String original = multi.getOriginalFileName("I_FILE");
	
	String contype = multi.getContentType("I_FILE");
	
	// 서버 폴더에 파일을 업로드
	//----------------------
	
	BOARD_IDTO dto = new BOARD_IDTO();
	dto.setI_TITLE(title);
	dto.setI_DATE(ss);
	dto.setI_CONTENT(content);
	dto.setI_FILE(filename);
	dto.setU_ID(writer);
	PJ2020DAO	dbPro = new PJ2020DAO();
	dbPro.I_BOARD_Change(dto, "i");
	response.sendRedirect("I_List.jsp");
	
%>