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
import javax.swing.SwingConstants;

public class CardInterface {
	
	public JFrame frame;
	private JTextField card_number_input;
	private JLabel error_message;
	private JTextField security_code_input;
	
	public static void main(String[] args) {
		;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardInterface x = new CardInterface();
					x.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CardInterface() {
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
		title_1.setBounds(169, 24, 121, 60);
		title_1.setIcon(null);
		frame.getContentPane().add(title_1);
		
		JButton submit_button = new JButton("Submit");
		submit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				validatePayment(card_number_input.getText(), security_code_input.getText());
				
			}
			
		});
		
		submit_button.setBounds(281, 184, 104, 33);
		submit_button.setBackground(black);
		submit_button.setForeground(white);
		frame.getContentPane().add(submit_button);
		frame.getRootPane().setDefaultButton(submit_button);
		
		card_number_input = new JTextField();
		card_number_input.setBounds(142, 112, 263, 33);
		card_number_input.requestFocus();
		
		card_number_input.setBackground(grey);
		frame.getContentPane().add(card_number_input);
		card_number_input.setColumns(10);
		
		JLabel card_number_label = new JLabel("16-Digit Card Number");
		card_number_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		card_number_label.setBounds(144, 92, 121, 14);
		frame.getContentPane().add(card_number_label);
		frame.getContentPane().setBackground(white);
		
		JLabel title_2 = new JLabel("CARD");
		title_2.setFont(new Font("Arial", Font.BOLD, 32));
		title_2.setBounds(291, 24, 98, 60);
		frame.getContentPane().add(title_2);
		
		error_message = new JLabel("");
		error_message.setHorizontalAlignment(SwingConstants.CENTER);
		error_message.setForeground(Color.RED);
		error_message.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error_message.setBounds(120, 235, 309, 14);
		frame.getContentPane().add(error_message);
		
		security_code_input = new JTextField();
		security_code_input.setColumns(10);
		security_code_input.setBackground(new Color(226, 226, 226));
		security_code_input.setBounds(156, 184, 104, 33);
		frame.getContentPane().add(security_code_input);
		
		JLabel security_code_label = new JLabel("Security Code");
		security_code_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		security_code_label.setBounds(158, 164, 121, 14);
		frame.getContentPane().add(security_code_label);
	}
	
	private void validatePayment(String card_number, String security_code) {
		
		boolean allow = true;
	
		if(security_code.length() == 3 && security_code.matches("\\d+")) {
			
		} else {
			error_message.setText("Enter a valid card security number");
			allow = false;
		}
		
		if(card_number.length() == 16 && card_number.matches("\\d+")) {
			
		} else {
			error_message.setText("Enter a valid card number");
			allow = false;
		}
		
		
		if(allow) {
			error_message.setText("");
			InterfaceManager.carryOutPayment("Credit Card");
			frame.dispose();
		}
	}
}
