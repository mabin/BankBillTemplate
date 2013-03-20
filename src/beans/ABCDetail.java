package beans;

public class ABCDetail extends Detail {
	private String subject = "";
	private String zhihang = "";
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "ABCDetail [subject=" + subject + ", getDate()=" + getDate()
				+ ", getOperation()=" + getOperation() + ", getIn()=" + getIn()
				+ ", getOut()=" + getOut() + ", getAmount()=" + getAmount()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public String getZhihang() {
		return zhihang;
	}
	public void setZhihang(String zhihang) {
		this.zhihang = zhihang;
	}
	
}
