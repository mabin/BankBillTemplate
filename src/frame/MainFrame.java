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
		this.setTitle("0320,增加中信银行");
		this.setSize(550, 400);
		this.setLocation(0, 0);
		this.setVisible(true);
		tabbedPanel = new JTabbedPane();
		JPanel dalian = new DaLianBankPanel();
		JPanel abc = new ABCBankPanel();
		JPanel gh = new GongHangPanel();
		JPanel zh = new ZhongHangPanel();
		JPanel zx = new ZhongXinPanel();
		tabbedPanel.add("大连银行", dalian);
		tabbedPanel.add("农业银行",abc);
		tabbedPanel.add("工商银行",gh);
		tabbedPanel.add("中国银行",zh);
		tabbedPanel.add("中信银行",zx);
		this.add(tabbedPanel);
		
		
	}
}
