package experiments;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;

import experiments.NewFrame;

public class Grid extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Grid();

	}
	
	public Grid() {
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Grid Frame");
		this.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel inner_panel = new JPanel();
		inner_panel.setLayout(new BorderLayout());
		
		JButton button_1 = new JButton("Button 1");
		JButton button_2 = new JButton("Button 2");
		JButton button_3 = new JButton("Button 3");
		JButton button_4 = new JButton("Button 4");
		
		JButton button_5 = new JButton("Button 5");
		JButton button_6 = new JButton("Button 6");
		
		panel.add(button_1, BorderLayout.NORTH);
		panel.add(button_2, BorderLayout.EAST);
		panel.add(button_3, BorderLayout.SOUTH);
		panel.add(button_4, BorderLayout.WEST);
		
		inner_panel.add(button_5, BorderLayout.PAGE_START);
		inner_panel.add(button_6, BorderLayout.PAGE_END);
		
		panel.add(inner_panel, BorderLayout.CENTER);
		
		this.add(panel);
		
		
		
		
		
	}

}
