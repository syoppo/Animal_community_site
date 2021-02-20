package csdit;

public class BOARD_ADTO {
	int A_NUM;
	String A_DATE;
	String A_CONTENT;
	String U_ID;
	int Q_NUM;
	
	public BOARD_ADTO() {}
	
	public BOARD_ADTO(int A_NUM, String A_DATE, String A_CONTENT, String U_ID, int Q_NUM) {//메개변수 생성자
		super();
		this.A_NUM = A_NUM;
		this.A_DATE = A_DATE;
		this.A_CONTENT = A_CONTENT;
		this.U_ID = U_ID;
		this.Q_NUM = Q_NUM;
	}
	
	
	public int getA_NUM() {
		return A_NUM;
	}
	public void setA_NUM(int a_NUM) {
		A_NUM = a_NUM;
	}
	public String getA_DATE() {
		return A_DATE;
	}
	public void setA_DATE(String a_DATE) {
		A_DATE = a_DATE;
	}
	public String getA_CONTENT() {
		return A_CONTENT;
	}
	public void setA_CONTENT(String a_CONTENT) {
		A_CONTENT = a_CONTENT;
	}
	public String getU_ID() {
		return U_ID;
	}
	public void setU_ID(String u_ID) {
		U_ID = u_ID;
	}
	public int getQ_NUM() {
		return Q_NUM;
	}
	public void setQ_NUM(int q_NUM) {
		Q_NUM = q_NUM;
	}
	
}
