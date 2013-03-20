package frame;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TemplateLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3184062489238292738L;

	public TemplateLabel(String name){
		setText(name);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
	}
}
