package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import app.Login;
import app.Home;
import app.AdminHome;
import app.ProductInterface;

import user.control.User;
import user.control.ActivityLog;
import user.control.ActivityLogFunctions;
import user.control.Basket;
import user.control.Product;
import user.control.ProductClassFunctions;
import user.control.SaveForLaterList;

public class InterfaceManager {
	
	public static User user;
	private static JLabel user_label;
	
	private static Login login_window;
	private static Home home_window;
	private static AdminHome admin_home_window;
	private static Object current_window;
	
	public static PaypalInterface PaypalInterface;
	public static CardInterface CardInterface;
	
	private static JPanel product_window;

	public enum State { IDLE, LOGIN, HOME, BROWSING, BASKET, SFL, PRODUCT, ADDPRODUCT }
	private static State active_state;
	public static List<State> state_list;
	private static int state_pointer = 0;
	
	public static Basket basket;
	public static JButton basket_button;
	public static SaveForLaterList sfl_list;
	
	public static Color white = Color.decode("#FFFFFF");
	public static Color grey = Color.decode("#E2E2E2");
	public static Color dark_grey = Color.decode("#B2B2B2");
	public static Color black = Color.decode("#303030");
	
	public static void main(String[] args) {
		InterfaceManager.setState(State.LOGIN);
		InterfaceManager.basket = new Basket();
		InterfaceManager.sfl_list = new SaveForLaterList();
		StateMachine();	
	}
	
	private static void InitLogin() {
		state_pointer = 0;
		state_list.clear();
		basket.clearBasket();
		sfl_list.clearBasket();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceManager.login_window = new Login();
					InterfaceManager.current_window = login_window;
					InterfaceManager.login_window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
     }
	
	private static void InitHome() {
		setState(State.BROWSING);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceManager.home_window = new Home();
					InterfaceManager.current_window = home_window;
					InterfaceManager.user_label.setText("Welcome " + user.getUsername());
					Home.home.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
     }
	
	private static void InitAdminHome() {
		setState(State.BROWSING);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceManager.admin_home_window = new AdminHome();
					InterfaceManager.current_window = home_window;
					InterfaceManager.user_label.setText("Welcome admin " + user.getUsername());
					AdminHome.admin_home.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
     }
	
	private static void OpenBasket(){
		Home.home.basket_panel = BasketInterface.returnBasketPanel();
		Home.home.navbar.repaint();
		Home.home.navbar.revalidate();
		InterfaceManager.Open(Home.home.basket_panel);
	}
	
	private static void OpenBrowsing(){
		if(user.getUserType().equals("customer")) {
			InterfaceManager.Open(Home.home.home_panel);
		} else {
			InterfaceManager.AdminOpen(AdminHome.admin_home.home_panel);
		}

	}
	
	private static void OpenSFL(){
		Home.home.sfl_panel = SFLInterface.returnSFLPanel();
		Home.home.navbar.repaint();
		Home.home.navbar.revalidate();
		InterfaceManager.Open(Home.home.sfl_panel);
	}
	
	public static void OpenProduct(Product product) {
		product_window = ProductInterface.viewProduct(product);
		if(user.getUserType().equals("customer")) {
			InterfaceManager.Open(product_window);
		} else {
			InterfaceManager.AdminOpen(product_window);
		}
	}
	
	public static void OpenProduct2() {
		if(product_window != null) {
			if(user.getUserType().equals("customer")) {
				InterfaceManager.Open(product_window);
			} else {
				InterfaceManager.AdminOpen(product_window);
			}
		}
	}
	
	private static void OpenAddProduct(){
		InterfaceManager.AdminOpen(AdminHome.admin_home.add_product_panel);
	}
	
	private static void Open(JPanel panel) {
		Home.home.container_panel.removeAll();
		Home.home.container_panel.add(panel);
		Home.home.container_panel.repaint();
		Home.home.container_panel.revalidate();
		Home.home.frame.getContentPane().repaint();
		Home.home.frame.getContentPane().revalidate();
	}
	
	private static void AdminOpen(JPanel panel) {
		AdminHome.admin_home.container_panel.removeAll();
		AdminHome.admin_home.container_panel.add(panel);
		AdminHome.admin_home.container_panel.repaint();
		AdminHome.admin_home.container_panel.revalidate();
		AdminHome.admin_home.frame.getContentPane().repaint();
		AdminHome.admin_home.frame.getContentPane().revalidate();
	}
	
 	
	private static void StateMachine() {
		while(true) {
			switch(active_state) {
				
				case LOGIN: {
					InitLogin();
					InterfaceManager.active_state = State.IDLE;
					break;
				} 
				
				case HOME: {
					if(user.getUserType().equals("customer")) {
					    InitHome();
					} else {
						InitAdminHome();
					}
					InterfaceManager.active_state = State.IDLE;
					break;
				}
				
				case BROWSING: {
					OpenBrowsing();
					InterfaceManager.active_state = State.IDLE;
					break;
				}	
				
				case BASKET: {
					OpenBasket();
					InterfaceManager.active_state = State.IDLE;
					break;
				}
				
				case SFL: {
					OpenSFL();
					InterfaceManager.active_state = State.IDLE;
					break;
				}
				
				case PRODUCT: {
					OpenProduct2();
					InterfaceManager.active_state = State.IDLE;
					break;
				}
				
				case ADDPRODUCT: {
					OpenAddProduct();
					InterfaceManager.active_state = State.IDLE;
					break;
				}
				
				default: {
					break;
				}
			}
		}
	}
	
	//Create list of products, and display in home panel
	
	public static void setState(State state) {
		if (InterfaceManager.state_list == null) {
			InterfaceManager.state_list = new ArrayList<State>();
		}
		InterfaceManager.active_state = state;
		if(state != State.LOGIN && state != State.HOME) {
			InterfaceManager.state_list.add(state_pointer, state);
			//Removes any duplicates
			if(state_pointer  <= InterfaceManager.state_list.size() - 2 && state_pointer > 0) {
				if(InterfaceManager.state_list.get(state_pointer) == InterfaceManager.state_list.get(state_pointer - 1)) {
					InterfaceManager.state_list.remove(state_pointer);
					//Not sure I guess resets the state counter
					state_pointer -= 1;
				}
			}
		}
//		System.out.println(state_list);
	}
	
	public static void setUser(User user) {
		InterfaceManager.user = user;
	}
	
	public static User getUser() {
		return InterfaceManager.user;
	}
	
	private static JPanel createNavbar() {
		
		JPanel navbar = new JPanel();
		navbar.setBorder(null);
		navbar.setBounds(0, 0, 1028, 62);
		navbar.setBackground(black);
		navbar.setLayout(null);
		
		JButton forward_button = new JButton("");
		JButton back_button = new JButton("");
		 
		back_button.setIcon(new ImageIcon(Home.class.getResource("/res/Arrow.png")));
		back_button.setBounds(10, 11, 46, 43);
		back_button.setBorderPainted(false); 
		back_button.setContentAreaFilled(false); 
		back_button.setOpaque(false);
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(state_pointer < state_list.size() - 1) {
					state_pointer += 1;
					State prev_state = state_list.get(state_pointer);
					active_state = prev_state;
				}
				
				
			}
		});
		navbar.add(back_button);
		
		forward_button.setIcon(new ImageIcon(Home.class.getResource("/res/ForwardArrow.png")));
		forward_button.setBounds(60, 11, 46, 43);
		forward_button.setBorderPainted(false); 
		forward_button.setContentAreaFilled(false); 
		forward_button.setOpaque(false);
		forward_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(state_pointer > 0) {
					state_pointer -= 1;
					State prev_state = state_list.get(state_pointer);
					active_state = prev_state;
				}

			}
		});
		navbar.add(forward_button);
		
		JLabel user_button = new JLabel("");
		user_button.setBackground(UIManager.getColor("Button.background"));
		user_button.setBounds(950, 0, 69, 63);
		user_button.setIcon(new ImageIcon(Home.class.getResource("/res/NavAvatar.png")));
		navbar.add(user_button);
		
		user_label = new JLabel("Welcome admin " + user.getUsername());
		user_label.setFont(new Font("Tahoma", Font.BOLD, 12));
		user_label.setHorizontalAlignment(SwingConstants.RIGHT);
		user_label.setForeground(Color.WHITE);
		user_label.setBounds(774, 11, 154, 43);
		navbar.add(user_label);
				
		JButton logout_button = new JButton("Log out");
		logout_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		logout_button.setForeground(Color.WHITE);
		
		logout_button.addActionListener(new ActionListener() {
			int dialog_button = JOptionPane.YES_NO_OPTION;
			public void actionPerformed(ActionEvent arg0) {
				int dialog_result = JOptionPane.showConfirmDialog (null, "Are you sure you'd like to log out?","Warning" ,dialog_button);
				if(dialog_result == JOptionPane.YES_OPTION){
					InterfaceManager.Logout ();
					InterfaceManager.setState(InterfaceManager.State.LOGIN);
				}
			}
		});
		
		logout_button.setBorderPainted(false); 
		logout_button.setContentAreaFilled(false); 
		logout_button.setOpaque(false);
		logout_button.setBounds(639, 11, 89, 43);
		navbar.add(logout_button);
		
		return navbar;
	}

	
	public static JPanel InitNavbar() {
		
		JPanel navbar = createNavbar();
		
		basket_button = new JButton("0");
		basket_button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		basket_button.setForeground(Color.WHITE);
		basket_button.setIcon(new ImageIcon(Home.class.getResource("/res/Basket.png")));
		basket_button.setBounds(719, 10, 100, 41);
		basket_button.setBorderPainted(false); 
		basket_button.setContentAreaFilled(false); 
		basket_button.setOpaque(false);
		basket_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(InterfaceManager.state_list.get(state_pointer) != State.BASKET) {
				    InterfaceManager.setState(InterfaceManager.State.BASKET);
				}
			}
		});
		navbar.add(basket_button);
				
		JButton sfl_button = new JButton("Save for later");
		sfl_button.setMinimumSize(new Dimension(112, 23));
		sfl_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		sfl_button.setForeground(Color.WHITE);
		sfl_button.setBounds(525, 12, 118, 43);
		sfl_button.setBorderPainted(false); 
		sfl_button.setContentAreaFilled(false); 
		sfl_button.setOpaque(false);
		sfl_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(InterfaceManager.state_list.get(state_pointer) != State.SFL) {
				    InterfaceManager.setState(InterfaceManager.State.SFL);
				}
			}
		});
		navbar.add(sfl_button);
		
		return navbar;
	}
	
	public static void addToBasket(Product product, int quantity) {
		InterfaceManager.basket.addProduct(product, quantity);
		InterfaceManager.basket_button.setText(Integer.toString(InterfaceManager.basket.getBasketSize()));
//		System.out.println("Size: " + InterfaceManager.basket_button.getText());
		JOptionPane.showMessageDialog(Home.home.frame, Integer.toString(quantity) + " " + product.getName() + " added to shopping basket", "Shopping basket", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void addToStock(Product product, int quantity) {
		product.setQuantityInStock(product.getQuantityInStock() + quantity);
		ProductClassFunctions.updateProduct(product);
		ProductInterface.product_stock.setText(Integer.toString(product.getQuantityInStock()) + " in stock");
		JOptionPane.showMessageDialog(AdminHome.admin_home.frame, Integer.toString(quantity) + " " + product.getName() + " added to stock", "Stock", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static JPanel InitAdminNavbar() {
		
		JPanel navbar = createNavbar();
		
		JButton add_product_button = new JButton("Add Product");
		add_product_button.setMinimumSize(new Dimension(112, 23));
		add_product_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		add_product_button.setForeground(Color.WHITE);
		add_product_button.setBounds(525, 12, 118, 43);
		add_product_button.setBorderPainted(false); 
		add_product_button.setContentAreaFilled(false); 
		add_product_button.setOpaque(false);
		add_product_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(InterfaceManager.state_list.get(state_pointer) != State.ADDPRODUCT) {
				    InterfaceManager.setState(InterfaceManager.State.ADDPRODUCT);
				}
			}
		});
		navbar.add(add_product_button);
		
		return navbar;
	}
	
	private static void Logout() {
		if(user.getUserType().equals("customer")) {
			Home.home.frame.dispose();
		} else {
			// Makes sure the right frame is disposed
			InterfaceManager.setState(State.BROWSING);
			AdminHome.admin_home.frame.dispose();
		}
		InterfaceManager.user = null;
	}
	
	public static void refreshBasket() {
		basket_button.setText(Integer.toString(basket.getBasketSize()));
		OpenBasket();
	}
	
	public static void clearBasket() {
		//LOGGING
		for(int i = 0; i < basket.getProducts().size(); i++) {
			Product product = basket.getProducts().get(i);
			ActivityLog log = new ActivityLog(user, product, basket.getBasket().get(product), "cancelled", "");
			createActivityLog(log);
		}
		 basket.clearBasket();
		 basket_button.setText("0");
		 OpenBasket();
		 JOptionPane.showMessageDialog(Home.home.home_panel, "Basket successfully cleared", "Basked", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void removeBasketProduct(Product product) {
		ActivityLog log = new ActivityLog(user, product, basket.getBasket().get(product), "cancelled", "");
		createActivityLog(log);
		
		basket.removeAllProduct(product);
		basket_button.setText(Integer.toString(basket.getBasketSize()));
		OpenBasket();
		JOptionPane.showMessageDialog(Home.home.home_panel, "Item successfully removed", "Item removal", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void increaseQuantity(Product product) {
		basket.addProduct(product);
		basket_button.setText(Integer.toString(basket.getBasketSize()));
		OpenBasket();
	}
	
	public static void decreaseQuantity(Product product) {
		basket.removeProduct(product);
		basket_button.setText(Integer.toString(basket.getBasketSize()));
		OpenBasket();
	}
	
	
	public static void moveToSFL(Product product) {
		ActivityLog log = new ActivityLog(user, product, basket.getBasket().get(product), "saved", "");
		createActivityLog(log);
		
		sfl_list.addPair(basket, product);
		basket.removeAllProduct(product);
		OpenBasket();
		basket_button.setText(Integer.toString(basket.getBasketSize()));
		JOptionPane.showMessageDialog(Home.home.home_panel, "Item successfully moved", "Item moved", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void moveToBasket(Product product) {
		basket.addPair(sfl_list, product);
		sfl_list.removeAllProduct(product);
		OpenSFL();
		basket_button.setText(Integer.toString(basket.getBasketSize()));
		JOptionPane.showMessageDialog(Home.home.home_panel, "Item successfully moved", "Item moved", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void clearSFL() {		
		 sfl_list.clearBasket();
		 OpenSFL();
		 JOptionPane.showMessageDialog(Home.home.home_panel, "Saved for later cleared", "Saved for later", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void removeSFLProduct(Product product) {
		sfl_list.removeAllProduct(product);
		OpenSFL();
		JOptionPane.showMessageDialog(Home.home.home_panel, "Item successfully removed", "Item removal", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void increaseSFLQuantity(Product product) {
		sfl_list.addProduct(product);
		OpenSFL();
	}
	
	public static void decreaseSFLQuantity(Product product) {
		sfl_list.removeProduct(product);
		OpenSFL();
	}
	
	public static void moveAllToSFL(Basket basket) {
		
		for(int i = 0; i < basket.getProducts().size(); i++) {
			Product product = basket.getProducts().get(i);
			ActivityLog log = new ActivityLog(user, product, basket.getBasket().get(product), "saved", "");
			createActivityLog(log);
		}
		
		sfl_list.mergeWith(basket);
		basket.clearBasket();
		OpenBasket();
		basket_button.setText(Integer.toString(basket.getBasketSize()));
		JOptionPane.showMessageDialog(Home.home.home_panel, "Items successfully moved", "Items moved", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void moveAllToBasket(SaveForLaterList sfl_list) {
		basket.mergeWith(sfl_list);
		sfl_list.clearBasket();
		OpenSFL();
		basket_button.setText(Integer.toString(basket.getBasketSize()));
		JOptionPane.showMessageDialog(Home.home.home_panel, "Items successfully moved", "Items moved", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void createActivityLog(ActivityLog log) {
		try {
			ActivityLogFunctions.writeToLog(log);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void carryOutPayment(String payment_type) {
		String message = String.format("£" + "%.2f", basket.getTotal()) + " paid for using " + payment_type;
		JOptionPane.showMessageDialog(Home.home.home_panel, message, "Payment Success", JOptionPane.INFORMATION_MESSAGE);
		List<Product> basket_products = basket.getProducts();
		for(int i = 0; i < basket_products.size(); i++) {
			Product product = basket_products.get(i);
			
			ActivityLog log = new ActivityLog(user, product, basket.getBasket().get(product), "purchased", payment_type);
			createActivityLog(log);
			
			product.setQuantityInStock(product.getQuantityInStock() - basket.getBasket().get(product));
			ProductClassFunctions.updateProduct(product);
		}
		basket.clearBasket();
		OpenBrowsing();
	}
	
	public static void refreshPage() {
		basket_button.setText(Integer.toString(basket.getBasketSize()));
		OpenBrowsing();
	}
	
	
	
}
