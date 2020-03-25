package app;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;

import user.control.LoginFunctions;
import user.control.User;
import app.InterfaceManager;

public class PaypalInterface {
	
	public JFrame frame;
	private JTextField email_input;
	private JLabel error_message;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaypalInterface x = new PaypalInterface();
					x.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PaypalInterface() {
		initialize();
	}
	
	//White: #FFFFFF
	//Grey: #E2E2E2
	//Dark Grey: #B2B2B2
	//Black: #303030	

	private void initialize() {
		Color white = Color.decode("#FFFFFF");
		Color grey = Color.decode("#E2E2E2");
		Color dark_grey = Color.decode("#B2B2B2");
		Color black = Color.decode("#303030");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 300);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int x = (dim.width / 2) - (frame.getWidth() / 2);
		int y = (dim.height / 2) - (frame.getHeight() / 2);
		frame.setLocation(x, y);
		
		JLabel title_1 = new JLabel("PAY BY");
		title_1.setFont(new Font("Arial", Font.PLAIN, 32));
		title_1.setBounds(168, 48, 121, 60);
		title_1.setIcon(null);
		frame.getContentPane().add(title_1);
		
		JButton submit_button = new JButton("Submit");
		submit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				validatePayment(email_input.getText());
				
			}
			
		});
		
		submit_button.setBounds(372, 156, 104, 33);
		submit_button.setBackground(black);
		submit_button.setForeground(white);
		frame.getContentPane().add(submit_button);
		frame.getRootPane().setDefaultButton(submit_button);
		
		email_input = new JTextField();
		email_input.setBounds(100, 156, 263, 33);
		email_input.requestFocus();
		
		email_input.setBackground(grey);
		frame.getContentPane().add(email_input);
		email_input.setColumns(10);
		
		JLabel email_label = new JLabel("Email");
		email_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		email_label.setBounds(102, 136, 69, 14);
		frame.getContentPane().add(email_label);
		frame.getContentPane().setBackground(white);
		
		JLabel title_2 = new JLabel("PAYPAL");
		title_2.setFont(new Font("Arial", Font.BOLD, 32));
		title_2.setBounds(296, 50, 137, 60);
		frame.getContentPane().add(title_2);
		
		error_message = new JLabel("");
		error_message.setForeground(Color.RED);
		error_message.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error_message.setBounds(102, 197, 170, 14);
		frame.getContentPane().add(error_message);
	}
	
	private void validatePayment(String email) {
		//-------------------------------------
		String email_regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                			"[a-zA-Z0-9_+&*-]+)*@" + 
                			"(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                			"A-Z]{2,7}$"; 
                  
		Pattern pat = Pattern.compile(email_regex); 
		if (email != null) {
			if(pat.matcher(email).matches()) {
				error_message.setText("");
				InterfaceManager.carryOutPayment("PayPal");
				frame.dispose();
			} else {
				error_message.setText("Enter a valid email address");
			}
		} 
		//-------------------------------------
	}
	
	//   Title: Check if email address valid or not in Java
	//   Author: GeeksforGeeks
	//   Date: Unknown
	//   Code version: Unknown
	//   Availability: https://www.geeksforgeeks.org/check-email-address-valid-not-java/
}
