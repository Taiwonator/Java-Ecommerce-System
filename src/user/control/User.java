package user.control;


public class User {
	private int id;
	private String username;
	private String surname;
	private Address address;
	private String user_type;
	
	//full constructor
	public User(int id, String username, String surname, int house_number, String postcode, String city, String user_type) {
		address = new Address();
		
		this.id = id;
		this.username = username;
		this.surname = surname;
		this.address.house_number = house_number;
		this.address.postcode = postcode;
		this.address.city = city;
		this.user_type = user_type;
	}
	
	//blank constructor
	public User() {
		
	}
	
	//-----------------------GETTERS AND SETTERS------------------------------
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
	    return this.username;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void setAddress(int house_number, String postcode, String city) {
		address = new Address(house_number, postcode, city);
		this.address = address;
	}
	
	public int getHouseNumber() {
		return this.address.house_number;
	}
	
	public String getPostcode() {
		return this.address.postcode;
	}
	
	public String getCity() {
		return this.address.city;
	}
	
	public void setUserType(String user_type) {
		this.user_type = user_type;
	}
	
	public String getUserType() {
		return this.user_type;
	}
	
	//-------------------------------------------------------------------------
}
