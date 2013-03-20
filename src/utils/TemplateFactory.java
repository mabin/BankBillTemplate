package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import beans.Bill;
import beans.Detail;

public class TemplateFactory {
	public static double OVERAGE= 0 ;
	private Bill bill =  null;
	private String timeLine = null ;
	private String struct = null ;
	private List<String> BankBranches = new ArrayList<String>();
	BanksDataUtils bankData = null;
	
	public void packageData(String bankName, 
									String bankCard,
									String timeLine, 
									double balance, 
									double rate, 
									double consume,
									double income, 
									String struct) {
		bankData = new BanksDataUtils();
		String printOrg = null ;	//打印机构
		
		bill = new Bill();
		bill.setBankName(bankName);
		if (bankName.equals("中国农业银行")){
			bill.setTitle("金穗借记卡明细对账单");
			bankData.setBankType("ABC");
			printOrg = "打印机构：中国农行股份有限公司";
		}
		else if (bankName.equals("中国交通银行")){
			bill.setTitle("中国交通银行");
			bankData.setBankType("COMM");
			printOrg = "打印机构：中国交通银行股份有限公司";
		}
		BankBranches = bankData.getList();
		
		bill.setPrintOrg(printOrg+BankBranches.get(RandomNumUtils.getInt(0, BankBranches.size())));
		
		bill.setCardNo(bankCard);
		bill.setPrintDate(Calendar.getInstance().getTime());
		bill.setPageNum(1);
		bill.setCardOwner("匿名");
		bill.setAccountSequence("0000");
		bill.setCashType("人民币");
		bill.setIncome(income);
		bill.setExpenses(consume);
		bill.setBalance(balance);
		OVERAGE = balance;
		this.timeLine = timeLine;
		this.struct = struct ;
		System.out.println(bill.toString());
	}
	
	public void createBillList(){
		//明细List
		List<Detail> details = new ArrayList<Detail>();
		//转存收入比例
		float cash = Float.valueOf(struct.split("-")[0]);
		float transfer = Float.valueOf(struct.split("-")[1]);
		
		int beginYear = Integer.parseInt(timeLine.split("-")[0].substring(0,4)) ;
		int beginMonth = Integer.parseInt(timeLine.split("-")[0].substring(4,6));
		int endYear = Integer.parseInt(timeLine.split("-")[1].substring(0,4)) ;
		int endMonth = Integer.parseInt(timeLine.split("-")[1].substring(4,6));

		int months = 0;
		/**
		 * 20100301 - 2012-07-01
		 * (12-3+1)+7+12*(12-10-1)
		 
		if ((endYear-beginYear)>0 ){
			months = (12-beginMonth+1)+endMonth+12*(endYear-beginYear-1);
		}
		else if ((endYear-beginYear)==0){
			months = endMonth-beginMonth+1;
		}

		for (int i=0; i<months; i++){

			int account = 0 ;
			
			if (i%2==0){
				details.add(produceDetail(1,bill.getIncome()*0.3+RandomNumUtils.getInt(100, 700),"2012","03","01",1)); //现金存款
				details.add(produceDetail(2,bill.getIncome()*0.7-RandomNumUtils.getInt(100, 700),"2012","03","01",1)); //转帐存款
				while ( account < bill.getExpenses()+RandomNumUtils.getInt(100, 400) ){
					double amount = RandomNumUtils.getFloat(45, 896);
					produceDetail(3,amount,"2012","03","01",1);
					account += amount;
				}
			}else{
				details.add(produceDetail(1,bill.getIncome()*0.3-RandomNumUtils.getInt(100, 700),"2012","03","01",1)); //现金存款
				details.add(produceDetail(2,bill.getIncome()*0.7+RandomNumUtils.getInt(100, 700),"2012","03","01",1)); //转帐存款
				while ( account < bill.getExpenses()+RandomNumUtils.getInt(100, 200) ){
					double amount = RandomNumUtils.getFloat(45, 896);
					produceDetail(3,amount,"2012","03","01",1);
					account += amount;
				}
			}
			
			
			
		}*/
			
	}
	
	/**
	 * 
	 * @param type 1:现金存款，2：转账存款，3：消费
	 * @param amount
	 * @param date
	 * @return
	 
	public Detail produceDetail(int type,double amount,String year,String month,String day,int times){
		Detail detail = new Detail();
		
		switch (type){
		case 1:
			detail.setDate(year+""+month+""+(30/11*times));
			detail.setAddress(BankBranches.get(RandomNumUtils.getInt(0, bankData.getList().size())));
			detail.setOperation("现金存入");
			detail.setSaveIn(amount);
			detail.setAmount(OVERAGE=OVERAGE+amount);
			break;
		case 2:
			detail.setDate(year+""+month+""+(30/11*times));
			detail.setAddress(BankBranches.get(RandomNumUtils.getInt(0, bankData.getList().size())));
			detail.setOperation("转帐存入");
			detail.setSaveIn(amount);
			detail.setAmount(OVERAGE=OVERAGE+amount);
			break;
		case 3:
			detail.setDate(year+""+month+""+(30/11*times));
			detail.setAddress(BankBranches.get(RandomNumUtils.getInt(0, bankData.getList().size())));
			detail.setOperation("刷卡消费");
			detail.setExpensesOut(amount);
			detail.setAmount(OVERAGE=OVERAGE-amount);
			break;
		}
		System.out.println(detail.toString());
		return detail;
	}
	*/
}















