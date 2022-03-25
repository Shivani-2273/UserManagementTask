package model;

public class Address {

	private int addressId,userId;
	private String streetAddress,city,state,pin;
	
	
	public void setAddressId(int addressId) {
		this.addressId=addressId;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setUserId(int userId) {
		this.userId=userId;
	}
	public int getUserId() {
		return userId;
	}
	public void setPin(String pin) {
		this.pin=pin;
	}
	public String getPin() {
		return pin;
	}
	public void setAddress(String streetAddress) {
		this.streetAddress=streetAddress;
	}
	public String getAddress() {
		return streetAddress;
	}
	public void setCity(String city) {
		this.city=city;
	}
	public String getCity() {
		return city;
	}
	public void setState(String state) {
		this.state=state;
	}
	public String getState() {
		return state;
	}

}
