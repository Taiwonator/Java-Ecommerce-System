package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import user.control.Product;
import user.control.ProductClassFunctions;

import java.awt.Color;
import javax.swing.JButton;

public class AddStockInterface {

	private JFrame frame;
	
	private static JPanel panel;
	
	private static JComboBox<String> device_name_input;
	private static JComboBox<String> device_type_input;
	private static JComboBox<String> color_input;
	private static JComboBox<String> connectivity_input;
	private static JComboBox variable_input;
	
	private static JTextField brand_input;
	private static JTextField current_stock_input;
	private static JTextField original_price_input;
	private static JTextField retail_price_input;
	
	private static JLabel error_message;

	
	public static JPanel returnAddProductPanel() {		
		panel = new JPanel(null);
		panel.setBackground(InterfaceManager.white);
		
		JLabel title = new JLabel("Add Item");
		title.setFont(new Font("Tahoma", Font.PLAIN, 32));
		title.setBounds(197, 88, 192, 61);
		panel.add(title);
		
		JLabel device_name_label = new JLabel("Device Name");
		device_name_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		device_name_label.setBounds(197, 152, 107, 26);
		panel.add(device_name_label);
		
		String[] device_name_options = {"mouse", "keyboard"};
		device_name_input = new JComboBox<String>(device_name_options);
		device_name_input.setBounds(292, 154, 131, 26);
		device_name_input.setBackground(InterfaceManager.grey);
		panel.add(device_name_input);
		
		JLabel device_type_label = new JLabel("Device Type");
		device_type_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		device_type_label.setBounds(197, 203, 107, 26);
		panel.add(device_type_label);
		
		String[] device_type_options = {"gaming", "standard", "internet", "flexible"};
		device_type_input = new JComboBox<String>(device_type_options);
		device_type_input.setBounds(292, 205, 131, 26);
		device_type_input.setBackground(InterfaceManager.grey);
		panel.add(device_type_input);
		
		JLabel brand_label = new JLabel("Brand");
		brand_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		brand_label.setBounds(197, 254, 107, 24);
		panel.add(brand_label);
		
		brand_input = new JTextField();
		brand_input.setBounds(292, 254, 131, 26);
		brand_input.setBackground(InterfaceManager.grey);
		panel.add(brand_input);
		brand_input.setColumns(10);
		
		JLabel color_label = new JLabel("Color");
		color_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		color_label.setBounds(197, 302, 107, 26);
		panel.add(color_label);
		
		String[] color_options = {"black", "white", "grey", "yellow", "blue", "red", "green", "brown", "pink", "orange"};
		color_input = new JComboBox<String>(color_options);
		color_input.setBounds(292, 304, 131, 26);
		color_input.setBackground(InterfaceManager.grey);
		panel.add(color_input);
		
		JLabel connectivity_label = new JLabel("Connectivity");
		connectivity_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		connectivity_label.setBounds(197, 351, 107, 26);
		panel.add(connectivity_label);
		
		String[] connectivity_options = {"wired", "wireless"};
		connectivity_input = new JComboBox<String>(connectivity_options);
		connectivity_input.setBounds(292, 353, 131, 26);
		connectivity_input.setBackground(InterfaceManager.grey);
		panel.add(connectivity_input);
		
		JLabel current_stock_label = new JLabel("Current Stock");
		current_stock_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		current_stock_label.setBounds(197, 403, 107, 24);
		panel.add(current_stock_label);
		
		current_stock_input = new JTextField();
		current_stock_input.setColumns(10);
		current_stock_input.setBounds(292, 403, 131, 26);
		current_stock_input.setBackground(InterfaceManager.grey);
		panel.add(current_stock_input);
		
		JLabel original_price_label = new JLabel("Original Price");
		original_price_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		original_price_label.setBounds(512, 152, 107, 24);
		panel.add(original_price_label);
		
		original_price_input = new JTextField();
		original_price_input.setColumns(10);
		original_price_input.setBounds(607, 152, 131, 26);
		original_price_input.setBackground(InterfaceManager.grey);
		panel.add(original_price_input);
		
		JLabel retail_price_label = new JLabel("Retail Price");
		retail_price_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		retail_price_label.setBounds(512, 203, 107, 24);
		panel.add(retail_price_label);
		
		retail_price_input = new JTextField();
		retail_price_input.setColumns(10);
		retail_price_input.setBounds(607, 203, 131, 26);
		retail_price_input.setBackground(InterfaceManager.grey);
		panel.add(retail_price_input);
		
		JLabel variable_label = new JLabel("Variable label");
		variable_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		variable_label.setBounds(512, 250, 107, 26);
		panel.add(variable_label);
		
		variable_input = new JComboBox();
		variable_input.setBounds(607, 252, 131, 26);
		variable_input.setBackground(InterfaceManager.grey);
		panel.add(variable_input);
		
		String value = (String)device_name_input.getSelectedItem();
    	variable_input.removeAllItems();
		if(value.equals("keyboard")) {
			variable_label.setText("Layout");
			variable_input.addItem("UK");
			variable_input.addItem("US");
		} else if(value.equals("mouse")) {
			variable_label.setText("No. Buttons");
			for(int i = 1; i <= 20; i++) {
				variable_input.addItem(new Integer(i));
			}
		}
		
		JButton submit_button = new JButton("SUBMIT");
		submit_button.setForeground(Color.WHITE);
		submit_button.setBackground(InterfaceManager.black);
		submit_button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		submit_button.setBounds(511, 382, 131, 45);
		submit_button.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	addToStock();
		    }
		});
		panel.add(submit_button);
		
		error_message = new JLabel("");
		error_message.setBackground(Color.WHITE);
		error_message.setForeground(Color.RED);
		error_message.setFont(new Font("Arial", Font.BOLD, 14));
		error_message.setBounds(658, 403, 258, 24);
		panel.add(error_message);
		
		device_name_input.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	String value = (String)device_name_input.getSelectedItem();
		    	variable_input.removeAllItems();
				if(value.equals("keyboard")) {
					variable_label.setText("Layout");
					variable_input.addItem("UK");
					variable_input.addItem("US");
				} else if(value.equals("mouse")) {
					variable_label.setText("No. Buttons");
					for(int i = 1; i <= 20; i++) {
						variable_input.addItem(new Integer(i));
					}
				}
		    }
		});
		
		return panel;
	}
	
	private static void addToStock() {
		
		if(verifyBrand(brand_input.getText()) && verifyStock(current_stock_input.getText()) && verifyPrice(original_price_input.getText()) && verifyPrice(retail_price_input.getText())) {
			
			Product product = createStockItem();
			List<Product> all_products = ProductClassFunctions.getProductInfo();
			boolean unique = true;
			for(int i = 0; i < all_products.size(); i++) {
				if(product.isIdenticalTo(all_products.get(i))) {
					unique = false;
				}
			}
			
			if(unique) {
				error_message.setText("");
				int dialog_result = JOptionPane.showConfirmDialog (null, "Are you sure you want to add " + product.getName() + " to the product stock?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(dialog_result == JOptionPane.YES_OPTION){
					JOptionPane.showMessageDialog(panel, product.getName() + " successfully added to product stock", "Product Stock", JOptionPane.INFORMATION_MESSAGE);
					ProductClassFunctions.addProduct(product);
				}
			} else {
				error_message.setText("Product already exists in stock");
			}

			
		}

	}
	
	private static Product createStockItem() {
		Product product = new Product();
		
		product.setDeviceName((String)device_name_input.getSelectedItem());
		product.setDeviceType((String)device_type_input.getSelectedItem());
		product.setBrand(brand_input.getText().toLowerCase());
		product.setColor((String)color_input.getSelectedItem());
		product.setConnectivity((String)connectivity_input.getSelectedItem());
		product.setQuantityInStock(Integer.parseInt(current_stock_input.getText().toLowerCase()));
		product.setOriginalPrice(Float.parseFloat(original_price_input.getText().toLowerCase()));
		product.setRetailPrice(Float.parseFloat(retail_price_input.getText().toLowerCase()));
		
		if(product.getDeviceName().equals("keyboard")) {
			product.setLayout((String)variable_input.getSelectedItem());
		} else {
			product.setButtonCount((Integer)variable_input.getSelectedItem());
		}

		return product; 
	}
	
	private static boolean verifyBrand(String brand) {
		boolean out = false;
		
		if(!brand.isEmpty() && brand.trim().length() != 0) {
			out = true;
		} else {
			error_message.setText("Brand: Not filled in");
		}
		
		return out;
	} 
	
	private static boolean verifyStock(String stock) {
		boolean out = false;
		if(stock.matches("\\d+")) {
			out = true;
		} else {
			error_message.setText("Stock: Must be an integer");
		}
	
		return out;
	}
	
	private static boolean verifyPrice(String price) {
		boolean out = false;
		
		if(!price.isEmpty() && price.trim().length() != 0) {
			try {
		        float float_price = Float.parseFloat(price);
		        if(float_price >= 0) {
		        	if((float_price*100) == Math.ceil(float_price*100)) {
				        out = true;
		        	} else {
		    			error_message.setText("Currency: Must be 2 decimal places");
		        	}
		        } else {
					error_message.setText("Currency: Must be non negative");
		        }
		    } catch (NumberFormatException e){
				error_message.setText("Currency: Must be a number");
		    }
		} else {
			error_message.setText("Currency: Not filled in");
		}
		
		return out;
	}
}
