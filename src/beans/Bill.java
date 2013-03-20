package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill {
	private String bankName;
	private String title;
	private String printOrg;
	private Date printDate; 
	private int pageNum;
	private String cardOwner;
	private String cardNo;
	private String accountSequence;
	private String cashType;
	private double income;
	private double expenses;
	private double balance; //期初余额
	private List<Detail> details = new ArrayList<Detail>();
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrintOrg() {
		return printOrg;
	}
	public void setPrintOrg(String printOrg) {
		this.printOrg = printOrg;
	}
	public Date getPrintDate() {
		return printDate;
	}
	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public String getCardOwner() {
		return cardOwner;
	}
	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getAccountSequence() {
		return accountSequence;
	}
	public void setAccountSequence(String accountSequence) {
		this.accountSequence = accountSequence;
	}
	public String getCashType() {
		return cashType;
	}
	public void setCashType(String cashType) {
		this.cashType = cashType;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public double getExpenses() {
		return expenses;
	}
	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	public List<Detail> getDetails() {
		return details;
	}
	public void setDetails(List<Detail> details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "Bill [bankName=" + bankName + ", title=" + title
				+ ", printOrg=" + printOrg + ", printDate=" + printDate
				+ ", pageNum=" + pageNum + ", cardOwner=" + cardOwner
				+ ", cardNo=" + cardNo + ", accountSequence=" + accountSequence
				+ ", cashType=" + cashType + ", income=" + income
				+ ", expenses=" + expenses + ", details=" + details + "]";
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
}
