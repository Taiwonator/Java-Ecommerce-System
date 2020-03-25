package user.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityLog {
	
	private int user_id;
	private String postcode;
	private int barcode;
	private float retail_price;
	private int quantity;
	private String status;
	private String payment_method;
	private String date;
	
	public static void main(String[] args) {
		new ActivityLog();
	}
	
	public ActivityLog() {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		this.setDate(dateFormat.format(date));
	}
	
	public ActivityLog(User user, Product product, int quantity, String status, String payment_method) {
		
		this.user_id = user.getId();
		this.postcode = user.getPostcode();
		this.barcode = product.getBarcode();
		this.retail_price = product.getRetailPrice();
		this.quantity = quantity;
		this.status = status;
		this.payment_method = payment_method;
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		this.setDate(dateFormat.format(date));
		
	}

	public String getPaymentMethod() {
		return payment_method;
	}

	public void setPaymentMethod(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getRetailPrice() {
		return retail_price;
	}

	public void setRetailPrice(float retail_price) {
		this.retail_price = retail_price;
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
