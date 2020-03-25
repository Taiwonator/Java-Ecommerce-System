package app;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import app.AddStockInterface;
import user.control.ProductClassFunctions;

public class HomeFunctions {

	public JFrame frame;
	public JPanel navbar;
	public JPanel container_panel;
	public JPanel home_panel;
	public JPanel products_panel;
	public JPanel basket_panel;
	public JPanel sfl_panel;
	public JPanel add_product_panel;
	
	public JComboBox<String> combo_box;
	public JTextField search_bar;
	public List<String> main_filters;

	public HomeFunctions(String user_type) {
		initialize(user_type);
	}
	
	private void initialize(String user_type) {
		
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 1033, 615);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		this.frame.getContentPane().setBackground(InterfaceManager.white);
		this.frame.setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int x = (dim.width / 2) - (frame.getWidth() / 2);
		int y = (dim.height / 2) - (frame.getHeight() / 2);
		this.frame.setLocation(x, y);
		
		if(user_type.equals("customer")) {
			this.navbar = InterfaceManager.InitNavbar();
			this.frame.getContentPane().add(this.navbar);
		} else {
			this.navbar = InterfaceManager.InitAdminNavbar();
			this.frame.getContentPane().add(this.navbar);
		}
		
		this.container_panel = new JPanel();
		this.container_panel.setBackground(Color.WHITE);
		this.container_panel.setBounds(0, 0, 1027, 586);
		this.frame.getContentPane().add(container_panel);
		this.container_panel.setLayout(new CardLayout(0, 0));
		
		this.home_panel = new JPanel();
		this.home_panel.setBackground(Color.WHITE);
		this.container_panel.add(home_panel, "name_9704827776300");
		this.home_panel.setLayout(null);
		
		JPanel side_panel = new JPanel();
		side_panel.setBounds(0, 0, 160, 586);
		side_panel.setBackground(InterfaceManager.black);
		JLabel title = new JLabel("FILTER");
		title.setFont(new Font("Arial", Font.BOLD, 24));
		title.setForeground(Color.WHITE);
		title.setBounds(7, 100, 143, 32);
		side_panel.add(title);
		
		JLabel brand_label = new JLabel("Brand");
		brand_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		brand_label.setForeground(Color.WHITE);
		brand_label.setBounds(9, 152, 63, 16);
		side_panel.add(brand_label);
		
		JScrollPane brands_scroll_panel = new JScrollPane();
		brands_scroll_panel.setBounds(10, 180, 143, 199);
		side_panel.add(brands_scroll_panel);
		
		JPanel brands_panel = new JPanel();
		brands_panel.setBounds(0, 0, 10, 10);
		brands_scroll_panel.setViewportView(brands_panel);
		brands_panel.setLayout(new GridLayout(0, 1, 0, 0));
		brands_panel.setBackground(InterfaceManager.black);
		
		main_filters = new ArrayList<>();
		List<String> brands = ProductClassFunctions.getAllBrands();
		List<JCheckBox> brand_checkboxes = new ArrayList<>();
		for(int i = 0; i < brands.size(); i++) {
			JCheckBox checkbox = new JCheckBox(brands.get(i));
			checkbox.setBackground(InterfaceManager.black);
			checkbox.setForeground(Color.WHITE);
			final Integer innerI = new Integer(i);
			checkbox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(checkbox.isSelected()) {
						clearBrandsFromList(brand_checkboxes, checkbox, main_filters, brands.get(innerI));
					} else {
						main_filters.remove(brands.get(innerI));
					}
					submitFunction();
				}
			});
			brands_panel.add(checkbox);
			brand_checkboxes.add(checkbox);
		}
		
		JScrollPane layout_scroll_panel = new JScrollPane();
		layout_scroll_panel.setBounds(10, 450, 143, 88);
		side_panel.add(layout_scroll_panel);
		
		JPanel layout_panel = new JPanel();
		layout_panel.setBounds(0, 0, 141, 197);
		layout_scroll_panel.setViewportView(layout_panel);
		layout_panel.setLayout(new GridLayout(0, 1, 0, 0));
		layout_panel.setBackground(InterfaceManager.black);
		
		JCheckBox UK_checkbox = new JCheckBox("UK");
		JCheckBox US_checkbox = new JCheckBox("US");
		UK_checkbox.setBackground(InterfaceManager.black);
		UK_checkbox.setForeground(Color.WHITE);
		UK_checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(UK_checkbox.isSelected()) {
					main_filters.add("UK");
					main_filters.remove("US");
					US_checkbox.setSelected(false);
				} else {
					main_filters.remove("UK");
				}
				submitFunction();
			}
		});
		layout_panel.add(UK_checkbox);
		
		US_checkbox.setBackground(InterfaceManager.black);
		US_checkbox.setForeground(Color.WHITE);
		US_checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(UK_checkbox.isSelected()) {
					main_filters.add("US");
					main_filters.remove("UK");
					UK_checkbox.setSelected(false);
				} else {
					main_filters.remove("US");
				}
				submitFunction();
			}
		});
		layout_panel.add(US_checkbox);
		
		JLabel layout_label = new JLabel("Layout");
		layout_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		layout_label.setForeground(Color.WHITE);
		layout_label.setBounds(10, 422, 74, 20);
		side_panel.add(layout_label
				);
		
		this.home_panel.add(side_panel);
		side_panel.setLayout(null);
		
		if(user_type.equals("customer")) {
			
			this.basket_panel = BasketInterface.returnBasketPanel();
			this.container_panel.add(basket_panel, "name_9704822002700");
			
			this.sfl_panel = SFLInterface.returnSFLPanel();
			this.container_panel.add(sfl_panel);
			
		} else if(user_type.equals("admin")) {
			
			this.add_product_panel = AddStockInterface.returnAddProductPanel();
		}
		
		JScrollPane scroll_panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_panel.setBackground(Color.WHITE);
		scroll_panel.setBounds(160, 66, 867, 520);
		this.home_panel.add(scroll_panel);
		
		this.products_panel = new JPanel();
		this.products_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.products_panel.setBackground(Color.WHITE);
		this.products_panel.setAutoscrolls(true);
		this.products_panel.setBounds(0, 0, 164, 522);
		scroll_panel.setViewportView(products_panel);
		this.products_panel.setLayout(new GridLayout(0, 3, 5, 5));
		
		ProductFunctions.addProductsToPanel(this.products_panel);
		
		JPanel header_panel = new JPanel();
		header_panel.setBackground(Color.WHITE);
		scroll_panel.setColumnHeaderView(header_panel);
		header_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		String[] combo_box_array = {"All", "mouse", "keyboard"};
		combo_box = new JComboBox<String>(combo_box_array);
		combo_box.setBackground(InterfaceManager.grey);
		header_panel.add(combo_box);
		
		search_bar = new JTextField();
		header_panel.add(search_bar);
		search_bar.setBackground(InterfaceManager.grey);
		search_bar.setColumns(30);
		
		JButton submit_button = new JButton("Search");
		submit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submitFunction();
			}
		});
		
		combo_box.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	submitFunction();
		    }
		});
		
		this.frame.getRootPane().setDefaultButton(submit_button);
		header_panel.add(submit_button);
				
	}
	
	public void searchFunction(String tag) {
		this.products_panel.removeAll();
		List<String> temp = new ArrayList<>();
		ProductFunctions.addFilteredProductsToPanel(this.products_panel, tag, temp);
		this.products_panel.repaint();
		this.products_panel.revalidate();
	}
	
	public void submitFunction() {
		products_panel.removeAll();
		String value = (String)combo_box.getSelectedItem();
		if(value != "All") {
			main_filters.add(value);
		}
		ProductFunctions.addFilteredProductsToPanel(products_panel, search_bar.getText(), main_filters);
		if(value != "All") {
			main_filters.remove(value);
		}
		search_bar.setText("");
		products_panel.repaint();
		products_panel.revalidate();
	}
	
	private void clearBrandsFromList(List<JCheckBox> checkboxes, JCheckBox checkbox, List<String> filters, String brand) {
		filters.add(brand);
		for(int i = 0; i < filters.size(); i++) {
			if(!filters.get(i).equals("UK") && !filters.get(i).equals("US") && !filters.get(i).equals(brand) && !filters.get(i).equals("mouse") && !filters.get(i).equals("keyboard")) {
				filters.remove(i);
			} else {
				
			}
		}
		for(int i = 0; i < checkboxes.size(); i++) {
			if(!checkboxes.get(i).equals(checkbox)) {
				checkboxes.get(i).setSelected(false);
			}
		}
	}

}
