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

import beans.GongHangDetail;
import beans.Detail;

public class GongHangHandler {
	private List<GongHangDetail> details = null;
	private String startDateStr;
	private String endDateStr;
	private String cardNo;
	private String userName;
	private float beginAmount;
	private float sumConsume;
	private float sumIncome;
	//private String salaryDate;
	//private float salary;
	private float rate;
	private String rateDate;
	
	public List<GongHangDetail> generate(){
		details = new ArrayList<GongHangDetail>();
		DateTime startDate = new DateTime(getStartDateStr());
		DateTime endDate = new DateTime(getEndDateStr());
		
		//distance days
		Days d = Days.daysBetween(startDate, endDate);
		int distance = d.getDays();
		
		for (int i=0; i<=distance; i++){
			GongHangDetail detail = new GongHangDetail();
			detail.setDate(startDate.plusDays(i));
			details.add(detail);
		}
		
		List<GongHangDetail> allDetail = new ArrayList<GongHangDetail>();
		//int compareMonth = TimeUtils.compareMonths(startDateStr, endDateStr);
		List<GongHangDetail> monthly = new ArrayList<GongHangDetail>();
//		for (int i=startDate.getMonthOfYear(); i<=endDate.getMonthOfYear(); i++){
//			//System.out.println("月份："+i+" pluse month "+startDate.getMonthOfYear());
//			monthly = monthlyDetail(details,i,startDate);
//			startDate = startDate.plusMonths(1);
//			allDetail.addAll(monthly);
//		}
//		
		DateTime now = startDate;
		int loopMonth = 0;
		while (now.getYear()<=endDate.getYear()){
			System.out.println(now.getMonthOfYear()+"-"+now.getYear()+"   loopMonth"+loopMonth);
			now = startDate.plusMonths(loopMonth);
			System.out.println("---------------------------------------------------------------"+now.getMonthOfYear()+"-"+now.getYear()+"   loopMonth"+loopMonth);
			monthly = monthlyDetail(details,now.getMonthOfYear(),now);
			allDetail.addAll(monthly);
//			if (now.getYear()>endDate.getYear() && now.getMonthOfYear() > endDate.getMonthOfYear()){
//				break;
//			}
			if (now.getYear() == endDate.getYear() && now.getMonthOfYear() > endDate.getMonthOfYear()){
				break;
			}
			loopMonth++;
		}
		
		return allDetail;
	}
	
	public List<GongHangDetail> monthlyDetail(List<GongHangDetail> detailSet, int month, DateTime startDate){
		List<GongHangDetail> monthlyDetail = new ArrayList<GongHangDetail>();
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
		
		//生成流水明细
		while ( avgConsume >0 && avgIncome >0 && a.getMonthOfYear() == month){
			int randomNum = RandomNumUtils.getInt(1,5);
			DateTime randomDate = a.plusDays(randomNum);
			a = randomDate;
			if ( randomDate.getMonthOfYear() == month){
				GongHangDetail detail = new GongHangDetail();
					if ( RandomNumUtils.getInt(1, 100) % 2 == 0){//支出
						float cons = (float)((int)((sumConsume-sumConsume/days)*(RandomNumUtils.getInt(10, 25)*0.01)/100)*100);
						 //余额为0或消费金额大于余额，不能消费
						if (beginAmount == 0 ||  cons > beginAmount) continue;
						beginAmount = beginAmount - cons ;
						avgConsume = avgConsume - cons;
						detail.setDate(randomDate);
						detail.setAmount(beginAmount);
						detail.setOut(cons);
						detail.setIn(0);
						detail.setOperation("借");
						String[] digst = randomDigst(1);
						detail.setSubject(digst[0]); //"52059 跨行"
						detail.setRecorder(digst[1]);//"3400 2001"
						detail.setSendOrg(digst[2]);//"00023 65"
					}else{//收入
						float in = (float)((int)((sumIncome-sumIncome/days)*(RandomNumUtils.getInt(10, 25)*0.01)/100)*100);
						if ( in > avgIncome || avgIncome == 0) continue;
						beginAmount = beginAmount + in ;
						avgIncome = avgIncome - in ;
						detail.setDate(randomDate);
						detail.setAmount(beginAmount);
						detail.setIn(in);
						detail.setOut(0);
						detail.setOperation("贷");
						String[] digst = randomDigst(0);
						detail.setSubject(digst[0]);
						detail.setRecorder(digst[1]);
						detail.setSendOrg(digst[2]);
				}
				monthlyDetail.add(detail);
			}
		}
		return monthlyDetail;
	}
	
	/**
	 * 摘要类型
	 * 0：贷 1：借
	 * WY代表网银转帐，LZ代表联行来帐，TR代表退款，转帐续存，CS代表现存，TR代表工资代发，AC代表ATM取款，DF代表其它代付
	 * @param type
	 * @return
	 */
	public String[] randomDigst(int type){
		String[][] dai= {
				{"52059 跨行","3400     2001","00023 65"},
				{"9960 跨行","3400     2001","00012 网上银行"},
				{"2917 卡存","3400     0729","06237 柜面交易"},
				{"2920 卡存","3400     0729","06232 柜面交易"},
				{"2805 ATM转帐","3400     0748","00014 ATM交易"}
		};
		
		String[][] jie = {
				{"34024 网转","3400     2001","00012 网上银行"},
				{"52019 快捷","3400     0729","00070 中间业务后台方式"},
				{"34009 网转","3400     2001","00012 网上银行"},
				{"34009 网转","3400     2001","00012 网上银行"},
				{"52078 跨行","3400     2033","00012 网上银行"},
				{"53017 联通","3400     0729","00010 网上银行"},
				{"52139 跨行汇款","3400     2001","00012 网上银行"},
				{"2803 ATM取款","3400     2101","09999 ATM交易"}
		};
		
		if (type ==0) 
			return dai[RandomNumUtils.getInt(0, dai.length)];
		else
			return jie[RandomNumUtils.getInt(0, jie.length)];
	}
	

	public List<GongHangDetail> getDetails() {
		return details;
	}

	public void setDetails(List<GongHangDetail> details) {
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
