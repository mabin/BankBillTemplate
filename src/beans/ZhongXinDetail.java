package beans;

public class ZhongXinDetail extends Detail {
	private String objectAccount ;
	private String nowRedirect;
	private String subject;
	public String getObjectAccount() {
		return objectAccount;
	}
	public void setObjectAccount(String objectAccount) {
		this.objectAccount = objectAccount;
	}
	public String getNowRedirect() {
		return nowRedirect;
	}
	public void setNowRedirect(String nowRedirect) {
		this.nowRedirect = nowRedirect;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "ZhongXinDetail [objectAccount=" + objectAccount
				+ ", nowRedirect=" + nowRedirect + ", subject=" + subject
				+ ", getObjectAccount()=" + getObjectAccount()
				+ ", getNowRedirect()=" + getNowRedirect() + ", getSubject()="
				+ getSubject() + "]";
	}
	
	
}
