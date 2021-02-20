package csdit;

public class BOARD_FDTO {
	int F_NUM;
	String F_TITLE;
	String F_DATE;
	String F_CONTENT;
	String U_ID;
	
	

	public BOARD_FDTO() {}
	
	public BOARD_FDTO(int F_NUM, String F_TITLE, String F_DATE, String F_CONTENT,String U_ID) {//메개변수 생성자
		super();
		this.F_NUM = F_NUM;
		this.F_TITLE = F_TITLE;
		this.F_DATE = F_DATE;
		this.F_CONTENT = F_CONTENT;
		this.U_ID = U_ID;
	}
	
	public int getF_NUM() {
		return F_NUM;
	}
	public void setF_NUM(int f_NUM) {
		F_NUM = f_NUM;
	}
	public String getF_TITLE() {
		return F_TITLE;
	}
	public void setF_TITLE(String f_TITLE) {
		F_TITLE = f_TITLE;
	}
	public String getF_DATE() {
		return F_DATE;
	}
	public void setF_DATE(String f_DATE) {
		F_DATE = f_DATE;
	}
	public String getF_CONTENT() {
		return F_CONTENT;
	}
	public void setF_CONTENT(String f_CONTENT) {
		F_CONTENT = f_CONTENT;
	}
	public String getU_ID() {
		return U_ID;
	}
	public void setU_ID(String u_ID) {
		U_ID = u_ID;
	}
}
