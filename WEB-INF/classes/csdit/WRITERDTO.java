package csdit;

public class WRITERDTO {
	private String id;
	private String pwd;
	private String name;
	
	public WRITERDTO() {}
	
	public WRITERDTO(String id, String pwd, String name) {//메개변수 생성자
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
