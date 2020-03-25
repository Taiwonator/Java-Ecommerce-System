package experiments;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;
import java.io.File;

public class LoginInterface extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LoginInterface();
	}
	
	public LoginInterface() {
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Login");
		
		//White: #FFFFFF
		//Grey: #E2E2E2
		//Dark Grey: #B2B2B2
		//Black: #303030
		
		JPanel panel = new JPanel();
		
		Color background_color = Color.decode("#FFFFFF");
		panel.setBackground(background_color);
		
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 50;
		c.weighty = 10;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		
		
		JLabel avatar = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/res/Avatar.png")).getImage();
		avatar.setIcon(new ImageIcon(img));
		
		
		JTextField username_field = new JTextField("");
		username_field.requestFocus();
		
		JButton submit = new JButton("Submit");
		
		panel.add(avatar, c);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
	    panel.add(username_field, c);
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		panel.add(submit, c);
	    
		this.add(panel);
		
		this.setVisible(true);

	}

}
