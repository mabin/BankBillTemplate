package beans;

public class GongHangDetail extends Detail {
	private String serial = "";
	private String subject = "";
	private String sendOrg = "";
	private String recorder = "";
	private String around = "";
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSendOrg() {
		return sendOrg;
	}
	public void setSendOrg(String sendOrg) {
		this.sendOrg = sendOrg;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	@Override
	public String toString() {
		return "DaLianDetail [serial=" + serial + ", subject=" + subject
				+ ", sendOrg=" + sendOrg + ", recorder=" + recorder
				+ ", getSerial()=" + getSerial() + ", getSubject()="
				+ getSubject() + ", getSendOrg()=" + getSendOrg()
				+ ", getRecorder()=" + getRecorder() + ", getDate()="
				+ getDate() + ", getOperation()=" + getOperation()
				+ ", getIn()=" + getIn() + ", getOut()=" + getOut()
				+ ", getAmount()=" + getAmount() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public String getAround() {
		return around;
	}
	public void setAround(String around) {
		this.around = around;
	}
	
	
}
