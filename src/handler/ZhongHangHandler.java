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
import beans.ZhongHangDetail;

public class ZhongHangHandler {
	private List<ZhongHangDetail> details = null;
	private String startDateStr;
	private String endDateStr;
	private String cardNo;
	private String userName;
	private float beginAmount;
	private float sumConsume;
	private float sumIncome;
	private float rate;
	private String rateDate;
	
	public List<ZhongHangDetail> generate(){
		details = new ArrayList<ZhongHangDetail>();
		DateTime startDate = new DateTime(getStartDateStr());
		DateTime endDate = new DateTime(getEndDateStr());
		
		//distance days
		Days d = Days.daysBetween(startDate, endDate);
		int distance = d.getDays();
		
		for (int i=0; i<=distance; i++){
			ZhongHangDetail detail = new ZhongHangDetail();
			detail.setDate(startDate.plusDays(i));
			details.add(detail);
		}
		
		List<ZhongHangDetail> allDetail = new ArrayList<ZhongHangDetail>();
		List<ZhongHangDetail> monthly = new ArrayList<ZhongHangDetail>();
		DateTime now = startDate;
		int loopMonth = 0;
		while (now.getYear()<=endDate.getYear()){
			System.out.println(now.getMonthOfYear()+"-"+now.getYear()+"   loopMonth"+loopMonth);
			now = startDate.plusMonths(loopMonth);
			System.out.println("---------------------------------------------------------------"+now.getMonthOfYear()+"-"+now.getYear()+"   loopMonth"+loopMonth);
			monthly = monthlyDetail(details,now.getMonthOfYear(),now);
			allDetail.addAll(monthly);
			if (now.getYear() == endDate.getYear() && now.getMonthOfYear() > endDate.getMonthOfYear()){
				break;
			}
			loopMonth++;
		}
		
		return allDetail;
	}
	
	public List<ZhongHangDetail> monthlyDetail(List<ZhongHangDetail> detailSet, int month, DateTime startDate){
		List<ZhongHangDetail> monthlyDetail = new ArrayList<ZhongHangDetail>();
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
				ZhongHangDetail detail = new ZhongHangDetail();
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
						detail.setOperation(randomOper());
						detail.setBranch(randomBranch());
						detail.setSubject("CNY"); 
						detail.setObjectAccount(randomObject());
						String[] trade = randomDigst(1);
						detail.setTradeCode(trade[0]);
						detail.setTradeName(trade[1]);
						detail.setTradeType("01");
					}else{//收入
						float in = (float)((int)((sumIncome-sumIncome/days)*(RandomNumUtils.getInt(10, 25)*0.01)/100)*100);
						if ( in > avgIncome || avgIncome == 0) continue;
						beginAmount = beginAmount + in ;
						avgIncome = avgIncome - in ;
						detail.setDate(randomDate);
						detail.setAmount(beginAmount);
						detail.setIn(in);
						detail.setOut(0);
						detail.setOperation(randomOper());
						detail.setBranch(randomBranch());
						detail.setSubject("CNY"); 
						detail.setObjectAccount(randomObject());
						String[] trade = randomDigst(0);
						detail.setTradeCode(trade[0]);
						detail.setTradeName(trade[1]);
						detail.setTradeType("01");
				}
				monthlyDetail.add(detail);
			}
		}
		return monthlyDetail;
	}
	
	/**
	 * 摘要类型
	 * @param type
	 * @return
	 */
	public String[] randomDigst(int type){
		String[][] cun= {
				{"01045","无折转客户帐"},
				{"01055","无折转客户帐转帐"},
				{"01010","无折现金存款"},
				{"01030 ","无折直接贷记"}
		};
		
		String[][] qu = {
				{"01055","无折客户帐转帐"}
		};
		
		if (type ==0) 
			return cun[RandomNumUtils.getInt(0, cun.length)];
		else
			return qu[RandomNumUtils.getInt(0, qu.length)];
	}
	
	public String randomBranch(){
		String[] branches = {"04386","04822","04380","00557","00350","04814"};
		return branches[RandomNumUtils.getInt(0, branches.length)];
	}
	
	public String randomOper(){
		String[] opers = {"9880100","0569060","9997707","1142359","","8300341","9992922","0504542"};
		return opers[RandomNumUtils.getInt(0, opers.length)];
	}
	
	public String randomObject(){
		String[] objs = {"90481400191310001","283061391407","290860723498","283055846512","306453358299"
				,"301260410349","283061391407","303853358296","90481200183210027"};
		return objs[RandomNumUtils.getInt(0, objs.length)];
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
