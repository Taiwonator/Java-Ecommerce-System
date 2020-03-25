package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import app.InterfaceManager.State;
import user.control.LoginFunctions;
import user.control.Product;
import user.control.ProductClassFunctions;
import user.control.User;

public class ProductFunctions {

	
	public static JPanel createHomeProduct(Product product_object, String name_input, float price_input, int barcode) {
		JPanel product = new JPanel();
		product.setBorder(new LineBorder(Color.LIGHT_GRAY));
		GridBagLayout gbl_product = new GridBagLayout();
		gbl_product.columnWidths = new int[]{153, 0};
		gbl_product.rowHeights = new int[]{124, 0, 14, 0, 14, 0};
		gbl_product.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_product.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		product.setLayout(gbl_product);
		
		JLabel image = new JLabel("");
		image.setIcon(new ImageIcon(Home.class.getResource("/res/Avatar.png")));
		GridBagConstraints gbc_image = new GridBagConstraints();
		gbc_image.insets = new Insets(10, 20, 10, 20);
		gbc_image.gridx = 0;
		gbc_image.gridy = 0;
		product.add(image, gbc_image);
		
		JLabel name = new JLabel(name_input);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.insets = new Insets(10, 20, 10, 20);
		gbc_name.gridx = 0;
		gbc_name.gridy = 1;
		product.add(name, gbc_name);
		
		JLabel price = new JLabel("\u00A3" + String.format("%.2f", price_input));
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_price = new GridBagConstraints();
		gbc_price.insets = new Insets(0, 0, 5, 0);
		gbc_price.gridx = 0;
		gbc_price.gridy = 2;
		product.add(price, gbc_price);
		
		JButton button = new JButton("View Product");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.gridx = 0;
		gbc_button.gridy = 3;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				InterfaceManager.setState(State.PRODUCT);
				InterfaceManager.OpenProduct(product_object);
				
			}
			
		});
		product.add(button, gbc_button);
		
		return product;
	}
	
	private static void addToPanel(JPanel parent, List<Product> products) {
		for(int i = 0; i < products.size(); i++) {
			JPanel temp = createHomeProduct(products.get(i), capitalize(products.get(i).getName()), products.get(i).getRetailPrice(), products.get(i).getBarcode());
			parent.add(temp);
		}
	}
	
	public static void addProductsToPanel(JPanel parent) {
		List<Product> products = ProductClassFunctions.getProductInfo();
		addToPanel(parent, products);
	}
	
	public static void addProductsToPanel(JPanel parent, List<Product> products) {
		addToPanel(parent, products);
	}
	
	public static String capitalize(String input) {
		String output;
		
	    if(input.isEmpty() || input == null) {
	    	output = input;
	    } else {
	    	output = input.substring(0, 1).toUpperCase() + input.substring(1);
	    }
	    
	    return output;
	}
	
	public static String[] StringToArray(String input) {
		String[] output = input.split(" ");
		return output;
 	} 
	
	public static void main(String[] args) {
//		System.out.println(returnFilteredProducts2("wired black small red something"));
	}
	
	public static void addFilteredProductsToPanel(JPanel parent, String filter, List<String> main_filters) {
		List<Product> products = ProductFunctions.returnFilteredProducts2(filter, main_filters);
		addToPanel(parent, products);
	}
	
	public static List<Product> returnFilteredProducts(String filter) {
		List<Product> output = new ArrayList<Product>();
		List<Product> all_products = ProductClassFunctions.getProductInfo();
		List<String> filters = Arrays.asList(StringToArray(filter));
		
		for(int i = 0; i < filters.size(); i++) {
			for(int j = 0; j < all_products.size(); j++) {
				if(all_products.get(j).getAll().contains(filters.get(i))) {
					output.add(all_products.get(j));
				}
			}
		}
		return output;
	}	
	
	public static List<Product> returnFilteredProducts2(String filter, List<String> main_filters) {
		List<Product> output = new ArrayList<Product>();
		List<Product> all_products = ProductClassFunctions.getProductInfo();
		List<String> filters = Arrays.asList(StringToArray(filter));
		if(filter.isEmpty() || filters.size() == 0) {
			for(int j = 0; j < all_products.size(); j++) {
				
					boolean allow = true;
					for(int k = 0; k < main_filters.size(); k++) {
						if(!all_products.get(j).getAll().contains(main_filters.get(k))) {
							allow = false;
						}
					}
					if(allow) {
						System.out.println(main_filters);
						output.add(all_products.get(j));
					}
					
			}	
		} else {
			for(int i = filters.size(); i > 0; i--) {
				for(int j = 0; j < all_products.size(); j++) {
					// IFF the product has a j keywords AND the list doesn't contain the product
					if(checkForFilters(all_products.get(j), filters, i) && !output.contains(all_products.get(j))) {
						
						boolean allow = true;
						for(int k = 0; k < main_filters.size(); k++) {
							if(!all_products.get(j).getAll().contains(main_filters.get(k))) {
								allow = false;
							}
						}
						if(allow) {
							output.add(all_products.get(j));
						}
						
					}
				}
			}
		}
		return output;
	}
	
	public static boolean checkForFilters(Product product, List<String> filters, int pass_count) {
		List<Product> all_products = ProductClassFunctions.getProductInfo();
		int count = 0;
		
		for(int i = 0; i < filters.size(); i++) {
			if(product.getAll().contains(filters.get(i))) {
				count++;
			}
		}
		
		if(count == pass_count) {
			return true;
//			System.out.println(count + " " + pass_count);
		} else {
			return false;
//			System.out.println(count + " " + pass_count);
		}
	}
	
	// Check for if all parts of filter are contained in product, After every part, counter is incremeted and at the end, if the in counter == pass count (inital: filter length, end: 1), then add product to list IFF not already in list
	// pass count decremented every time so must only pass one of the tests at the very end
	
	// Filter based on all attributes (drop down menu)
	// Should accept each word as unique by running a search on each word, and listing the products in order of occurrences in array of product search results
	
	// String split into array.
	// Each item in array generates search results

}
