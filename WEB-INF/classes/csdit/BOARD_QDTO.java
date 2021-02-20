package csdit;

public class BOARD_QDTO {
	int Q_NUM;
	String Q_TITLE;
	String Q_DATE;
	String Q_CONTENT;
	String U_ID;//유저아이디
	
	public BOARD_QDTO() {}
	
	public BOARD_QDTO(int Q_NUM, String Q_TITLE, String Q_DATE, String Q_CONTENT,String U_ID) {//메개변수 생성자
		super();
		this.Q_NUM = Q_NUM;
		this.Q_TITLE = Q_TITLE;
		this.Q_DATE = Q_DATE;
		this.Q_CONTENT = Q_CONTENT;
		this.U_ID = U_ID;
	}
	
	
	public int getQ_NUM() {
		return Q_NUM;
	}
	public void setQ_NUM(int q_NUM) {
		Q_NUM = q_NUM;
	}
	public String getQ_TITLE() {
		return Q_TITLE;
	}
	public void setQ_TITLE(String q_TITLE) {
		Q_TITLE = q_TITLE;
	}
	public String getQ_DATE() {
		return Q_DATE;
	}
	public void setQ_DATE(String q_DATE) {
		Q_DATE = q_DATE;
	}
	public String getQ_CONTENT() {
		return Q_CONTENT;
	}
	public void setQ_CONTENT(String q_CONTENT) {
		Q_CONTENT = q_CONTENT;
	}
	public String getU_ID() {
		return U_ID;
	}
	public void setU_ID(String q_ID) {
		U_ID = q_ID;
	}
	
}
