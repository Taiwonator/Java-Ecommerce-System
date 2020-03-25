package experiments;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import app.ProductFunctions;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFormattedTextField;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import app.*;

public class HomeExperiment {

	public JFrame frame;
	public JPanel container_panel;
	public JPanel home_panel;
	public JPanel basket_panel;
	public JPanel sfl_panel;
	private JPanel product_1;
	private JLabel image_1;
	private JLabel name_1;
	private JLabel price_1;
	private JPanel products_panel;
	private JPanel product;
	private JLabel image;
	private JLabel name;
	private JLabel price;
	private JPanel product_2;
	private JLabel image_2;
	private JLabel name_2;
	private JLabel price_2;
	private JPanel product_3;
	private JLabel image_3;
	private JLabel name_3;
	private JLabel price_3;
	private JPanel product_4;
	private JLabel image_4;
	private JLabel name_4;
	private JLabel price_4;
	private JPanel product_5;
	private JLabel image_5;
	private JLabel name_5;
	private JLabel price_5;
	
	public static void main(String[] args) {
		HomeExperiment x = new HomeExperiment();
	}

	
	public HomeExperiment() {
		initialize();
	}

	
	private void initialize() {
		Color white = Color.decode("#FFFFFF");
		Color grey = Color.decode("#E2E2E2");
		Color dark_grey = Color.decode("#B2B2B2");
		Color black = Color.decode("#303030");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1033, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(white);
		frame.setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int x = (dim.width / 2) - (frame.getWidth() / 2);
		int y = (dim.height / 2) - (frame.getHeight() / 2);
		frame.setLocation(x, y);	
		frame.getContentPane().add(InterfaceManager.InitNavbar());
		
		container_panel = new JPanel();
		container_panel.setBackground(Color.WHITE);
		container_panel.setBounds(0, 0, 1027, 586);
		frame.getContentPane().add(container_panel);
		container_panel.setLayout(new CardLayout(0, 0));
		
		home_panel = new JPanel();
		home_panel.setBackground(Color.WHITE);
		container_panel.add(home_panel, "name_9704827776300");
		home_panel.setLayout(null);
		
		basket_panel = new JPanel();
		basket_panel.setBackground(Color.YELLOW);
		container_panel.add(basket_panel, "name_9704822002700");
		basket_panel.setLayout(null);
		
		sfl_panel = new JPanel();
		sfl_panel.setBackground(Color.GREEN);
		container_panel.add(sfl_panel);
		sfl_panel.setLayout(null);
		
		JPanel side_panel = new JPanel();
		side_panel.setBounds(0, 0, 162, 586);
		side_panel.setBackground(black);
		home_panel.add(side_panel);
		side_panel.setLayout(null);
		
		products_panel = new JPanel();
		products_panel.setAutoscrolls(true);
		products_panel.setBounds(161, 68, 866, 518);
		home_panel.add(products_panel);
		products_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		product = new JPanel();
		product.setBorder(null);
		products_panel.add(product);
		GridBagLayout gbl_product = new GridBagLayout();
		gbl_product.columnWidths = new int[]{153, 0};
		gbl_product.rowHeights = new int[]{124, 0, 14, 14, 0};
		gbl_product.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_product.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		product.setLayout(gbl_product);
		
		image = new JLabel("");
		GridBagConstraints gbc_image = new GridBagConstraints();
		gbc_image.insets = new Insets(10, 20, 10, 20);
		gbc_image.gridx = 0;
		gbc_image.gridy = 0;
		product.add(image, gbc_image);
		
		name = new JLabel("Apollo Tall Wide Shelf Unit - Oak");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.insets = new Insets(10, 20, 10, 20);
		gbc_name.gridx = 0;
		gbc_name.gridy = 2;
		product.add(name, gbc_name);
		
		price = new JLabel("\u00A319.99");
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_price = new GridBagConstraints();
		gbc_price.gridx = 0;
		gbc_price.gridy = 3;
		product.add(price, gbc_price);
		
		product_2 = new JPanel();
		product_2.setBorder(null);
		products_panel.add(product_2);
		GridBagLayout gbl_product_2 = new GridBagLayout();
		gbl_product_2.columnWidths = new int[]{153, 0};
		gbl_product_2.rowHeights = new int[]{124, 0, 14, 14, 0};
		gbl_product_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_product_2.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		product_2.setLayout(gbl_product_2);
		
		image_2 = new JLabel("");
		GridBagConstraints gbc_image_2 = new GridBagConstraints();
		gbc_image_2.insets = new Insets(10, 20, 10, 20);
		gbc_image_2.gridx = 0;
		gbc_image_2.gridy = 0;
		product_2.add(image_2, gbc_image_2);
		
		name_2 = new JLabel("Apollo Tall Wide Shelf Unit - Oak");
		name_2.setHorizontalAlignment(SwingConstants.CENTER);
		name_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_name_2 = new GridBagConstraints();
		gbc_name_2.insets = new Insets(10, 20, 10, 20);
		gbc_name_2.gridx = 0;
		gbc_name_2.gridy = 2;
		product_2.add(name_2, gbc_name_2);
		
		price_2 = new JLabel("\u00A319.99");
		price_2.setHorizontalAlignment(SwingConstants.CENTER);
		price_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_price_2 = new GridBagConstraints();
		gbc_price_2.gridx = 0;
		gbc_price_2.gridy = 3;
		product_2.add(price_2, gbc_price_2);
		
		product_3 = new JPanel();
		product_3.setBorder(null);
		products_panel.add(product_3);
		GridBagLayout gbl_product_3 = new GridBagLayout();
		gbl_product_3.columnWidths = new int[]{153, 0};
		gbl_product_3.rowHeights = new int[]{124, 0, 14, 14, 0};
		gbl_product_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_product_3.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		product_3.setLayout(gbl_product_3);
		
		image_3 = new JLabel("");
		GridBagConstraints gbc_image_3 = new GridBagConstraints();
		gbc_image_3.insets = new Insets(10, 20, 10, 20);
		gbc_image_3.gridx = 0;
		gbc_image_3.gridy = 0;
		product_3.add(image_3, gbc_image_3);
		
		name_3 = new JLabel("Apollo Tall Wide Shelf Unit - Oak");
		name_3.setHorizontalAlignment(SwingConstants.CENTER);
		name_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_name_3 = new GridBagConstraints();
		gbc_name_3.insets = new Insets(10, 20, 10, 20);
		gbc_name_3.gridx = 0;
		gbc_name_3.gridy = 2;
		product_3.add(name_3, gbc_name_3);
		
		price_3 = new JLabel("\u00A319.99");
		price_3.setHorizontalAlignment(SwingConstants.CENTER);
		price_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_price_3 = new GridBagConstraints();
		gbc_price_3.gridx = 0;
		gbc_price_3.gridy = 3;
		product_3.add(price_3, gbc_price_3);
		
		product_4 = new JPanel();
		product_4.setBorder(null);
		products_panel.add(product_4);
		GridBagLayout gbl_product_4 = new GridBagLayout();
		gbl_product_4.columnWidths = new int[]{153, 0};
		gbl_product_4.rowHeights = new int[]{124, 0, 14, 14, 0};
		gbl_product_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_product_4.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		product_4.setLayout(gbl_product_4);
		
		image_4 = new JLabel("");
		GridBagConstraints gbc_image_4 = new GridBagConstraints();
		gbc_image_4.insets = new Insets(10, 20, 10, 20);
		gbc_image_4.gridx = 0;
		gbc_image_4.gridy = 0;
		product_4.add(image_4, gbc_image_4);
		
		name_4 = new JLabel("Apollo Tall Wide Shelf Unit - Oak");
		name_4.setHorizontalAlignment(SwingConstants.CENTER);
		name_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_name_4 = new GridBagConstraints();
		gbc_name_4.insets = new Insets(10, 20, 10, 20);
		gbc_name_4.gridx = 0;
		gbc_name_4.gridy = 2;
		product_4.add(name_4, gbc_name_4);
		
		price_4 = new JLabel("\u00A319.99");
		price_4.setHorizontalAlignment(SwingConstants.CENTER);
		price_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_price_4 = new GridBagConstraints();
		gbc_price_4.gridx = 0;
		gbc_price_4.gridy = 3;
		product_4.add(price_4, gbc_price_4);
		
		product_5 = new JPanel();
		product_5.setBorder(null);
		products_panel.add(product_5);
		GridBagLayout gbl_product_5 = new GridBagLayout();
		gbl_product_5.columnWidths = new int[]{153, 0};
		gbl_product_5.rowHeights = new int[]{124, 0, 14, 14, 0};
		gbl_product_5.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_product_5.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		product_5.setLayout(gbl_product_5);
		
		image_5 = new JLabel("");
		GridBagConstraints gbc_image_5 = new GridBagConstraints();
		gbc_image_5.insets = new Insets(10, 20, 10, 20);
		gbc_image_5.gridx = 0;
		gbc_image_5.gridy = 0;
		product_5.add(image_5, gbc_image_5);
		
		name_5 = new JLabel("Apollo Tall Wide Shelf Unit - Oak");
		name_5.setHorizontalAlignment(SwingConstants.CENTER);
		name_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_name_5 = new GridBagConstraints();
		gbc_name_5.insets = new Insets(10, 20, 10, 20);
		gbc_name_5.gridx = 0;
		gbc_name_5.gridy = 2;
		product_5.add(name_5, gbc_name_5);
		
		price_5 = new JLabel("\u00A319.99");
		price_5.setHorizontalAlignment(SwingConstants.CENTER);
		price_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_price_5 = new GridBagConstraints();
		gbc_price_5.gridx = 0;
		gbc_price_5.gridy = 3;
		product_5.add(price_5, gbc_price_5);
		
	}
}
