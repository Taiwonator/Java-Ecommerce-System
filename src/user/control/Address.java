package user.control;


public class Address {
	public int house_number;
	public String postcode;
	public String city;
	
	//full constructor
	public Address(int house_number, String postcode, String city) {
		this.house_number = house_number;
		this.postcode = postcode;
		this.city = city;
	}
	
	//blank constructor
	public Address() {
		
	}

}
