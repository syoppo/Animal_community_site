package csdit;

public class BOARD_PDTO {
	int P_NUM;
	String P_TITLE;
	String P_DATE;
	String P_FILE;
	String P_ID;//유저아이디
	
	public BOARD_PDTO() {}
	
	public BOARD_PDTO(int P_NUM, String P_TITLE, String P_DATE, String P_FILE,String P_ID) {//메개변수 생성자
		super();
		this.P_NUM = P_NUM;
		this.P_TITLE = P_TITLE;
		this.P_DATE = P_DATE;
		this.P_FILE = P_FILE;
		this.P_ID = P_ID;
	}

	public int getP_NUM() {
		return P_NUM;
	}

	public void setP_NUM(int p_NUM) {
		P_NUM = p_NUM;
	}

	public String getP_TITLE() {
		return P_TITLE;
	}

	public void setP_TITLE(String p_TITLE) {
		P_TITLE = p_TITLE;
	}

	public String getP_DATE() {
		return P_DATE;
	}

	public void setP_DATE(String p_DATE) {
		P_DATE = p_DATE;
	}

	public String getP_FILE() {
		return P_FILE;
	}

	public void setP_FILE(String p_FILE) {
		P_FILE = p_FILE;
	}

	public String getP_ID() {
		return P_ID;
	}

	public void setP_ID(String p_ID) {
		P_ID = p_ID;
	}
	
}
