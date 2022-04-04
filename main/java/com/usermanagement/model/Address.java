package com.usermanagement.model;

public class Address {

	private int userId, addressId, is_default;
	private String addressLine, city, state, pin;

	public Address() {
	}

	public Address(int userId, int addressId, String addressLine, String city, String state, String pin,
			int is_default) {
		super();
		this.userId = userId;
		this.addressId = addressId;
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.is_default = is_default;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getAddressId() {
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
		return "Address [userId=" + userId + ", addressId=" + addressId + ", addressLine=" + addressLine + ", city="
				+ city + ", state=" + state + ", pin=" + pin + ", is_default=" + is_default + "]";
	}

	

}
