package user.control;

import java.util.Arrays;
import java.util.List;

public class Product {
	private int barcode;
	private String device_name;
	private String device_type;
	private String brand;
	private String color;
	private String connectivity;
	private int quantity_in_stock;
	private float original_price;
	private float retail_price;
	private int button_count;
	private String layout;
	
	public Product(int barcode, String device_name, String device_type, String brand, String color, String connectivity, int quantity_in_stock, float original_price, float retail_price, String layout, int button_count) {
		this.barcode = barcode;
		this.device_name = device_name;
		this.device_type = device_type;
		this.brand = brand;
		this.color = color;
		this.connectivity = connectivity;
		this.quantity_in_stock = quantity_in_stock;
		this.original_price = original_price;
		this.retail_price = retail_price;
		if(device_name.equals("keyboard")) {
			this.layout = layout;
			this.button_count = 0;
		} else if (device_name.equals("mouse")) {
			this.layout = null;
			this.button_count = button_count;
		} 
	}
	
	public Product(){
		
	}
	
	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int id) {
		this.barcode = id;
	}

	public String getDeviceName() {
		return device_name;
	}

	public void setDeviceName(String device_name) {
		this.device_name = device_name;
	}

	public String getDeviceType() {
		return device_type;
	}

	public void setDeviceType(String device_type) {
		this.device_type = device_type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand.toLowerCase();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getConnectivity() {
		return connectivity;
	}

	public void setConnectivity(String connectivity) {
		this.connectivity = connectivity;
	}

	public int getQuantityInStock() {
		return quantity_in_stock;
	}

	public void setQuantityInStock(int quantity_in_stock) {
		this.quantity_in_stock = quantity_in_stock;
	}

	public float getOriginalPrice() {
		return original_price;
	}

	public void setOriginalPrice(float original_price) {
		this.original_price = original_price;
	}

	public float getRetailPrice() {
		return retail_price;
	}

	public void setRetailPrice(float retail_price) {
		this.retail_price = retail_price;
	}

	public int getButtonCount() {
		return button_count;
	}

	public void setButtonCount(int button_count) {
		this.button_count = button_count;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	public String getName(){
		String output = this.getConnectivity() + " " + this.getBrand() + " " + this.getDeviceType() + " " + this.getDeviceName();
		if(this.getDeviceName().equals("keyboard")) {
		    output += " (" + this.getLayout() + ")";
		} else {
			if(this.getButtonCount() == 1) {
				output += " (" + this.getButtonCount() + " button)";
			} else {
				output += " (" + this.getButtonCount() + " buttons)";
			}
		}
		return output;
	}
	
	public List<String> getAll() {
		if(this.getDeviceName().equals("keyboard")) {
			String[] array = new String[] {Integer.toString(this.getBarcode()), this.getDeviceName(), this.getDeviceType(), this.getBrand(), this.getColor(), this.getConnectivity(), this.getLayout()};
			List<String> list = Arrays.asList(array);
			return list;
		} else {
			String[] array = new String[] {Integer.toString(this.getBarcode()), this.getDeviceName(), this.getDeviceType(), this.getBrand(), this.getColor(), this.getConnectivity(), Integer.toString(this.getButtonCount())};
			List<String> list = Arrays.asList(array);
			return list;
		}
	}
	
	public boolean isIdenticalTo(Product product) {
		boolean out = false;
		
			if(this.brand.equals(product.getBrand()) && this.device_name.equals(product.getDeviceName()) && this.device_type.equals(product.getDeviceType()) && this.color.equals(product.getColor()) && this.connectivity.equals(product.getConnectivity())) {
				if(this.device_name.equals("keyboard")) {
					if(this.layout.equals(product.getLayout())) {
						out = true;
					}
				} else if (this.device_name.equals("mouse")) {
					if(this.button_count == product.getButtonCount()) {
						out = true;
					}
				}
			}
			
		
		return out;
	}
	
}
