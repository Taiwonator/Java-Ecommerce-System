package app;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.InterfaceManager.State;
import user.control.Product;
import app.Home;

public class ProductInterface {
	
	public static JLabel product_stock;

	public static JPanel viewProduct(Product product) {
		
		String name = product.getName();
		int barcode = product.getBarcode();
		int stock = product.getQuantityInStock();
		String color = product.getColor();
		float price = product.getRetailPrice();
		List<String> tags = product.getAll();

		
		JPanel product_panel = new JPanel();
		product_panel.setBounds(311, 228, 10, 10);
		product_panel.setLayout(null);
		product_panel.setBackground(Color.WHITE);
		
		JLabel product_image = new JLabel("");
		product_image.setIcon(new ImageIcon(Home.class.getResource("/res/ProductImage.png")));
		product_image.setBounds(121, 109, 425, 398);
		product_panel.add(product_image);
		
		JLabel product_name = new JLabel(name);
		product_name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		product_name.setBounds(556, 183, 401, 59);
		product_panel.add(product_name);
		
		JLabel product_barcode = new JLabel(Integer.toString(barcode));
		product_barcode.setForeground(Color.GRAY);
		product_barcode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		product_barcode.setBounds(556, 244, 100, 22);
		product_panel.add(product_barcode);
		
		product_stock = new JLabel(Integer.toString(stock) + " in stock");
		product_stock.setFont(new Font("Tahoma", Font.BOLD, 12));
		product_stock.setHorizontalAlignment(SwingConstants.RIGHT);
		product_stock.setBounds(806, 245, 97, 20);
		product_panel.add(product_stock);
		
		JLabel product_color = new JLabel(color);
		product_color.setFont(new Font("Tahoma", Font.BOLD, 12));
		product_color.setBounds(556, 269, 102, 22);
		product_panel.add(product_color);
		JLabel product_price = new JLabel("\u00A3 " + String.format("%.2f", price));
		product_price.setFont(new Font("Tahoma", Font.BOLD, 30));
		product_price.setBounds(556, 292, 252, 50);
		product_panel.add(product_price);
				
		JPanel product_tags_panel = new JPanel();
		product_tags_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		product_tags_panel.setBounds(557, 345, 343, 52);
		product_tags_panel.setLayout(new GridLayout(0, 4, 4, 4));
		
		for(int i = 1; i < tags.size(); i++) {
			JButton button = new JButton(tags.get(i));
			button.setFont(new Font("Arial", Font.PLAIN, 10));
			button.setBackground(InterfaceManager.black);
			button.setForeground(InterfaceManager.white);
			final Integer innerI = new Integer(i);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					InterfaceManager.setState(State.BROWSING);
					if(InterfaceManager.user.getUserType().equals("customer")) {
						Home.home.searchFunction(tags.get(innerI));
					} else {
						AdminHome.admin_home.searchFunction(tags.get(innerI));
					}
				}
				
			});
			product_tags_panel.add(button);
		}
		
		product_panel.add(product_tags_panel);
		
		JLabel product_quantity = new JLabel("Quantity: ");
		product_quantity.setBounds(556, 420, 90, 20);
		product_panel.add(product_quantity);
		
		JComboBox<Integer> combo_box = new JComboBox<Integer>();
		if(InterfaceManager.user.getUserType().equals("customer")) {
			for(int i = 1; i <= 30; i++) {
				combo_box.addItem(new Integer(i));
			}
		} else {
			int i = 1;
			while(i <= 1000) {
				if(i >= 1 && i < 20) {
					combo_box.addItem(new Integer(i));
					i++;
				} else if (i >= 20 && i < 100) {
					combo_box.addItem(new Integer(i));
					i += 5;
				} else if (i >= 100 && i < 200) {
					combo_box.addItem(new Integer(i));
					i += 10;
				} else if (i >= 200 && i < 500) {
					combo_box.addItem(new Integer(i));
					i += 50;
				} else if (i >= 500 && i <= 1000) {
					combo_box.addItem(new Integer(i));
					i += 100;
				} 
			}
		}
		combo_box.setBounds(616, 420, 75, 25);
		combo_box.setBackground(InterfaceManager.grey);
		product_panel.add(combo_box);
		
		int dialog_button = JOptionPane.YES_NO_OPTION;

		if(InterfaceManager.user.getUserType().equals("customer")) {
			JButton sfl_button = new JButton("SAVE FOR LATER");
			sfl_button.setBackground(InterfaceManager.grey);
			sfl_button.setForeground(Color.DARK_GRAY);
			sfl_button.setBounds(556, 457, 137, 44);
			sfl_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int quantity = (int) combo_box.getSelectedItem();
					if(quantity <= product.getQuantityInStock()) {
						String message = ("Are you want to add " + Integer.toString(quantity) + " " + product.getName() + " to your saved for later list?");
						int dialog_result = JOptionPane.showConfirmDialog (null, message, "Confirm" , dialog_button);
						if(dialog_result == JOptionPane.YES_OPTION){
							InterfaceManager.sfl_list.addProduct(product, quantity);
							JOptionPane.showMessageDialog(product_panel, Integer.toString(quantity) + " " + product.getName() + " added to save for later list", "Save for later", JOptionPane.INFORMATION_MESSAGE);
						}
						System.out.println(InterfaceManager.basket.getBasket());
					} else {
						String message = ("There are only " + Integer.toString(product.getQuantityInStock()) + " of " + product.getName() + " left in stock. Would you like to add the remaining " + Integer.toString(product.getQuantityInStock()) +  " to your save for later list?");
						int dialog_result = JOptionPane.showConfirmDialog (null, message, "Warning" , dialog_button);
						if(dialog_result == JOptionPane.YES_OPTION){
							InterfaceManager.sfl_list.addProduct(product, product.getQuantityInStock());
							JOptionPane.showMessageDialog(product_panel, Integer.toString(product.getQuantityInStock()) + " " + product.getName() + " added to save for later list", "Save for later", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			product_panel.add(sfl_button);
			//		JOptionPane.showMessageDialog(Home.home.frame, Integer.toString(quantity) + " " + product.getName() + " added to shopping basket", "Shopping basket", JOptionPane.INFORMATION_MESSAGE);

			JButton atb_button = new JButton("ADD TO BASKET");
			atb_button.setBounds(708, 457, 127, 44);
			atb_button.setBackground(InterfaceManager.black);
			atb_button.setForeground(InterfaceManager.white);
			atb_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int quantity = (int) combo_box.getSelectedItem();
					if(quantity <= product.getQuantityInStock()) {
						String message = ("Are you want to add " + Integer.toString(quantity) + " " + product.getName() + " to your shopping basket?");
						int dialog_result = JOptionPane.showConfirmDialog (null, message, "Confirm" , dialog_button);
						if(dialog_result == JOptionPane.YES_OPTION){
							InterfaceManager.addToBasket(product, quantity);
						}
						System.out.println(InterfaceManager.basket.getBasket());
					} else {
						String message = ("There are only " + Integer.toString(product.getQuantityInStock()) + " of " + product.getName() + " left in stock. Would you like to order the remaining " + Integer.toString(product.getQuantityInStock()) +  "?");
						int dialog_result = JOptionPane.showConfirmDialog (null, message, "Warning" , dialog_button);
						if(dialog_result == JOptionPane.YES_OPTION){
							InterfaceManager.addToBasket(product, product.getQuantityInStock());
						}
					}
				}
			});
			product_panel.add(atb_button);
		} else {
			JButton add_stock_button = new JButton("ADD STOCK");
			add_stock_button.setBackground(InterfaceManager.black);
			add_stock_button.setForeground(InterfaceManager.white);
			add_stock_button.setBounds(556, 457, 137, 44);
			add_stock_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int quantity = (int) combo_box.getSelectedItem();
					String message = ("Are you want to add " + Integer.toString(quantity) + " " + product.getName() + " to the stock?");
					int dialog_result = JOptionPane.showConfirmDialog (null, message, "Confirm" , dialog_button);
					if(dialog_result == JOptionPane.YES_OPTION){
						InterfaceManager.addToStock(product, quantity);
					}
				}
			});
			product_panel.add(add_stock_button);
		}
		
		return product_panel;
	}
	
	public static String[] StringToArray(String input) {
		String[] output = input.split(" ");
		return output;
 	} 
	
	public void foo() {
		
	}

}
