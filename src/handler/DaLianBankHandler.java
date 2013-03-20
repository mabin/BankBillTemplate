package handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Days;

import utils.RandomDateUtils;
import utils.RandomNumUtils;
import utils.TimeUtils;

import beans.DaLianDetail;
import beans.Detail;

public class DaLianBankHandler {
	private List<DaLianDetail> details = null;
	private String startDateStr;
	private String endDateStr;
	private String cardNo;
	private String userName;
	private float beginAmount;
	private float sumConsume;
	private float sumIncome;
	private String salaryDate;
	private float salary;
	private float rate;
	private String rateDate;
	
	public List<DaLianDetail> generate(){
		details = new ArrayList<DaLianDetail>();
		DateTime startDate = new DateTime(getStartDateStr());
		DateTime endDate = new DateTime(getEndDateStr());
		
		//distance days
		Days d = Days.daysBetween(startDate, endDate);
		int distance = d.getDays();
		
		for (int i=0; i<=distance; i++){
			DaLianDetail detail = new DaLianDetail();
			detail.setDate(startDate.plusDays(i));
			details.add(detail);
		}
		
		List<DaLianDetail> allDetail = new ArrayList<DaLianDetail>();
		//int compareMonth = TimeUtils.compareMonths(startDateStr, endDateStr);
		List<DaLianDetail> monthly = new ArrayList<DaLianDetail>();
		for (int i=startDate.getMonthOfYear(); i<=endDate.getMonthOfYear(); i++){
			//System.out.println("月份："+i+" pluse month "+startDate.getMonthOfYear());
			monthly = monthlyDetail(details,i,startDate);
			startDate = startDate.plusMonths(1);
			allDetail.addAll(monthly);
		}
		
		return allDetail;
	}
	
	public List<DaLianDetail> monthlyDetail(List<DaLianDetail> detailSet, int month, DateTime startDate){
		List<DaLianDetail> monthlyDetail = new ArrayList<DaLianDetail>();
		//找出本月包含还有多少天,至少有一天
		int days = 1;
		for (Detail detail : detailSet){
			if (detail.getDate().getMonthOfYear() == month){
				days++;
			}
		}
		
		//根据天数，算出平均消费总额和平均其他收入总额
		float avgConsume = sumConsume-sumConsume/days;
		float avgIncome = sumIncome-sumIncome/days;
		
		DateTime a = startDate;
		
		//发放工资标记
		boolean salaryFlag = false ;
		//生成流水明细
		while ( avgConsume >0 && avgIncome >0 && a.getMonthOfYear() == month){
			
			//DateTime randomDate = RandomDateUtils.getDate(startDateStr, endDateStr);
			int randomNum = RandomNumUtils.getInt(1,5);
//			System.out.println("loop month "+month);
			//DateTime randomDate = a.plusDays(RandomNumUtils.getInt(1, 6));
//			System.out.print(month+" === "+a.getYear()+"-"+a.getMonthOfYear()+"-"+a.getDayOfMonth());
			DateTime randomDate = a.plusDays(randomNum);
//			System.out.println(",plus "+randomNum+" days , is "+randomDate.getYear()+"-"+randomDate.getMonthOfYear()+"-"+randomDate.getDayOfMonth());
			a = randomDate;
			//if (a.getMonthOfYear()!=month){a = new DateTime(startDateStr);}
			if ( randomDate.getMonthOfYear() == month){
				DaLianDetail detail = new DaLianDetail();
				
				if ( randomDate.getDayOfMonth() == Integer.parseInt(salaryDate) && salaryFlag == false){
					//发薪日
					beginAmount = beginAmount + salary;
					detail.setDate(randomDate);
					detail.setIn(salary);
					detail.setOut(0);
					detail.setAmount(beginAmount);
					detail.setOperation("存");
					detail.setSubject("TR");
					detail.setRecorder(randomRecorder());
					detail.setSendOrg(randomSendOrg());
					salaryFlag = true ;
				}else{
					if ( RandomNumUtils.getInt(1, 100) % 2 == 0){//支出
						
						float cons = (float)((int)((sumConsume-sumConsume/days)*(RandomNumUtils.getInt(10, 25)*0.01)/10)*10);
						 //余额为0或消费金额大于余额，不能消费
						if (beginAmount == 0 ||  cons > beginAmount) continue;
						beginAmount = beginAmount - cons ;
						avgConsume = avgConsume - cons;
						
						detail.setDate(randomDate);
						detail.setAmount(beginAmount);
						detail.setOut(cons);
						detail.setIn(0);
						detail.setOperation("取");
						detail.setSubject(randomDigst(1));
						detail.setRecorder(randomRecorder());
						detail.setSendOrg(randomSendOrg());
					}else{//收入
						
						float in = (float)((int)((sumIncome-sumIncome/days)*(RandomNumUtils.getInt(10, 25)*0.01)/10)*10);
						if ( in > avgIncome || avgIncome == 0) continue;
						beginAmount = beginAmount + in ;
						avgIncome = avgIncome - in ;
						detail.setDate(randomDate);
						detail.setAmount(beginAmount);
						detail.setIn(in);
						detail.setOut(0);
						detail.setOperation("存");
						detail.setSubject(randomDigst(0));
						detail.setRecorder(randomRecorder());
						detail.setSendOrg(randomSendOrg());
					}
				}
				monthlyDetail.add(detail);
			}
		}
		if (!salaryFlag){
			//发薪日
			DaLianDetail detail = new DaLianDetail();
			beginAmount = beginAmount + salary;
			String salaryYear = String.valueOf(a.getYear());
			//System.out.println("last month "+month);
			String salaryMonth = String.valueOf(month);
			String dd = salaryYear+"-"+salaryMonth+"-"+salaryDate;
			detail.setDate(new DateTime(dd));
			detail.setIn(salary);
			detail.setOut(0);
			detail.setAmount(beginAmount);
			detail.setOperation("存");
			detail.setSubject("TR");
			detail.setRecorder(randomRecorder());
			detail.setSendOrg(randomSendOrg());
			monthlyDetail.add(detail);
			
		}
		return monthlyDetail;
	}
	
	/**
	 * 摘要类型
	 * 0：存 1：取
	 * WY代表网银转帐，LZ代表联行来帐，TR代表退款，转帐续存，CS代表现存，TR代表工资代发，AC代表ATM取款，DF代表其它代付
	 * @param type
	 * @return
	 */
	public String randomDigst(int type){
		String[] inArray = {"WY","LZ","TR","CS"};
		String[] outArray = {"AC","DF"};
		if (type == 0){
			return inArray[RandomNumUtils.getInt(0, inArray.length)];
		}else{
			return outArray[RandomNumUtils.getInt(0, outArray.length)];
		}
	}
	
	/**
	 * 发送机构
	 * @return
	 */
	public String randomSendOrg(){
		String[] org = {"0010106","0019901","0010605","0016001"};
		return org[RandomNumUtils.getInt(0, org.length)];
	}
	
	/**
	 * 记账员
	 * @return
	 */
	public String randomRecorder(){
		String[] recorder = {"V9902","V9901","0007","N9999","A0007","A0001"};
		return recorder[RandomNumUtils.getInt(0, recorder.length)];
	}


	public List<DaLianDetail> getDetails() {
		return details;
	}

	public void setDetails(List<DaLianDetail> details) {
		this.details = details;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public float getBeginAmount() {
		return beginAmount;
	}

	public void setBeginAmount(float beginAmount) {
		this.beginAmount = beginAmount;
	}

	public float getSumConsume() {
		return sumConsume;
	}

	public void setSumConsume(float sumConsume) {
		this.sumConsume = sumConsume;
	}

	public float getSumIncome() {
		return sumIncome;
	}

	public void setSumIncome(float sumIncome) {
		this.sumIncome = sumIncome;
	}

	public String getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getRateDate() {
		return rateDate;
	}

	public void setRateDate(String rateDate) {
		this.rateDate = rateDate;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}
	
}
