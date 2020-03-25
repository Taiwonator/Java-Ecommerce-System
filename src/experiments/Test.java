package experiments;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.GridBagConstraints;

public class Test {

	private JFrame frame;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 651, 429);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int x = (dim.width / 2) - (frame.getWidth() / 2);
		int y = (dim.height / 2) - (frame.getHeight() / 2);
		frame.setLocation(x, y);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(0, 74, 635, 99);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText("Button clicked");
;			}
		});
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Choose User Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(198, 22, 241, 46);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(btnNewButton);
	}
}
