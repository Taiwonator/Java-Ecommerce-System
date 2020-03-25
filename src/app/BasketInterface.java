package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import app.InterfaceManager.State;
import user.control.Product;
import user.control.ProductClassFunctions;
import app.PaypalInterface;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class BasketInterface {
	
	private static JPanel container_panel;

	public static JPanel returnBasketPanel() {
		
		JPanel basket_panel = new JPanel();
		basket_panel.setLayout(null);
		basket_panel.setBackground(Color.WHITE);
				
		JLabel title = new JLabel("Shopping Basket");
		title.setFont(new Font("Tahoma", Font.PLAIN, 24));
		title.setBounds(97, 82, 194, 67);
		basket_panel.add(title);
		
		JScrollPane scroll_panel = new JScrollPane();
		scroll_panel.setBorder(null);
		scroll_panel.setBounds(96, 152, 667, 303);
		basket_panel.add(scroll_panel);
		
		container_panel = new JPanel();
		container_panel.setBorder(null);
		container_panel.setBackground(Color.WHITE);
		container_panel.setBounds(0, 0, 20, 10);
		scroll_panel.setViewportView(container_panel);
		container_panel.setLayout(new GridLayout(0, 1, 5, 5));
		
		List<Product> unique_products = InterfaceManager.basket.getProducts();
		for(int i = 0; i < unique_products.size(); i++) {
			container_panel.add(createBasketProduct(unique_products.get(i)));
		}
		
		JButton clear_all_button = new JButton("Clear all");
		clear_all_button.setBounds(97, 466, 147, 41);
		clear_all_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int dialog_result = JOptionPane.showConfirmDialog (null, "Are you sure you want to clear your basket?", "Confirm", JOptionPane.YES_NO_OPTION);
				 if(dialog_result == JOptionPane.YES_OPTION){
					 InterfaceManager.clearBasket();
				 }
			}
			
		});
		basket_panel.add(clear_all_button);
		
		JButton save_all_for_later_button = new JButton("Save all");
		save_all_for_later_button.setBounds(254, 466, 133, 41);
		save_all_for_later_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int dialog_result = JOptionPane.showConfirmDialog (null, "Are you sure you want to move all items to your saved for later list?", "Confirm", JOptionPane.YES_NO_OPTION);
				 if(dialog_result == JOptionPane.YES_OPTION){
					 InterfaceManager.moveAllToSFL(InterfaceManager.basket);
				 }
			}
			
		});
		basket_panel.add(save_all_for_later_button);
		
		JButton card_button = new JButton("Card payment");
		card_button.setBounds(811, 257, 118, 34);
		card_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 cardPayment();
			}
			
		});
		basket_panel.add(card_button);
		
		JButton paypal_button = new JButton("Paypal");
		paypal_button.setBounds(811, 302, 118, 34);
		paypal_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 paypalPayment();
			}
			
		});
		basket_panel.add(paypal_button);
		
		JLabel total_label = new JLabel("Total: \u00A3 " + String.format("%.2f", InterfaceManager.basket.getTotal()));
		total_label.setHorizontalAlignment(SwingConstants.CENTER);
		total_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		total_label.setBounds(793, 213, 147, 34);
		basket_panel.add(total_label);
		
		if(unique_products.size() == 0) {
			clear_all_button.setEnabled(false);
			save_all_for_later_button.setEnabled(false);
			paypal_button.setEnabled(false);
			card_button.setEnabled(false);
		}
		
		return basket_panel;
	}
	
	private static JPanel createBasketProduct(Product product) {
		JPanel basket_product_panel = new JPanel();
		basket_product_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		basket_product_panel.setBackground(Color.WHITE);
		container_panel.add(basket_product_panel);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{128, 93, 10, 5, 5, 60, 60, 0};
		gbl_panel_2.rowHeights = new int[]{35, 29, 30, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		basket_product_panel.setLayout(gbl_panel_2);
		
		JLabel image = new JLabel("");
		image.setBorder(new LineBorder(new Color(0, 0, 0)));
		image.setIcon(null);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 10);
		gbc_lblNewLabel_2.gridheight = 3;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		basket_product_panel.add(image, gbc_lblNewLabel_2);
		
		JLabel name = new JLabel(product.getName());
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 0;
		basket_product_panel.add(name, gbc_lblNewLabel_3);
		
		JLabel price = new JLabel("\u00A3 " + String.format("%.2f", product.getRetailPrice()));
		price.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 1;
		basket_product_panel.add(price, gbc_lblNewLabel_4);
		
		JButton open_button = new JButton("Open Product");
		open_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InterfaceManager.setState(State.PRODUCT);
				InterfaceManager.OpenProduct(product);
			}
			
		});
		GridBagConstraints open_button_c = new GridBagConstraints();
		open_button_c.anchor = GridBagConstraints.NORTH;
		open_button_c.fill = GridBagConstraints.HORIZONTAL;
		open_button_c.gridx = 1;
		open_button_c.gridy = 2;
		basket_product_panel.add(open_button, open_button_c);
		
		JButton increase_button = new JButton("+");
		increase_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InterfaceManager.increaseQuantity(product);
			}
			
		});
		GridBagConstraints increase_button_c = new GridBagConstraints();
		increase_button_c.anchor = GridBagConstraints.NORTH;
		increase_button_c.fill = GridBagConstraints.NONE;
		increase_button_c.gridx = 4;
		increase_button_c.gridy = 1;
		basket_product_panel.add(increase_button, increase_button_c);
		
		JButton decrease_button = new JButton("-");
		decrease_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InterfaceManager.decreaseQuantity(product);
			}
			
		});
		GridBagConstraints decrease_button_c = new GridBagConstraints();
		decrease_button_c.anchor = GridBagConstraints.NORTH;
		decrease_button_c.fill = GridBagConstraints.NONE;
		decrease_button_c.gridx = 3;
		decrease_button_c.gridy = 1;
		basket_product_panel.add(decrease_button, decrease_button_c);
		
		JLabel quantity = new JLabel("Quantity: " + Integer.toString(InterfaceManager.basket.getBasket().get(product)));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 1;
		basket_product_panel.add(quantity, gbc_lblNewLabel_5);
		
		JButton delete_button = new JButton("Delete");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int dialog_result = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this item?", "Confirm", JOptionPane.YES_NO_OPTION);
				 if(dialog_result == JOptionPane.YES_OPTION){
					 InterfaceManager.removeBasketProduct(product);
				 }
			}
		});
		GridBagConstraints gbc_btnNewButton_3_1 = new GridBagConstraints();
		gbc_btnNewButton_3_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton_3_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3_1.gridx = 5;
		gbc_btnNewButton_3_1.gridy = 2;
		basket_product_panel.add(delete_button, gbc_btnNewButton_3_1);
		
		JButton sfl_button = new JButton("Save for later");
		sfl_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int dialog_result = JOptionPane.showConfirmDialog (null, "Are you sure you want to move this item to saved for later?", "Confirm", JOptionPane.YES_NO_OPTION);
				 if(dialog_result == JOptionPane.YES_OPTION){
					 InterfaceManager.moveToSFL(product);
//					 InterfaceManager.setState(State.SFL);
				 }
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.gridx = 6;
		gbc_btnNewButton_3.gridy = 2;
		basket_product_panel.add(sfl_button, gbc_btnNewButton_3);
		
		return basket_product_panel;
	}
	
	public static void cardPayment() {
		if(checkStock()) {
			if(!(InterfaceManager.CardInterface == null)) {
				InterfaceManager.CardInterface.frame.dispose();
			}
			if(!(InterfaceManager.PaypalInterface == null)) {
				InterfaceManager.PaypalInterface.frame.dispose();
			}
			InterfaceManager.CardInterface = new CardInterface();
			InterfaceManager.CardInterface.frame.setVisible(true);
		}
	}
	
	public static void paypalPayment() {
		if(checkStock()) {
			if(!(InterfaceManager.PaypalInterface == null)) {
				InterfaceManager.PaypalInterface.frame.dispose();
			}
			if(!(InterfaceManager.CardInterface == null)) {
				InterfaceManager.CardInterface.frame.dispose();
			}
			InterfaceManager.PaypalInterface = new PaypalInterface();
			InterfaceManager.PaypalInterface.frame.setVisible(true);
		}
	}
	
	public static boolean checkStock() {
		boolean allow = true;
		List<Product> basket_products = InterfaceManager.basket.getProducts();
		for(int i = 0; i < basket_products.size(); i++) {
			Product basket_product = basket_products.get(i);
			if(InterfaceManager.basket.getBasket().get(basket_product) <= basket_product.getQuantityInStock()) {
				
			} else {
				JOptionPane.showMessageDialog(Home.home.home_panel, "You are attempting to buy above our current stock, we have reduced your item quantity for " + basket_product.getName() + " from " + InterfaceManager.basket.getBasket().get(basket_product) + " to " + basket_product.getQuantityInStock(), "Item quantity changed", JOptionPane.INFORMATION_MESSAGE);
				InterfaceManager.basket.changeProductAmount(basket_product, basket_product.getQuantityInStock());
				allow = false;
			}
		}
		if(!allow) {
			InterfaceManager.refreshBasket();
		}
		return allow;
	}
	
	
}
