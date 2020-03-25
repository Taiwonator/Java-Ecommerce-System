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
import java.awt.event.ActionEvent;
import java.awt.Color;

import user.control.LoginFunctions;
import user.control.User;
import app.InterfaceManager;

public class Login {
	
	public JFrame frame;
	private JTextField username_field;
	private JLabel error_message;
	
	

	public Login() {
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
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int x = (dim.width / 2) - (frame.getWidth() / 2);
		int y = (dim.height / 2) - (frame.getHeight() / 2);
		frame.setLocation(x, y);
		
		JLabel avatar = new JLabel("");
		avatar.setBounds(231, 32, 124, 124);
		avatar.setIcon(new ImageIcon(Login.class.getResource("/res/Avatar.png")));
		frame.getContentPane().add(avatar);
		
		JButton login_button = new JButton("LOG IN");
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//LOGIN BUTTON
				
				User user = LoginFunctions.ValidateUsername(username_field.getText());
				if(user != null) {
					frame.dispose();
					error_message.setText("");
					InterfaceManager.setUser(user);
					InterfaceManager.setState(InterfaceManager.State.HOME);
				} else {
					error_message.setText("Username is incorrect.");
				}
			}
			
		});
		
		login_button.setBounds(241, 269, 104, 33);
		login_button.setBackground(black);
		login_button.setForeground(white);
		frame.getContentPane().add(login_button);
		frame.getRootPane().setDefaultButton(login_button);
		
		username_field = new JTextField();
		username_field.setBounds(163, 202, 263, 33);
		
		username_field.setBackground(grey);
		frame.getContentPane().add(username_field);
		username_field.setColumns(10);
		
		JLabel username_label = new JLabel("Username");
		username_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		username_label.setBounds(165, 182, 69, 14);
		frame.getContentPane().add(username_label);
		
		error_message = new JLabel("");
		error_message.setForeground(Color.RED);
		error_message.setBounds(163, 242, 182, 14);
		frame.getContentPane().add(error_message);
		frame.getContentPane().setBackground(white);
	}
}
