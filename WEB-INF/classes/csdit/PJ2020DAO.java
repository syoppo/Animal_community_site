package csdit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class PJ2020DAO {
	private static PJ2020DAO instance = new PJ2020DAO();
	
	public static PJ2020DAO getInstance() {
		return instance;
	}
	//커넥션 풀 내용.
	private Connection getConnection() throws Exception{
		Connection con = null;
		
		try {	
		//1. Context 객체 얻기
		Context initCtx = new InitialContext();

		//2. "java:comp/env" 에 해당하는 객체를 찾아서 envCtx에 삽입
		Context envCtx = (Context) initCtx.lookup("java:comp/env");

		//3. "jdbc/animal"에 해당하는 개체를 찾아서 ds에 삽입
		DataSource ds = (DataSource) envCtx.lookup("jdbc/animal");
		
		//4. 커넥션 풀로 부터 커넥션 객체를 얻어냄
		con = ds.getConnection();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//=======================================================================
	//함수 정리
	/*
	자유게시판 
	포토게시판
	정보게시판
	질문게시판
	질문에 댓글관련
	유저
	사이트 관리 
	U_ID_Check()-유저가입시 중복체크
	U_NICK(U_ID)-유저아이디로 네임 찾기
	
	*/
	//===========================================================================
	
	//유저 정보 하나 가져오기
	public WRITERDTO User_info(String U_ID){

			Connection con=null;
			Statement stmt = null;
			ResultSet rs = null;
			WRITERDTO dto = new WRITERDTO();
		
			
			try {
				con = getConnection();
				stmt = con.createStatement();
											
				String sql= "select * from WRITER where U_ID=?";//받은 자유게시판 아이디로 검색
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, U_ID);
				
				rs = pstmt.executeQuery();					
				
				while(rs.next()) {//값을 dto에 넣는다.
					dto.setId(rs.getString("U_ID"));
					dto.setPwd(rs.getString("U_PW"));
					dto.setName(rs.getString("U_NAME"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try { 
					if(rs!=null) rs.close();
					if(stmt!=null)stmt.close();
					if(con!=null) con.close();
				}catch(Exception e){e.printStackTrace();}
			}	
			return dto;//리턴
	}
	//가입유저중복 체크
		public Boolean U_ID_Check(String U_ID){

				Connection con=null;
				Statement stmt = null;
				ResultSet rs = null;
				String ID = null;//값을 저장할 변수
				Boolean Check = false;
				
				try {
					con = getConnection();
					stmt = con.createStatement();
												
					String sql= "select U_ID from WRITER where U_ID=?";//입력한 id가 있는 지 검색
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, U_ID);
					
					rs = pstmt.executeQuery();					
					
					while(rs.next()) {//값을 저장.
						ID = rs.getString("U_ID"); 	
					}
					
					if(ID == null)//값이 엇다면 null이고 null이면 체크를 true로 만듬
						Check = true;
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { 
						if(rs!=null) rs.close();
						if(stmt!=null)stmt.close();
						if(con!=null) con.close();
					}catch(Exception e){e.printStackTrace();}
				}	
				return Check;//리턴
		}
		//관리자 유저 확인 관리자라면 true
		public Boolean MASTER_Check(String U_ID){

				Connection con=null;
				Statement stmt = null;
				ResultSet rs = null;
				String CLASS = null;//값을 저장할 변수
				Boolean Check = false;
				
				try {
					con = getConnection();
					stmt = con.createStatement();
												
					String sql= "select W_C from WRITER_CLASS where U_ID=?";//입력한 id가 있는 지 검색
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, U_ID);
					
					rs = pstmt.executeQuery();					
					
					while(rs.next()) {//값을 저장.
						CLASS = rs.getString("W_C"); 	
					}
					
					if(CLASS != null)//값이 없다면 null이고 있다면 마스터이므로 체크를 true로 만듬
						Check = true;
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { 
						if(rs!=null) rs.close();
						if(stmt!=null)stmt.close();
						if(con!=null) con.close();
					}catch(Exception e){e.printStackTrace();}
				}	
				return Check;//리턴
		}
	//입력/변경/삭제 함수.
	public void WRITERChange(WRITERDTO dto, String flag) {
		Connection con = null; PreparedStatement pstmt = null;
		String sql=null;
		try { 
			con = getConnection();
			if(flag.equals("i")) {
				sql = "INSERT INTO WRITER VALUES(?,?,?)";		
				//3.sql문 준비
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPwd());
				pstmt.setString(3, dto.getName());
			}else if(flag.equals("u")) {
				sql = "update WRITER set U_NAME=?, U_PW=? where U_ID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getPwd());
				pstmt.setString(3, dto.getId());
			}else if(flag.equals("d")) {
				sql = "delete from WRITER where U_ID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getId());
			}
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	//자유게시판
	//입력/변경/삭제 함수.
		public void F_BOARD_Change(BOARD_FDTO dto, String flag) {
			Connection con = null; PreparedStatement pstmt = null;
			String sql=null;
			try { 
				con = getConnection();
				if(flag.equals("i")) {
					sql = "INSERT INTO BOARD_F VALUES(SEQ_F_NUM.NEXTVAL, ?, ?, ?, ?)";		
					//3.sql문 준비
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getF_TITLE());
					pstmt.setString(2, dto.getF_DATE());
					pstmt.setString(3, dto.getF_CONTENT());
					pstmt.setString(4, dto.getU_ID());
				}else if(flag.equals("u")) {
					sql = "update BOARD_F set F_TITLE=?, F_DATE=? ,F_CONTENT=? where F_NUM=?";//수정시 날짜 변경 허용?
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getF_TITLE());
					pstmt.setString(2, dto.getF_DATE());
					pstmt.setString(3, dto.getF_CONTENT());
					pstmt.setInt(4, dto.getF_NUM());
				}else if(flag.equals("d")) {
					sql = "delete from BOARD_F where F_NUM=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, dto.getF_NUM());
				}
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	
				//자유게시판 값 하나 가져오기
				public BOARD_FDTO BOARD_F(int F_NUM){

						Connection con=null;
						Statement stmt = null;
						ResultSet rs = null;
						BOARD_FDTO dto = new BOARD_FDTO();
					
						
						try {
							con = getConnection();
							stmt = con.createStatement();
														
							String sql= "select * from BOARD_F where F_NUM=?";//받은 자유게시판 아이디로 검색
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, F_NUM);
							
							rs = pstmt.executeQuery();					
							
							while(rs.next()) {//값을 dto에 넣는다.
								dto.setF_NUM(rs.getInt("F_NUM")); 
								dto.setF_TITLE(rs.getString("F_TITLE"));
								dto.setF_DATE(rs.getString("F_DATE"));
								dto.setF_CONTENT(rs.getString("F_CONTENT"));
								dto.setU_ID(rs.getString("U_ID"));	
							}
							
						}catch(Exception e) {
							e.printStackTrace();
						}finally {
							try { 
								if(rs!=null) rs.close();
								if(stmt!=null)stmt.close();
								if(con!=null) con.close();
							}catch(Exception e){e.printStackTrace();}
						}	
						return dto;//리턴
				}
				//포토게시판 값 하나 가져오기
				public BOARD_PDTO BOARD_P(int P_NUM){

						Connection con=null;
						Statement stmt = null;
						ResultSet rs = null;
						BOARD_PDTO dto = new BOARD_PDTO();
					
						
						try {
							con = getConnection();
							stmt = con.createStatement();
														
							String sql= "select * from BOARD_P where P_NUM=?";//받은 자유게시판 아이디로 검색
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, P_NUM);
							
							rs = pstmt.executeQuery();					
							
							while(rs.next()) {//값을 dto에 넣는다.
								dto.setP_NUM(rs.getInt("P_NUM")); 
								dto.setP_TITLE(rs.getString("P_TITLE"));
								dto.setP_DATE(rs.getString("P_DATE"));
								dto.setP_FILE(rs.getString("P_FILE"));
								dto.setP_ID(rs.getString("U_ID"));	
							}
							
						}catch(Exception e) {
							e.printStackTrace();
						}finally {
							try { 
								if(rs!=null) rs.close();
								if(stmt!=null)stmt.close();
								if(con!=null) con.close();
							}catch(Exception e){e.printStackTrace();}
						}	
						return dto;//리턴
				}
		
		
				//listUser
				public ArrayList<WRITERDTO> listUser(){
					
					//db검색정보 저장위해 arraylist생성
					ArrayList<WRITERDTO> dtos = new ArrayList<WRITERDTO>();
						Connection con=null;
						Statement stmt = null;
						ResultSet rs = null;
						
						try {
							con = getConnection();
							String sql= "select * from WRITER ORDER BY U_ID";
							stmt = con.createStatement();
							rs = stmt.executeQuery(sql);
							
							while(rs.next()) {
								String id = rs.getString("U_ID");
								String pwd = rs.getString("U_PW");
								String name = rs.getString("U_NAME");
								WRITERDTO dto = new WRITERDTO(id, pwd, name);
								dtos.add(dto);
							}
						}catch(Exception e) {
							e.printStackTrace();
						}finally {
							try { 
								if(rs!=null) rs.close();
								if(stmt!=null)stmt.close();
								if(con!=null) con.close();
							}catch(Exception e){e.printStackTrace();}
						}	
						return dtos;
				}
				//로그인(2)
				public int checkUser(String id, String pwd) {
					int check = 0;
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try {
						con = getConnection();
						String sql = "SELECT U_ID, U_PW FROM WRITER WHERE U_ID=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, id);
						
						rs = pstmt.executeQuery();
						
						if(rs.next()) {
							String dbpwd = rs.getString("U_PW");
							if(dbpwd.equals(pwd)) {
								check=1;
							}else {	//id는 있으나 pwd가 틀리다.
								check=0;
							}
						}else {
							check=-1;
						}
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						try { 
							if(rs!=null) rs.close();
							if(pstmt !=null) pstmt.close();
							if(con != null)con.close();
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
					return check;
				}
				
				//포토게시판 관련 수정
		public void P_BOARD_Change(BOARD_PDTO dto, String flag) {
			Connection con = null; PreparedStatement pstmt = null;
			String sql=null; ResultSet rs = null;
			try { 
				con = getConnection();
				if(flag.equals("i")) {
					sql = "INSERT INTO BOARD_P VALUES(SEQ_P_NUM.NEXTVAL,?,?,?,?)";		
					//3.sql문 준비
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getP_TITLE());
					pstmt.setString(2, dto.getP_DATE());
					pstmt.setString(3, dto.getP_FILE());
					pstmt.setString(4, dto.getP_ID());
				}else if(flag.equals("u")) {
					sql = "update BOARD_P set P_TITLE=?, P_FILE=? where P_NUM=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getP_TITLE());
					pstmt.setString(2, dto.getP_FILE());
					pstmt.setInt(3, dto.getP_NUM());
					pstmt.executeUpdate();
				}else if(flag.equals("d")) {
					sql = "delete from BOARD_P where P_NUM=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, dto.getP_NUM());
					pstmt.executeUpdate();
				}
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		//QNA게시판
		//입력/변경/삭제 함수.
			public void Q_BOARD_Change(BOARD_QDTO dto, String flag) {
				Connection con = null; PreparedStatement pstmt = null;
				String sql=null; ResultSet rs = null;
				try { 
					con = getConnection();
					if(flag.equals("i")) {
						sql = "INSERT INTO BOARD_Q VALUES(SEQ_Q_NUM.NEXTVAL, ?, ?, ?, ?)";		
						//3.sql문 준비
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getQ_TITLE());
						pstmt.setString(2, dto.getQ_DATE());
						pstmt.setString(3, dto.getQ_CONTENT());
						pstmt.setString(4, dto.getU_ID());
					}else if(flag.equals("u")) {
						sql = "update BOARD_Q set Q_TITLE=?, Q_DATE=? ,Q_CONTENT=? where Q_NUM=?";//수정시 날짜 변경 허용?
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getQ_TITLE());
						pstmt.setString(2, dto.getQ_DATE());
						pstmt.setString(3, dto.getQ_CONTENT());
						pstmt.setInt(4, dto.getQ_NUM());
					}else if(flag.equals("d")) {
						sql = "delete from BOARD_Q where Q_NUM=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, dto.getQ_NUM());
					}
					pstmt.executeUpdate();
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if(pstmt != null) pstmt.close();
						if(con != null) con.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			//QNA게시판 값 하나 가져오기
			public BOARD_QDTO BOARD_Q(int Q_NUM){

					Connection con=null;
					Statement stmt = null;
					ResultSet rs = null;
					BOARD_QDTO dto = new BOARD_QDTO();
				
					
					try {
						con = getConnection();
						stmt = con.createStatement();
													
						String sql= "select * from BOARD_Q where Q_NUM=?";//받은 자유게시판 아이디로 검색
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, Q_NUM);
						
						rs = pstmt.executeQuery();					
						
						while(rs.next()) {//값을 dto에 넣는다.
							dto.setQ_NUM(rs.getInt("Q_NUM")); 
							dto.setQ_TITLE(rs.getString("Q_TITLE"));
							dto.setQ_DATE(rs.getString("Q_DATE"));
							dto.setQ_CONTENT(rs.getString("Q_CONTENT"));
							dto.setU_ID(rs.getString("U_ID"));	
						}
						
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						try { 
							if(rs!=null) rs.close();
							if(stmt!=null)stmt.close();
							if(con!=null) con.close();
						}catch(Exception e){e.printStackTrace();}
					}	
					return dto;//리턴
			}
		
			//전체 레코드 갯수 가져오기.-자유게시판
			public int getCount_F() {
				int count = 0;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "SELECT COUNT(F_NUM) COUNT FROM BOARD_F";
				
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next())
					{
						count = rs.getInt("count");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				}
				return count;
			}
			//검색된 레코드 갯수 가져오기.-자유게시판검색 제목 검색
			public int getCount_F(String ward) {
				int count = 0;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "SELECT COUNT(F_NUM) COUNT FROM BOARD_F WHERE F_TITLE LIKE ?";
				
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+ward+"%");
					rs = pstmt.executeQuery();
					
					if(rs.next())
					{
						count = rs.getInt("count");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				}
				return count;
			}
			//메게변수로 주어진 페이지에서 한화면에 출력한 갯수많큼 dto반환-자유게시판
			public ArrayList<BOARD_FDTO> getList_F(int page, int numOfRecords){
				
				ArrayList<BOARD_FDTO> dtos = new ArrayList<BOARD_FDTO>();
				Connection con=null; PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "SELECT * FROM (SELECT ROWNUM NUM, L.* FROM (SELECT * FROM BOARD_F ORDER BY F_NUM DESC) L) WHERE NUM BETWEEN ? AND ? ";
				//1-10/11-20/21-30/
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, 1+(page-1)*numOfRecords);
					pstmt.setInt(2, page*numOfRecords);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						int num = rs.getInt("F_NUM");
						String title = rs.getString("F_TITLE");
						String date = rs.getString("F_DATE");
						String content = rs.getString("F_CONTENT");
						String user = rs.getString("U_ID");
						//레코드하나 DTO저장
						BOARD_FDTO dto = new BOARD_FDTO(num, title, date, content, user);
						dtos.add(dto);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				} 
				return dtos;//호출한 jsp파일로 DTO가 저장된 list반환
				
			}
			//메게변수로 주어진 페이지에서 한화면에 출력한 갯수많큼 dto반환-자유게시판 검색
			public ArrayList<BOARD_FDTO> getList_F(int page, int numOfRecords, String ward){
				
				ArrayList<BOARD_FDTO> dtos = new ArrayList<BOARD_FDTO>();
				Connection con=null; PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "SELECT * FROM (SELECT ROWNUM NUM, L.* FROM (SELECT * FROM BOARD_F WHERE F_TITLE LIKE ? ORDER BY F_NUM DESC) L) WHERE NUM BETWEEN ? AND ? ";
				//1-10/11-20/21-30/
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+ward+"%");
					pstmt.setInt(2, 1+(page-1)*numOfRecords);
					pstmt.setInt(3, page*numOfRecords);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						int num = rs.getInt("F_NUM");
						String title = rs.getString("F_TITLE");
						String date = rs.getString("F_DATE");
						String content = rs.getString("F_CONTENT");
						String user = rs.getString("U_ID");
						//레코드하나 DTO저장
						BOARD_FDTO dto = new BOARD_FDTO(num, title, date, content, user);
						dtos.add(dto);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				} 
				return dtos;//호출한 jsp파일로 DTO가 저장된 list반환
				
			}
			//메게변수로 주어진 페이지에서 한화면에 출력한 갯수많큼 dto반환-포토게시판 검색
			public ArrayList<BOARD_PDTO> getList_P(int page, int numOfRecords, String ward){
				
				ArrayList<BOARD_PDTO> dtos = new ArrayList<BOARD_PDTO>();
				Connection con=null; PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "SELECT * FROM (SELECT ROWNUM NUM, L.* FROM (SELECT * FROM BOARD_P WHERE P_TITLE LIKE ? ORDER BY P_NUM DESC) L) WHERE NUM BETWEEN ? AND ? ";
				//1-10/11-20/21-30/
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+ward+"%");
					pstmt.setInt(2, 1+(page-1)*numOfRecords);
					pstmt.setInt(3, page*numOfRecords);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						int num = rs.getInt("P_NUM");
						String title = rs.getString("P_TITLE");
						String date = rs.getString("P_DATE");
						String file = rs.getString("P_FILE");
						String user = rs.getString("U_ID");
						//레코드하나 DTO저장
						BOARD_PDTO dto = new BOARD_PDTO(num, title, date, file, user);
						dtos.add(dto);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				} 
				return dtos;//호출한 jsp파일로 DTO가 저장된 list반환
				
			}
			//검색된 레코드 갯수 가져오기.-포토게시판 검색
			public int getCount_P(String ward) {
				int count = 0;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "SELECT COUNT(P_NUM) COUNT FROM BOARD_P WHERE P_TITLE LIKE ?";
				
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+ward+"%");
					rs = pstmt.executeQuery();
					
					if(rs.next())
					{
						count = rs.getInt("count");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				}
				return count;
			}
			//메게변수로 주어진 페이지에서 한화면에 출력한 갯수많큼 dto반환-정보 게시판 검색
			public ArrayList<BOARD_IDTO> getList_I(int page, int numOfRecords, String ward){
				
				ArrayList<BOARD_IDTO> dtos = new ArrayList<BOARD_IDTO>();
				Connection con=null; PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "SELECT * FROM (SELECT ROWNUM NUM, L.* FROM (SELECT * FROM BOARD_I WHERE I_TITLE LIKE ? ORDER BY I_NUM DESC) L) WHERE NUM BETWEEN ? AND ? ";
				//1-10/11-20/21-30/
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+ward+"%");
					pstmt.setInt(2, 1+(page-1)*numOfRecords);
					pstmt.setInt(3, page*numOfRecords);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						int num = rs.getInt("I_NUM");
						String title = rs.getString("I_TITLE");
						String date = rs.getString("I_DATE");
						String file = rs.getString("I_FILE");
						String content = rs.getString("I_CONTENT");
						String user = rs.getString("U_ID");
						//레코드하나 DTO저장
						BOARD_IDTO dto = new BOARD_IDTO(num, title, date, file, content, user);
						dtos.add(dto);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				} 
				return dtos;//호출한 jsp파일로 DTO가 저장된 list반환
				
			}
			//검색된 레코드 갯수 가져오기.정보 게시판 검색
			public int getCount_I(String ward) {
				int count = 0;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "SELECT COUNT(I_NUM) COUNT FROM BOARD_I WHERE I_TITLE LIKE ?";
				
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+ward+"%");
					rs = pstmt.executeQuery();
					
					if(rs.next())
					{
						count = rs.getInt("count");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				}
				return count;
			}
			//메게변수로 주어진 페이지에서 한화면에 출력한 갯수많큼 dto반환-질문게시판 검색
			public ArrayList<BOARD_QDTO> getList_Q(int page, int numOfRecords, String ward){
				
				ArrayList<BOARD_QDTO> dtos = new ArrayList<BOARD_QDTO>();
				Connection con=null; PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "SELECT * FROM (SELECT ROWNUM NUM, L.* FROM (SELECT * FROM BOARD_Q WHERE Q_TITLE LIKE ? ORDER BY Q_NUM DESC) L) WHERE NUM BETWEEN ? AND ? ";
				//1-10/11-20/21-30/
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+ward+"%");
					pstmt.setInt(2, 1+(page-1)*numOfRecords);
					pstmt.setInt(3, page*numOfRecords);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						int num = rs.getInt("Q_NUM");
						String title = rs.getString("Q_TITLE");
						String date = rs.getString("Q_DATE");
						String content = rs.getString("Q_CONTENT");
						String user = rs.getString("U_ID");
						//레코드하나 DTO저장
						BOARD_QDTO dto = new BOARD_QDTO(num, title, date, content, user);
						dtos.add(dto);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				} 
				return dtos;//호출한 jsp파일로 DTO가 저장된 list반환
				
			}
			//검색된 레코드 갯수 가져오기.질문 게시판 검색
			public int getCount_Q(String ward) {
				int count = 0;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "SELECT COUNT(Q_NUM) COUNT FROM BOARD_Q WHERE Q_TITLE LIKE ?";
				
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+ward+"%");
					rs = pstmt.executeQuery();
					
					if(rs.next())
					{
						count = rs.getInt("count");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				}
				return count;
			}
			//전체 레코드 갯수 가져오기.-포토게시판
			public int getCount_P() {
				int count = 0;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "SELECT COUNT(P_NUM) COUNT FROM BOARD_P";
				
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next())
					{
						count = rs.getInt("count");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				}
				return count;
			}
			//메게변수로 주어진 페이지에서 한화면에 출력한 갯수많큼 dto반환-포토게시판
			public ArrayList<BOARD_PDTO> getList_P(int page, int numOfRecords){
				
				ArrayList<BOARD_PDTO> dtos = new ArrayList<BOARD_PDTO>();
				Connection con=null; PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "SELECT * FROM (SELECT ROWNUM NUM, L.* FROM (SELECT * FROM BOARD_P ORDER BY P_NUM DESC) L) WHERE NUM BETWEEN ? AND ? ";
				//1-10/11-20/21-30/
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, 1+(page-1)*numOfRecords);
					pstmt.setInt(2, page*numOfRecords);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						int num = rs.getInt("P_NUM");
						String title = rs.getString("P_TITLE");
						String date = rs.getString("P_DATE");
						String file = rs.getString("P_FILE");
						String user = rs.getString("U_ID");
						//레코드하나 DTO저장
						BOARD_PDTO dto = new BOARD_PDTO(num, title, date, file, user);
						dtos.add(dto);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				} 
				return dtos;//호출한 jsp파일로 DTO가 저장된 list반환
				
			}//전체 레코드 갯수 가져오기.-질문게시판
			public int getCount_Q() {
				int count = 0;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "SELECT COUNT(Q_NUM) COUNT FROM BOARD_Q";
				
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next())
					{
						count = rs.getInt("count");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				}
				return count;
			}
			//메게변수로 주어진 페이지에서 한화면에 출력한 갯수많큼 dto반환-질문게시판
			public ArrayList<BOARD_QDTO> getList_Q(int page, int numOfRecords){
				
				ArrayList<BOARD_QDTO> dtos = new ArrayList<BOARD_QDTO>();
				Connection con=null; PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "SELECT * FROM (SELECT ROWNUM NUM, L.* FROM (SELECT * FROM BOARD_Q ORDER BY Q_NUM DESC) L) WHERE NUM BETWEEN ? AND ? ";
				//1-10/11-20/21-30/
				try {
					PJ2020DAO dao = PJ2020DAO.getInstance();
					con = dao.getConnection();
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, 1+(page-1)*numOfRecords);
					pstmt.setInt(2, page*numOfRecords);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						int num = rs.getInt("Q_NUM");
						String title = rs.getString("Q_TITLE");
						String date = rs.getString("Q_DATE");
						String content = rs.getString("Q_CONTENT");
						String user = rs.getString("U_ID");
						//레코드하나 DTO저장
						BOARD_QDTO dto = new BOARD_QDTO(num, title, date, content, user);
						dtos.add(dto);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try { if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(con!=null) con.close();
					}catch(Exception e) {e.printStackTrace();}
				} 
				return dtos;//호출한 jsp파일로 DTO가 저장된 list반환
				
			}
			//답변 댓글
			//입력/변경/삭제 함수.
				public void A_BOARD_Change(BOARD_ADTO dto, String flag) {
					Connection con = null; PreparedStatement pstmt = null;
					String sql=null; ResultSet rs = null;
					try { 
						con = getConnection();
						if(flag.equals("i")) {
							sql = "INSERT INTO BOARD_A VALUES(SEQ_A_NUM.NEXTVAL, ?, ?, ?, ?)";		
							//3.sql문 준비
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, dto.getA_DATE());
							pstmt.setString(2, dto.getA_CONTENT());
							pstmt.setString(3, dto.getU_ID());
							pstmt.setInt(4, dto.getQ_NUM());
						}else if(flag.equals("u")) {
							sql = "update BOARD_Q set A_DATE=? ,A_CONTENT=? where A_NUM=?";//수정시 날짜 변경 허용?
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, dto.getA_DATE());
							pstmt.setString(2, dto.getA_CONTENT());
							pstmt.setInt(3, dto.getA_NUM());
						}else if(flag.equals("d")) {
							sql = "delete from BOARD_A where A_NUM=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, dto.getA_NUM());
						}
						pstmt.executeUpdate();
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						try {
							if(pstmt != null) pstmt.close();
							if(con != null) con.close();
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				//전체 레코드 갯수 가져오기.-게시판댓글
				public int getCount_A(int Q_NUM) {
					int count = 0;
					
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String sql = "SELECT COUNT(A_NUM) COUNT FROM BOARD_A WHERE Q_NUM = ?";
					
					try {
						PJ2020DAO dao = PJ2020DAO.getInstance();
						con = dao.getConnection();
						
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, Q_NUM);
						rs = pstmt.executeQuery();
						
						if(rs.next())
						{
							count = rs.getInt("count");
						}
						
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						try { if(rs!=null) rs.close();
							if(pstmt!=null) pstmt.close();
							if(con!=null) con.close();
						}catch(Exception e) {e.printStackTrace();}
					}
					return count;
				}
				//메게변수로 주어진 페이지에서 한화면에 출력한 갯수많큼 dto반환-게시판댓글
				public ArrayList<BOARD_ADTO> getList_A(int page, int numOfRecords, int Q_NUM){
					
					ArrayList<BOARD_ADTO> dtos = new ArrayList<BOARD_ADTO>();
					Connection con=null; PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					String sql = "SELECT * FROM (SELECT ROWNUM NUM, L.* FROM (SELECT * FROM BOARD_A WHERE Q_NUM = ? ORDER BY A_NUM) L) WHERE NUM BETWEEN ? AND ? ";
					//1-10/11-20/21-30/
					try {
						PJ2020DAO dao = PJ2020DAO.getInstance();
						con = dao.getConnection();
						
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, Q_NUM);
						pstmt.setInt(2, 1+(page-1)*numOfRecords);
						pstmt.setInt(3, page*numOfRecords);
						rs = pstmt.executeQuery();
						
						while(rs.next()) {
							int num = rs.getInt("A_NUM");//댓글번호
							String date = rs.getString("A_DATE");//댓글 날짜
							String content = rs.getString("A_CONTENT");//댓글 내용
							String id = rs.getString("U_ID");//작성자
							int qnum = rs.getInt("Q_NUM");//댓글 소속
							//레코드하나 DTO저장
							BOARD_ADTO dto = new BOARD_ADTO(num, date, content, id, qnum);
							dtos.add(dto);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						try { if(rs!=null) rs.close();
							if(pstmt!=null) pstmt.close();
							if(con!=null) con.close();
						}catch(Exception e) {e.printStackTrace();}
					} 
					return dtos;//호출한 jsp파일로 DTO가 저장된 list반환
					
				}
				//정보게시판
				//입력/변경/삭제 함수.
					public void I_BOARD_Change(BOARD_IDTO dto, String flag) {
						Connection con = null; PreparedStatement pstmt = null;
						String sql=null;
						try { 
							con = getConnection();
							if(flag.equals("i")) {
								sql = "INSERT INTO BOARD_I VALUES(SEQ_I_NUM.NEXTVAL, ?, ?, ?, ?, ?)";		
								//3.sql문 준비
								pstmt = con.prepareStatement(sql);
								pstmt.setString(1, dto.getI_TITLE());
								pstmt.setString(2, dto.getI_DATE());
								pstmt.setString(3, dto.getI_FILE());
								pstmt.setString(4, dto.getI_CONTENT());
								pstmt.setString(5, dto.getU_ID());
							}else if(flag.equals("u")) {
								sql = "update BOARD_I set I_TITLE=?, I_DATE=? ,I_CONTENT=?,I_FILE=? where I_NUM=?";//수정시 날짜 변경 허용?
								pstmt = con.prepareStatement(sql);
								pstmt.setString(1, dto.getI_TITLE());
								pstmt.setString(2, dto.getI_DATE());
								pstmt.setString(3, dto.getI_CONTENT());
								pstmt.setString(4, dto.getI_FILE());
								pstmt.setInt(5, dto.getI_NUM());
							}else if(flag.equals("d")) {
								sql = "delete from BOARD_I where I_NUM=?";
								pstmt = con.prepareStatement(sql);
								pstmt.setInt(1, dto.getI_NUM());
							}
							pstmt.executeUpdate();
						}catch(Exception e) {
							e.printStackTrace();
						}finally {
							try {
								if(pstmt != null) pstmt.close();
								if(con != null) con.close();
							}catch(Exception e) {
								e.printStackTrace();
							}
						}
					}
					//유저아이디로 닉네임 값 가져오기
					public String U_NICK(String U_ID){

							Connection con=null;
							Statement stmt = null;
							ResultSet rs = null;
							String Name= null;
							
							try {
								con = getConnection();
								stmt = con.createStatement();
															
								String sql= "select U_NAME from WRITER where U_ID=?";//받은 아이디로 네임검색
								PreparedStatement pstmt = con.prepareStatement(sql);
								pstmt.setString(1, U_ID);
								
								rs = pstmt.executeQuery();					
								
								if(rs.next())
								{
									Name = rs.getString("U_NAME");
								}
								
							}catch(Exception e) {
								e.printStackTrace();
							}finally {
								try { 
									if(rs!=null) rs.close();
									if(stmt!=null)stmt.close();
									if(con!=null) con.close();
								}catch(Exception e){e.printStackTrace();}
							}	
							return Name;//리턴
					}
					
					//정보게시판 값 하나 가져오기
					public BOARD_IDTO BOARD_I(int I_NUM){

							Connection con=null;
							Statement stmt = null;
							ResultSet rs = null;
							BOARD_IDTO dto = new BOARD_IDTO();
						
							
							try {
								con = getConnection();
								stmt = con.createStatement();
															
								String sql= "select * from BOARD_I where I_NUM=?";//받은 자유게시판 아이디로 검색
								PreparedStatement pstmt = con.prepareStatement(sql);
								pstmt.setInt(1, I_NUM);
								
								rs = pstmt.executeQuery();					
								
								while(rs.next()) {//값을 dto에 넣는다.
									dto.setI_NUM(rs.getInt("I_NUM")); 
									dto.setI_TITLE(rs.getString("I_TITLE"));
									dto.setI_DATE(rs.getString("I_DATE"));
									dto.setI_CONTENT(rs.getString("I_CONTENT"));
									dto.setI_FILE(rs.getString("I_FILE"));
									dto.setU_ID(rs.getString("U_ID"));	
								}
								
							}catch(Exception e) {
								e.printStackTrace();
							}finally {
								try { 
									if(rs!=null) rs.close();
									if(stmt!=null)stmt.close();
									if(con!=null) con.close();
								}catch(Exception e){e.printStackTrace();}
							}	
							return dto;//리턴
					}
					//전체 레코드 갯수 가져오기.-정보게시판
					public int getCount_I() {
						int count = 0;
						
						Connection con = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						String sql = "SELECT COUNT(I_NUM) COUNT FROM BOARD_I";
						
						try {
							PJ2020DAO dao = PJ2020DAO.getInstance();
							con = dao.getConnection();
							
							pstmt = con.prepareStatement(sql);
							rs = pstmt.executeQuery();
							
							if(rs.next())
							{
								count = rs.getInt("count");
							}
							
						}catch(Exception e) {
							e.printStackTrace();
						}finally {
							try { if(rs!=null) rs.close();
								if(pstmt!=null) pstmt.close();
								if(con!=null) con.close();
							}catch(Exception e) {e.printStackTrace();}
						}
						return count;
					}
					//메게변수로 주어진 페이지에서 한화면에 출력한 갯수많큼 dto반환-자유게시판
					public ArrayList<BOARD_IDTO> getList_I(int page, int numOfRecords){
						
						ArrayList<BOARD_IDTO> dtos = new ArrayList<BOARD_IDTO>();
						Connection con=null; PreparedStatement pstmt = null;
						ResultSet rs = null;
						
						String sql = "SELECT * FROM (SELECT ROWNUM NUM, L.* FROM (SELECT * FROM BOARD_I ORDER BY I_NUM DESC) L) WHERE NUM BETWEEN ? AND ? ";
						//1-10/11-20/21-30/
						try {
							PJ2020DAO dao = PJ2020DAO.getInstance();
							con = dao.getConnection();
							
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, 1+(page-1)*numOfRecords);
							pstmt.setInt(2, page*numOfRecords);
							rs = pstmt.executeQuery();
							
							while(rs.next()) {
								int num = rs.getInt("I_NUM");
								String title = rs.getString("I_TITLE");
								String date = rs.getString("I_DATE");
								String content = rs.getString("I_CONTENT");
								String file = rs.getString("I_FILE");
								String user = rs.getString("U_ID");
								//레코드하나 DTO저장
								BOARD_IDTO dto = new BOARD_IDTO(num, title, date, content, file, user);
								dtos.add(dto);
							}
						}catch(Exception e) {
							e.printStackTrace();
						}finally {
							try { if(rs!=null) rs.close();
								if(pstmt!=null) pstmt.close();
								if(con!=null) con.close();
							}catch(Exception e) {e.printStackTrace();}
						} 
						return dtos;//호출한 jsp파일로 DTO가 저장된 list반환
						
					}
}
















