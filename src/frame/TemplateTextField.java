package frame;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;

public class TemplateTextField extends JTextField{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3184062489238392738L;

	public TemplateTextField(int columns){
		setText("");
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		setMinimumSize(new Dimension(120,30));
		setMaximumSize(new Dimension(400,30));
		setColumns(columns);
	}
}
