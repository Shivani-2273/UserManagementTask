package com.usermanagement.model;

public class Address {

	private int userId , is_default;
	private String addressLine, city, state, pin,addressId;

	public Address() {}
	
	public Address(int userId, int is_default, String addressLine, String city, String state, String pin,
			String addressId) {
		super();
		this.userId = userId;
		this.is_default = is_default;
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.addressId = addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public void setIsDefault(int is_default) {
		this.is_default = is_default;
	}

	public int getIsDefault() {
		return is_default;
	}

	@Override
	public String toString() {
		return "Address [userId=" + userId + ", is_default=" + is_default + ", addressLine=" + addressLine + ", city="
				+ city + ", state=" + state + ", pin=" + pin + ", addressId=" + addressId + "]";
	}

	
	

}
