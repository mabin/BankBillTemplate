package beans;

import java.util.Date;

import org.joda.time.DateTime;

public abstract class Detail {
	private DateTime date;
	private String operation;
	private float in = 0;
	private float out = 0;
	private float amount = 0;
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public float getIn() {
		return in;
	}
	public void setIn(float in) {
		this.in = in;
	}
	public float getOut() {
		return out;
	}
	public void setOut(float out) {
		this.out = out;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
}
