package csdit;

public class BOARD_IDTO {
	int I_NUM;
	String I_TITLE;
	String I_DATE;
	String I_CONTENT;
	String I_FILE;
	String U_ID;
	
	public BOARD_IDTO() {}
	
	public BOARD_IDTO(int I_NUM, String I_TITLE, String I_DATE, String I_CONTENT, String I_FILE,String U_ID) {//메개변수 생성자
		super();
		this.I_NUM = I_NUM;
		this.I_TITLE = I_TITLE;
		this.I_DATE = I_DATE;
		this.I_CONTENT = I_CONTENT;
		this.I_FILE = I_FILE;
		this.U_ID = U_ID;
	}
	
	public int getI_NUM() {
		return I_NUM;
	}
	public void setI_NUM(int i_NUM) {
		I_NUM = i_NUM;
	}
	public String getI_TITLE() {
		return I_TITLE;
	}
	public void setI_TITLE(String i_TITLE) {
		I_TITLE = i_TITLE;
	}
	public String getI_DATE() {
		return I_DATE;
	}
	public void setI_DATE(String i_DATE) {
		I_DATE = i_DATE;
	}
	public String getI_CONTENT() {
		return I_CONTENT;
	}
	public void setI_CONTENT(String i_CONTENT) {
		I_CONTENT = i_CONTENT;
	}
	public String getI_FILE() {
		return I_FILE;
	}
	public void setI_FILE(String i_FILE) {
		I_FILE = i_FILE;
	}
	public String getU_ID() {
		return U_ID;
	}
	public void setU_ID(String u_ID) {
		U_ID = u_ID;
	}
	
}
