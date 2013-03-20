package beans;

public class ZhongHangDetail extends Detail {
	private String branch;
	private String tradeCode;
	private String subject;
	private String tradeType;
	private String objectAccount;
	private String tradeName;
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getObjectAccount() {
		return objectAccount;
	}
	public void setObjectAccount(String objectAccount) {
		this.objectAccount = objectAccount;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	@Override
	public String toString() {
		return "ZhongHangDetail [branch=" + branch + ", tradeCode=" + tradeCode
				+ ", subject=" + subject + ", tradeType=" + tradeType
				+ ", objectAccount=" + objectAccount + ", tradeName="
				+ tradeName + "]";
	}
	
	
}
