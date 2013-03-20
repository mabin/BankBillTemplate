package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5988513125942516733L;
	
	private JTabbedPane tabbedPanel;
	
	public MainFrame(){
		this.setTitle("0314,修改农行日期和工行ATM取款");
		this.setSize(550, 400);
		this.setLocation(0, 0);
		this.setVisible(true);
		tabbedPanel = new JTabbedPane();
		JPanel dalian = new DaLianBankPanel();
		JPanel abc = new ABCBankPanel();
		JPanel gh = new GongHangPanel();
		JPanel zh = new ZhongHangPanel();
		tabbedPanel.add("大连银行", dalian);
		tabbedPanel.add("农业银行",abc);
		tabbedPanel.add("工商银行",gh);
		tabbedPanel.add("中国银行",zh);
		this.add(tabbedPanel);
		
		
	}
}
