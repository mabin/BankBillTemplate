package frame;


import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;


public class MainMethod {
	public static void main(String[] args) {

		try {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					MainFrame frame = new MainFrame();
					frame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setPreferredSize(frame.getSize());
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}