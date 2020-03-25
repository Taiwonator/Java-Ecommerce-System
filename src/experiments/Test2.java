package experiments;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class Test2 extends JFrame {
	
	JButton button_1;
	JTextField text_field_1;
	JTextArea text_area_1;
	int button_clicked;

	public static void main(String[] args) {

		new Test2();

	}
	
	public Test2() {
		this.setSize(400,400);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		int x = (dim.width / 2) - (this.getWidth() / 2);
		int y = (dim.height / 2) - (this.getHeight() / 2);
		
		this.setLocation(x, y);
		
		this.setResizable(false);
		this.setTitle("Test 2");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		button_1 = new JButton("Send");
		text_field_1 = new JTextField("Type here...", 15);
		text_area_1 = new JTextArea(15, 20);
		text_area_1.setText("Dummy text area text\n");
		text_area_1.setLineWrap(true);
		text_area_1.setWrapStyleWord(true);
		int num_of_lines = text_area_1.getLineCount();
		text_area_1.append("number of lines: " + num_of_lines);
		JScrollPane scrollbar_1 = new JScrollPane(text_area_1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		button_1 = new JButton("Click here");
		ListenForButton listen_for_button = new ListenForButton();
		
	    button_1.addActionListener(listen_for_button);
	    
	    panel.add(button_1);
	    this.add(text_field_1);
//	    panel.add(text_area_1);
//		panel.add(scrollbar_1);
		
		this.add(panel);
		
		this.setVisible(true);
		
		text_field_1.requestFocus();
	}
	
	
	
	private class ListenForButton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button_1) {
				button_clicked++;
				text_area_1.append("\nButton click times: " + button_clicked);
			}
		}
		
	}
	
	
	

}
