package frame;

import handler.ABCBankExcel;
import handler.ABCBankHandler;
import handler.DaLianBankExcel;
import handler.DaLianBankHandler;
import handler.GongHangExcel;
import handler.GongHangHandler;
import handler.ZhongHangExcel;
import handler.ZhongHangHandler;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.ExcelUtils;

import beans.ZhongHangDetail;

public class ZhongXinPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 797350190770933724L;

	private JLabel cardNoLabel,nameLabel,beginAmountLabel,currentTimeLabel,
				incomeLabel,consumeLabel,printOrgLabel;
	private JTextField cardNoField,nameField,currentTimeField,
		incomeField,consumeField,beginAmountField,printOrgField;
	private JButton processBtn;
	
	public ZhongXinPanel(){
		this.setLayout(new FlowLayout());
		
		cardNoLabel = new TemplateLabel("银行卡号");
		nameLabel = new TemplateLabel("用户名称");
		currentTimeLabel = new TemplateLabel("起止时间");
		beginAmountLabel = new TemplateLabel("期初余额");
		incomeLabel =  new TemplateLabel("月均收入");
		consumeLabel = new TemplateLabel("月均消费");
		printOrgLabel = new TemplateLabel("打印网点");
		
		cardNoField = new TemplateTextField(15);
		nameField =new TemplateTextField(15);
		currentTimeField = new TemplateTextField(15);
		beginAmountField = new TemplateTextField(15);
		incomeField = new TemplateTextField(15);
		consumeField = new TemplateTextField(15);
		printOrgField = new TemplateTextField(15);
		
		processBtn = new JButton("生成文档");
		
		
		cardNoField.setText("62210011010101");
		nameField.setText("张三");
		currentTimeField.setText("2013-01-11#2013-07-12");
		beginAmountField.setText("10000");
		incomeField.setText("5000");
		consumeField.setText("4500");
		printOrgField.setText("大连开发区滨海分理处");
		
		this.add(cardNoLabel);
		this.add(cardNoField);
		this.add(nameLabel);
		this.add(nameField);
		this.add(currentTimeLabel);
		this.add(currentTimeField);
		this.add(beginAmountLabel);
		this.add(beginAmountField);
		this.add(incomeLabel);
		this.add(incomeField);
		this.add(consumeLabel);
		this.add(consumeField);
		//this.add(printOrgLabel);
		//this.add(printOrgField);
		this.add(processBtn);
		
		processBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == processBtn){
					String cardNo = cardNoField.getText().trim();
					String userName = nameField.getText().trim();
					String currentTime = currentTimeField.getText().trim();
					String beginAmount = beginAmountField.getText().trim();
					String income = incomeField.getText().trim();
					String consume = consumeField.getText().trim();
					String printOrg = printOrgField.getText().trim();
					
					ZhongHangHandler handler = new ZhongHangHandler();
					handler.setCardNo(cardNo);
					handler.setUserName(userName);
					handler.setStartDateStr(currentTime.split("#")[0]);
					handler.setEndDateStr(currentTime.split("#")[1]);
					handler.setBeginAmount(Float.valueOf(beginAmount));
					handler.setSumIncome(Float.valueOf(income));
					handler.setSumConsume(Float.valueOf(consume));
					
					
					List<ZhongHangDetail> details = new ArrayList<ZhongHangDetail>();
					details = handler.generate();
					ZhongHangExcel excel = new ZhongHangExcel();
					 Date startTime = new Date();//当前时间
				     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    
				     String startStr = sdf.format(startTime);
				     String[] dateStr = startStr.split("-");
				     System.out.println(startStr);
					excel.writeToExcel("d:\\中信银行"+userName+".xls", details,cardNo,userName,printOrg,currentTime);
					
				}
			}
		});
	}
}
